package com.mvikotlin.components.profile.store

import com.arkivanov.mvikotlin.core.store.*
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.delay
import com.badoo.reaktive.single.singleFromFunction
import com.mvikotlin.components.profile.ProfileItem
import com.mvikotlin.components.profile.store.ProfileStore.Intent
import com.mvikotlin.components.profile.store.ProfileStore.Intent.Reload
import com.mvikotlin.components.profile.store.ProfileStore.Label
import com.mvikotlin.components.profile.store.ProfileStore.State
import com.mvikotlin.repository.ProfileRepository

internal class ProfileStoreImpl(
    private val userId: Long,
    private val storeFactory: StoreFactory,
    private val profileRepository: ProfileRepository,
) {

    fun create(): ProfileStore =
        object : ProfileStore, Store<Intent, State, Label> by storeFactory.create(
            name = "${ProfileStore::class.simpleName}",
            initialState = State.Loading,
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Message {
        object Loading : Message()
        object Error : Message()
        data class ItemLoaded(val profile: ProfileItem) : Message()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Message, Label>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            dispatch(
                Message.ItemLoaded(
                    ProfileItem(
                        userId = userId,
                        name = "Vladlen",
                        username = "Vladlen2010"
                    )
                )
            )
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Reload -> loadUser()
            }
        }

        private fun loadUser() {
//            profileRepository.loadUser(userId) //TODO:
            singleFromFunction {
                dispatch(Message.Loading)
            }
                .delay(1000, mainScheduler)
                .subscribeScoped(isThreadLocal = true) {
                    dispatch(
                        Message.ItemLoaded(
                            ProfileItem(
                                userId = userId,
                                name = "Vladlen",
                                username = "Vladlen2010"
                            )
                        )
                    )
                }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State =
            when (this) {
                is State.Content -> when (msg) {
                    is Message.ItemLoaded -> copy(profileItem = msg.profile)
                    Message.Error -> State.Error
                    Message.Loading -> State.Loading
                }
                else -> when (msg) {
                    is Message.ItemLoaded -> State.Content(profileItem = msg.profile)
                    Message.Error -> State.Error
                    Message.Loading -> State.Loading
                }
            }
    }
}