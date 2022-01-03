package com.decompose.components.document_list

import com.arkivanov.decompose.value.Value

interface DocumentList {

    val model: Value<Model>

    fun onItemClicked(itemId: String)

    data class Model(
        val documents: List<Document> = emptyList()
    )

}

data class Document( //TODO: transfer to domain layer
    val id: String,
    val title: String
)