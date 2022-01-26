package com.mvikotlin.components.post_list

import com.arkivanov.decompose.value.Value

interface PostListComponent {

    val model: Value<Model>

    fun onItemClicked(id: Long)

    fun onRefresh(isPull: Boolean)


    sealed class Model {
        object Loading : Model()
        object Error : Model()
        data class Content(
            val items: List<PostListItem>,
            val isRefreshing: Boolean,
        ) : Model()
    }


    sealed class Output {
        data class ItemSelected(val id: Long) : Output()
    }
}