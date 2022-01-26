package com.mvikotlin.components.edit.store

import com.arkivanov.mvikotlin.core.store.Store
import com.mvikotlin.components.edit.EditProfileItem
import com.mvikotlin.components.edit.store.EditProfileStore.Intent
import com.mvikotlin.components.edit.store.EditProfileStore.Label
import com.mvikotlin.components.edit.store.EditProfileStore.State

internal interface EditProfileStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class UsernameChanged(val userId: Long, val username: String) : Intent()
    }

    sealed class Label {
        object Changed: Label()
    }

    sealed class State {
        object Initial: State()
        object NotValid: State()
        data class Content(
            val profileItem: EditProfileItem = EditProfileItem(),
        ): State()
    }
}