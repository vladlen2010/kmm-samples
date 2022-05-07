package com.mvikotlin.components.post_details.integration

import com.mvikotlin.components.post_details.PostDetailsComponent.Model
import com.mvikotlin.components.post_details.PostDetailsItem
import com.mvikotlin.components.post_details.store.PostDetailsStore.State
import com.mvikotlin.response.Post

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

internal fun Post.toDomain(): PostDetailsItem = PostDetailsItem(title = title, body = body)