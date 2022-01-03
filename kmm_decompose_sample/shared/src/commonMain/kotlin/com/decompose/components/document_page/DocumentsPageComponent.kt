package com.decompose.components.document_page

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.decompose.components.document_list.DocumentListComponent

class DocumentsPageComponent(
    componentContext: ComponentContext,
    onDocumentClick: (String) -> Unit
) : DocumentsPage, ComponentContext by componentContext {

    override val documentList =
        DocumentListComponent(
            componentContext = childContext(key = "document_list"),
            onItemClick = { documentId -> onDocumentClick(documentId) }
        )
}