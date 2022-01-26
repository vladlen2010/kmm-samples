package com.mvikotlin.components.post_details.integration

import com.mvikotlin.components.post_details.PostDetailsComponent.Model
import com.mvikotlin.components.post_details.store.PostDetailsStore.State

internal val stateToModel: (State) -> Model =
    {
        when (it) {
            is State.Content -> Model.Content(
                title = it.post.title,
                body = it.post.body,
            )
            State.Error -> Model.Error
            State.Loading -> Model.Loading
        }
    }