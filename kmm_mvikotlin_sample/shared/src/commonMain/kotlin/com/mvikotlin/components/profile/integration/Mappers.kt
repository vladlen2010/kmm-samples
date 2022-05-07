package com.mvikotlin.components.profile.integration

import com.mvikotlin.components.profile.ProfileComponent.Model
import com.mvikotlin.components.profile.ProfileItem
import com.mvikotlin.components.profile.store.ProfileStore.State
import com.mvikotlin.response.User


internal val stateToModel: (State) -> Model =
    {
        when(it) {
            is State.Content -> Model.Content(
                name = it.profileItem.name,
                username = it.profileItem.username,
            )
            State.Error -> Model.Error
            State.Loading -> Model.Loading
        }
    }

internal fun User.toDomain() :ProfileItem {
    return ProfileItem(
        userId = id,
        name = name,
        username = username
    )
}