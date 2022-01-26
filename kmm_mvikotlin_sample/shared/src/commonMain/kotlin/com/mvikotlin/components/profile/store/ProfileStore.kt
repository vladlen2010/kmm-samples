package com.mvikotlin.components.profile.store

import com.arkivanov.mvikotlin.core.store.Store
import com.mvikotlin.components.profile.ProfileItem
import com.mvikotlin.components.profile.store.ProfileStore.Intent
import com.mvikotlin.components.profile.store.ProfileStore.State
import com.mvikotlin.components.profile.store.ProfileStore.Label

internal interface ProfileStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Reload : Intent()
    }

    sealed class Label {
        object Error : Label()
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class Content(
            val profileItem: ProfileItem = ProfileItem(),
        ) : State()
    }
}