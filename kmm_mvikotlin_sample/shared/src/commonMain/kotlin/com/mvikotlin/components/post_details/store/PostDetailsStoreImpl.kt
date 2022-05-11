package com.mvikotlin.components.post_details.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribeOn
import com.mvikotlin.components.post_details.PostDetailsItem
import com.mvikotlin.components.post_details.integration.toDomain
import com.mvikotlin.components.post_details.store.PostDetailsStore.Intent
import com.mvikotlin.components.post_details.store.PostDetailsStore.Label
import com.mvikotlin.components.post_details.store.PostDetailsStore.State
import com.mvikotlin.repository.PostRepository

internal class PostDetailsStoreImpl(
    private val postId: Long,
    private val storeFactory: StoreFactory,
    private val postRepository: PostRepository
) {

    fun create(): PostDetailsStore =
        object : PostDetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "${PostDetailsStore::class.simpleName}",
            initialState = State.Loading,
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Message {
        object Error : Message()
        object Loading : Message()
        data class ItemLoaded(val post: PostDetailsItem) : Message()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Message, Label>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            loadPost()
        }

        private fun loadPost() {
            postRepository.getPostById(postId)
                .map { it.toDomain() }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeScoped{  dispatch(Message.ItemLoaded(it)) }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.Reload -> {
                    dispatch(Message.Loading)
                    loadPost()
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State =
            when (this) {
                is State.Content -> when (msg) {
                    is Message.ItemLoaded -> copy(post = msg.post)
                    Message.Loading -> State.Loading
                    Message.Error -> State.Error
                }
                else -> when (msg) {
                    is Message.ItemLoaded -> State.Content(post = msg.post)
                    Message.Loading -> State.Loading
                    Message.Error -> State.Error
                }
            }
    }
}