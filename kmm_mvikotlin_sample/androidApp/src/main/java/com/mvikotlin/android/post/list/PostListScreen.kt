package com.mvikotlin.android.post.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.mvikotlin.android.common.ErrorLayout
import com.mvikotlin.android.common.Loader
import com.mvikotlin.components.post_list.PostListComponent
import com.mvikotlin.components.post_list.PostListComponent.Model
import com.mvikotlin.components.post_list.PostListItem

@Composable
fun PostListScreen(
    component: PostListComponent,
    modifier: Modifier = Modifier,
) {

    val model by component.model.subscribeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Posts")
                }
            )
        },
        modifier = modifier
    ) {
        when (val m = model) {
            Model.Loading -> Loader()
            Model.Error ->
                ErrorLayout(
                    text = "Error",
                    onClick = { component.onRefresh(false) }
                )
            is Model.Content ->
                PostList(
                    items = m.items,
                    isRefreshing = m.isRefreshing,
                    onRefresh = component::onRefresh,
                    onItemClicked = component::onItemClicked
                )
        }

    }

}

@Preview(showSystemUi = true)
@Composable
private fun PostListContentPreview() {
    PostListScreen(
        component = PostListPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PostListErrorPreview() {
    PostListScreen(
        component = PostListPreview(Model.Error),
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PostListLoadingPreview() {
    PostListScreen(
        component = PostListPreview(Model.Loading),
        modifier = Modifier.fillMaxSize()
    )
}

class PostListPreview(model: Model? = null) : PostListComponent {
    private val _model = model
        ?: Model.Content(
            items = List(15) {
                PostListItem(
                    id = it.toLong(),
                    title = "Post $it"
                )
            },
            isRefreshing = false
        )

    override val model: Value<Model> = MutableValue(_model)
    override fun onItemClicked(id: Long) {}
    override fun onRefresh(isPull: Boolean) {}
}