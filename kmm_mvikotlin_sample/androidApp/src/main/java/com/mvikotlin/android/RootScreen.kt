package com.mvikotlin.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.fade
import com.mvikotlin.android.profile.edit.EditProfileScreen
import com.mvikotlin.components.root.RootComponent
import com.mvikotlin.components.root.RootComponent.Child

@ExperimentalDecomposeApi
@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    Children(
        routerState = component.routerState,
        animation = childAnimation(fade())
    ) {
        when(val child = it.instance) {
            is Child.Main ->
                MainScreen(
                    component = child.component,
                    modifier = modifier
                )
            is Child.EditProfile ->
                EditProfileScreen(
                    component = child.component,
                    modifier = modifier
                )
        }
    }
}