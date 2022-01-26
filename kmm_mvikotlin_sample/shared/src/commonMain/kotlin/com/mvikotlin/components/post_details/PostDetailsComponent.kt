package com.mvikotlin.components.post_details

import com.arkivanov.decompose.value.Value

interface PostDetailsComponent {

    val model: Value<Model>

    fun onCloseClick()

    fun reload()

    sealed class Model {
        object Loading : Model()
        object Error : Model()
        data class Content(
            val title: String,
            val body: String,
        ) : Model()
    }

    sealed class Output {
        object Finished : Output()
    }
}