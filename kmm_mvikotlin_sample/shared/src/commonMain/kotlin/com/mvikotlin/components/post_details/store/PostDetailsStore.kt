package com.mvikotlin.components.post_details.store

import com.arkivanov.mvikotlin.core.store.Store
import com.mvikotlin.components.post_details.PostDetailsItem
import com.mvikotlin.components.post_details.store.PostDetailsStore.Intent
import com.mvikotlin.components.post_details.store.PostDetailsStore.State
import com.mvikotlin.components.post_details.store.PostDetailsStore.Label

internal interface PostDetailsStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Reload: Intent()
    }

    sealed class Label {

    }

    sealed class State {
        object Error : State()
        object Loading : State()
        data class Content(
            val post: PostDetailsItem = PostDetailsItem(),
        ) : State()
    }
}