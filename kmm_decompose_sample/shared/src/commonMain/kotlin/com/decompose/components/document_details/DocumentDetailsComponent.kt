package com.decompose.components.document_details

import com.arkivanov.decompose.ComponentContext

class DocumentDetailsComponent(
    componentContext: ComponentContext,
    private val onFinished: () -> Unit
): DocumentDetails, ComponentContext by componentContext {
    override fun onBackPressed() {
        onFinished()
    }
}