package com.decompose.components.application_details

import com.arkivanov.decompose.ComponentContext

class ApplicationDetailsComponent(
    componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : ApplicationDetails, ComponentContext by componentContext {

    override fun onBackPressed() {
        onFinished()
    }
}