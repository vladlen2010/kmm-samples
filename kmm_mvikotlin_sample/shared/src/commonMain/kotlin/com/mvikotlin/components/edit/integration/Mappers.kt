package com.mvikotlin.components.edit.integration

import com.mvikotlin.components.edit.EditProfileComponent.Model
import com.mvikotlin.components.edit.store.EditProfileStore.State

internal val stateToModel: (State) -> Model =
    {
        when (it) {
            State.Initial -> Model.Initial
            State.NotValid -> Model.Error
            is State.Content -> Model.Content(
                name = it.profileItem.name,
                username = it.profileItem.userName,
            )
        }

    }