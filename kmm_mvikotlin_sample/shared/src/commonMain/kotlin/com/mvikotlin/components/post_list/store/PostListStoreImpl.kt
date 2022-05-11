package com.mvikotlin.components.post_list.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.single.subscribeOn
import com.mvikotlin.components.post_list.PostListItem
import com.mvikotlin.components.post_list.integration.toDomain
import com.mvikotlin.components.post_list.store.PostListStore.Intent
import com.mvikotlin.components.post_list.store.PostListStore.Label
import com.mvikotlin.components.post_list.store.PostListStore.State
import com.mvikotlin.repository.PostRepository

internal class PostListStoreImpl(
    private val storeFactory: StoreFactory,
    private val postRepository: PostRepository
) {

    fun create(): PostListStore =
        object : PostListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "${PostListStore::class.simpleName}",
            initialState = State.Loading,
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutionImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Message {
        data class Loaded(val posts: List<PostListItem>) : Message()
        data class Loading(val keepContent: Boolean) : Message()
        object Error : Message()
    }

    private inner class ExecutionImpl : ReaktiveExecutor<Intent, Unit, State, Message, Label>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            loadPosts(forceUpdate = true)
        }

        private fun loadPosts(forceUpdate: Boolean) {
            postRepository.getPosts(forceUpdate)
                .map { posts -> posts.map { it.toDomain() } }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe{ dispatch(Message.Loaded(it)) }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.Refresh -> refresh(keepContent = intent.keepContent)
            }
        }

        private fun refresh(keepContent: Boolean) {
            dispatch(Message.Loading(keepContent))
            loadPosts(forceUpdate = keepContent)
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State =
            when (this) {
                is State.Content -> when (msg) {
                    is Message.Loaded -> copy(posts = msg.posts, isRefreshing = false)
                    is Message.Loading -> if (msg.keepContent) copy(isRefreshing = true) else State.Loading
                    Message.Error -> State.Error
                }
                else -> when (msg) {
                    is Message.Loaded -> State.Content(posts = msg.posts)
                    is Message.Loading -> State.Loading
                    Message.Error -> State.Error
                }
            }
    }
}