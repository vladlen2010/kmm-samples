package com.mvikotlin.components.post_list.integration

import com.mvikotlin.components.post_list.PostListComponent.Model
import com.mvikotlin.components.post_list.PostListItem
import com.mvikotlin.components.post_list.store.PostListStore.State
import com.mvikotlin.response.Post

internal val stateToModel: (State) -> Model =
    {
        when (it) {
            is State.Content -> Model.Content(
                items = it.posts,
                isRefreshing = it.isRefreshing
            )
            State.Error -> Model.Error
            State.Loading -> Model.Loading
        }
    }

internal fun Post.toDomain(): PostListItem {
   return PostListItem(
       id = id,
       title = title
   )
}
