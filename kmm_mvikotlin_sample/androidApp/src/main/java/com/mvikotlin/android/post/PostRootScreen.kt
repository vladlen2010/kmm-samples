package com.mvikotlin.android.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.mvikotlin.android.post.details.PostDetailsScreen
import com.mvikotlin.android.post.list.PostListPreview
import com.mvikotlin.android.post.list.PostListScreen
import com.mvikotlin.components.post_root.PostRootComponent

@ExperimentalDecomposeApi
@Composable
fun PostRootScreen(
    component: PostRootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        routerState = component.routerState
    ) {
        when(val child = it.instance) {
            is PostRootComponent.Child.PostList ->
                PostListScreen(
                    component = child.component,
                    modifier = modifier
                )
            is PostRootComponent.Child.PostDetails ->
                PostDetailsScreen(
                    component = child.component,
                    modifier = modifier
                )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun PostRootScreenPreview() {
    PostRootScreen(
        component = PostRootPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class PostRootPreview : PostRootComponent {
    override val routerState: Value<RouterState<*, PostRootComponent.Child>> =
        MutableValue(
            RouterState(
                configuration = ConfigStub(),
                instance = PostRootComponent.Child.PostList(
                    PostListPreview()
                )
            )
        )

    private class ConfigStub
}