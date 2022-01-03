package com.decompose.components.document_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class DocumentListComponent(
    componentContext: ComponentContext,
    private val onItemClick: (String) -> Unit
) : DocumentList, ComponentContext by componentContext {

    private val _model = MutableValue(
        DocumentList.Model(
            documents = List(100) { index ->
                Document(
                    id = "$index",
                    title = "Item $index"
                )
            }
        )
    )
    override val model: Value<DocumentList.Model> = _model

    override fun onItemClicked(itemId: String) {
        onItemClick(itemId)
    }

}