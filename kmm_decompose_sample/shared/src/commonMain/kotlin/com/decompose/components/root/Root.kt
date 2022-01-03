package com.decompose.components.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

interface Root {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child{
        class Main(val component: com.decompose.components.main.Main) : Child()
        class PermissionDetails(val component: com.decompose.components.permission_details.PermissionDetails) : Child()
        class DocumentDetails(val component: com.decompose.components.document_details.DocumentDetails): Child()
        class ApplicationDetails(val component: com.decompose.components.application_details.ApplicationDetails): Child()
        class AddDocument(val component: com.decompose.components.document_add.AddDocumentPage): Child()
    }
}