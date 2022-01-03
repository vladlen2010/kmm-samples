package com.decompose.components.permission_details

import com.arkivanov.decompose.ComponentContext

class PermissionDetailsComponent(
    componentContext: ComponentContext,
    private val onFinished: () -> Unit
) : PermissionDetails, ComponentContext by componentContext {

    override fun onBackPressed() {
        onFinished()
    }
}