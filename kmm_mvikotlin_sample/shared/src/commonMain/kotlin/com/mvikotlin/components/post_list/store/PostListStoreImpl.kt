package com.mvikotlin.components.post_list.store

import com.arkivanov.mvikotlin.core.store.*
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.delay
import com.badoo.reaktive.single.doOnAfterTerminate
import com.badoo.reaktive.single.singleFromFunction
import com.mvikotlin.components.post_list.PostListItem
import com.mvikotlin.components.post_list.store.PostListStore.*
import com.mvikotlin.repository.PostRepository

internal class PostListStoreImpl(
    private val storeFactory: StoreFactory,
    private val postRepository: PostRepository,
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
            dispatch(
                Message.Loaded(
                    posts = List(100) {
                        PostListItem(
                            id = it.toLong(),
                            title = "Post $it"
                        )
                    }
                )
            )
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.Refresh -> refresh(keepContent = intent.keepContent)
            }
        }

        private fun refresh(keepContent: Boolean) {
            singleFromFunction {
                dispatch(Message.Loading(keepContent))
            }
                .delay(1000, mainScheduler)
                .doOnAfterTerminate {
                    dispatch(
                        Message.Loaded(
                            posts = List(100) {
                                PostListItem(
                                    id = it.toLong(),
                                    title = "Refreshed Post $it"
                                )
                            }
                        )
                    )
                }
                .subscribeScoped(isThreadLocal = true, {
                    print("success")
                })
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