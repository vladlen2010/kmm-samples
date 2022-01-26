package com.mvikotlin.android.post.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.mvikotlin.android.common.ErrorLayout
import com.mvikotlin.android.common.Loader
import com.mvikotlin.android.common.TopBar
import com.mvikotlin.components.post_details.PostDetailsComponent
import com.mvikotlin.components.post_details.PostDetailsComponent.Model

@Composable
fun PostDetailsScreen(
    component: PostDetailsComponent,
    modifier: Modifier = Modifier,
) {

    val model by component.model.subscribeAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Post details",
                onBackPressed = component::onCloseClick
            )
        },
        modifier = modifier
    ) {

        when (val m = model) {
            Model.Error -> ErrorLayout(text = "error", onClick = component::reload)
            Model.Loading -> Loader()
            is Model.Content -> Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = m.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 22.sp
                )

                Text(
                    text = m.body,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PostDetailsScreenPreview() {
    PostDetailsScreen(
        component = PostDetailsPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class PostDetailsPreview : PostDetailsComponent {
    override val model: Value<Model> =
        MutableValue(
            Model.Content(
                title = "Post #1",
                body = LoremIpsum(25).values.first().toString()
            )
        )

    override fun onCloseClick() {}
    override fun reload() {}
}