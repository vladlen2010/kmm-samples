package com.mvikotlin.components.main

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.mvikotlin.components.post_root.PostRootComponent
import com.mvikotlin.components.profile.ProfileComponent

interface MainComponent {

    val routerState: Value<RouterState<*, Child>>
    val model: Value<Model>

    data class Model(
        val selectedTab: Tab = Tab.POST_ROOT
    )

    fun onTabClicked(tab: Tab)

    enum class Tab {
        POST_ROOT, PROFILE
    }

    sealed class Child {
        data class PostRoot(val component: PostRootComponent): Child()
        data class Profile(val component: ProfileComponent) : Child()
    }

    sealed class Output {
        data class EditProfile(val userId: Long): Output()
    }
}