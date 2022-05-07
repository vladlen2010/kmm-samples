package com.mvikotlin.components.edit.store

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
import com.mvikotlin.components.edit.EditProfileItem
import com.mvikotlin.components.edit.integration.toDomain
import com.mvikotlin.components.edit.store.EditProfileStore.Intent
import com.mvikotlin.components.edit.store.EditProfileStore.Label
import com.mvikotlin.components.edit.store.EditProfileStore.State
import com.mvikotlin.repository.ProfileRepository

internal class EditProfileStoreImpl(
    private val userId: Long,
    private val storeFactory: StoreFactory,
    private val profileRepository: ProfileRepository,
) {

    fun create() : EditProfileStore =
        object : EditProfileStore, Store<Intent, State, Label> by storeFactory.create(
            name = "${EditProfileStore::class.simpleName}",
            initialState = State.Initial,
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}


    private sealed class Message {
        data class ProfileLoaded(val profile: EditProfileItem) : Message()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Message, Label>() {

        override fun executeAction(action: Unit, getState: () -> State) {
            profileRepository.getProfile(userId)
                .map { it.toDomain() }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeScoped(
                    isThreadLocal = true,
                    onSuccess = { dispatch(Message.ProfileLoaded(it)) }
                )
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.UsernameChanged -> Unit //TODO:
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State =
            when (this) {
                is State.Content -> when (msg) {
                    is Message.ProfileLoaded -> copy(profileItem = msg.profile)
                }
                else -> when (msg) {
                    is Message.ProfileLoaded -> State.Content(profileItem = msg.profile)
                }
            }

    }
}