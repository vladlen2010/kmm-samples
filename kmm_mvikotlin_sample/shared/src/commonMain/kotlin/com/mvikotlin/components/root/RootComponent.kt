package com.mvikotlin.components.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelize
import com.mvikotlin.components.edit.EditProfileComponent
import com.mvikotlin.components.main.MainComponent

interface RootComponent {
    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Main(val component: MainComponent): Child()
        data class EditProfile(val component: EditProfileComponent) : Child()
    }
}