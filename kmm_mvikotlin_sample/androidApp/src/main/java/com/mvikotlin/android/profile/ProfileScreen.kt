package com.mvikotlin.android.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.mvikotlin.android.common.ErrorLayout
import com.mvikotlin.android.common.Loader
import com.mvikotlin.components.profile.ProfileComponent
import com.mvikotlin.components.profile.ProfileComponent.Model

@Composable
fun ProfileScreen(
    component: ProfileComponent,
    modifier: Modifier = Modifier,
) {

    val model by component.model.subscribeAsState()

    when (val m = model) {
        Model.Loading -> Loader()
        Model.Error -> ErrorLayout(text = "error", onClick = component::onReload)
        is Model.Content ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Profile")
                        },
                        actions = {
                            IconButton(
                                onClick = component::onEditClicked
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "edit"
                                )
                            }
                        }
                    )
                },
                modifier = modifier
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "name:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = m.name)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "username:")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = m.username)
                    }
                }
            }
    }


}

@Preview(showSystemUi = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        component = ProfilePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class ProfilePreview : ProfileComponent {
    override val model: Value<Model> =
        MutableValue(
            Model.Content(
                name = "Vladlen",
                username = "Vladlen2010",
            )
        )

    override fun onEditClicked() {}
    override fun onReload() {}
}