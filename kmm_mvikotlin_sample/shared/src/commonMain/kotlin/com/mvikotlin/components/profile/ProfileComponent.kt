package com.mvikotlin.components.profile

import com.arkivanov.decompose.value.Value

interface ProfileComponent {

    val model: Value<Model>

    fun onEditClicked()

    fun onReload()

    sealed class Model {
        object Error : Model()
        object Loading : Model()
        data class Content(
            val name: String,
            val username: String,
        ) : Model()
    }

//    sealed class Input {
//        data class ItemChanged(val userId: Long, val data: ProfileItem)
//    }

    sealed class Output {
        data class Edit(val userId: Long) : Output()
    }
}