package com.decompose.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.decompose.components.root.Root

@ExperimentalDecomposeApi
@Composable
fun RootUi(
    component: Root,
    modifier: Modifier = Modifier,
) {
    Children(
        routerState = component.routerState,
        animation = crossfade()
    ) {
        when (val child = it.instance) {
            is Root.Child.Main ->
                MainUi(
                    component = child.component,
                    modifier = modifier
                )
            is Root.Child.PermissionDetails ->
                PermissionDetailsUi(
                    component = child.component,
                    modifier = modifier
                )
            is Root.Child.DocumentDetails ->
                DocumentDetailsUi(
                    component = child.component,
                    modifier = modifier
                )
            is Root.Child.ApplicationDetails ->
                ApplicationDetailsUi(
                    component = child.component,
                    modifier = modifier
                )
            is Root.Child.AddDocument ->
                AddDocumentPageUi(
                    component = child.component,
                    modifier = modifier
                )
        }
    }

}