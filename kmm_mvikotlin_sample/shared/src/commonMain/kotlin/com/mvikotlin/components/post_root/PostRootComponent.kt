package com.mvikotlin.components.post_root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.mvikotlin.components.post_details.PostDetailsComponent
import com.mvikotlin.components.post_list.PostListComponent

interface PostRootComponent {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child{
        data class PostList(val component: PostListComponent): Child()
        data class PostDetails(val component: PostDetailsComponent): Child()
    }
}