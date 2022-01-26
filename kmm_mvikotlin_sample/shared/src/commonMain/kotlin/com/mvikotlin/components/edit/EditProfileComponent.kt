package com.mvikotlin.components.edit

import com.arkivanov.decompose.value.Value


interface EditProfileComponent {

    val model: Value<Model>

    fun onUsernameChanged(username: String)

    fun onCloseClick()

    sealed class Model {
        object Error : Model()
        object Initial : Model()
        data class Content(
            val name: String,
            val username: String,
        ) : Model()
    }

    sealed class Output {
        object Finished : Output()
    }
}