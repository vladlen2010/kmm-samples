package com.mvikotlin.android.profile.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.mvikotlin.android.common.TopBar
import com.mvikotlin.components.edit.EditProfileComponent
import com.mvikotlin.components.edit.EditProfileComponent.Model

@Composable
fun EditProfileScreen(
    component: EditProfileComponent,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = {
            TopBar(
                title = "Edit profile",
                onBackPressed = component::onCloseClick
            )
        },
        modifier = modifier
    ) {

    }
}

@Preview(showSystemUi = true)
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(
        component = EditProfilePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class EditProfilePreview : EditProfileComponent {
    override val model: Value<Model> =
        MutableValue(
            Model.Content(
                name = "Vladlen",
                username = "Vladlen2010"
            )
        )

    override fun onUsernameChanged(username: String) {}
    override fun onCloseClick() {}
}