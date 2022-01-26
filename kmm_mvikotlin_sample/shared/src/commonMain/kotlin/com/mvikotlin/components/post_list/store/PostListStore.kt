package com.mvikotlin.components.post_list.store

import com.arkivanov.mvikotlin.core.store.Store
import com.mvikotlin.components.post_list.PostListItem
import com.mvikotlin.components.post_list.store.PostListStore.*

internal interface PostListStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class Refresh(val keepContent: Boolean): Intent()
    }

    sealed class Label {

    }

    sealed class State {
        object Error : State()
        object Loading : State()
        data class Content(
            val posts: List<PostListItem> = emptyList(),
            val isRefreshing: Boolean = false
        ): State()
    }
}