package com.decompose.components.document_add

import com.arkivanov.decompose.ComponentContext

class AddDocumentPageComponent(
    componentContext: ComponentContext,
    private val onFinished: () -> Unit
) : AddDocumentPage, ComponentContext by componentContext {

    override fun onBackPressed() {
        onFinished()
    }

}