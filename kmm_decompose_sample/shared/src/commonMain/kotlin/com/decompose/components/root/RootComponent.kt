package com.decompose.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.decompose.components.application_details.ApplicationDetails
import com.decompose.components.application_details.ApplicationDetailsComponent
import com.decompose.components.document_add.AddDocumentPage
import com.decompose.components.document_add.AddDocumentPageComponent
import com.decompose.components.document_details.DocumentDetails
import com.decompose.components.document_details.DocumentDetailsComponent
import com.decompose.components.main.Main
import com.decompose.components.main.MainComponent
import com.decompose.components.permission_details.PermissionDetails
import com.decompose.components.permission_details.PermissionDetailsComponent
import com.decompose.components.root.Root.Child

class RootComponent(
    componentContext: ComponentContext,
) : Root, ComponentContext by componentContext {

    private val router =
        router<Config, Child>(
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.Main -> Child.Main(main(componentContext))
            Config.PermissionDetails -> Child.PermissionDetails(permissionDetails(componentContext))
            Config.DocumentDetails -> Child.DocumentDetails(documentDetails(componentContext))
            Config.ApplicationDetails -> Child.ApplicationDetails(applicationDetails(
                componentContext))
            Config.AddDocument -> Child.AddDocument(addDocument(componentContext))
        }

    private fun main(componentContext: ComponentContext): Main =
        MainComponent(
            componentContext = componentContext,
            onPermissionClicked = { router.push(Config.PermissionDetails) },
            onDocumentClicked = { router.push(Config.DocumentDetails) },
            onApplicationClicked = { router.push(Config.ApplicationDetails) },
            onAddDocumentClicked = { router.push(Config.AddDocument) }
        )

    private fun permissionDetails(componentContext: ComponentContext): PermissionDetails =
        PermissionDetailsComponent(
            componentContext = componentContext,
            onFinished = { router.pop() }
        )

    private fun documentDetails(componentContext: ComponentContext): DocumentDetails =
        DocumentDetailsComponent(
            componentContext = componentContext,
            onFinished = { router.pop() }
        )

    private fun applicationDetails(componentContext: ComponentContext): ApplicationDetails =
        ApplicationDetailsComponent(
            componentContext = componentContext,
            onFinished = { router.pop() }
        )

    private fun addDocument(componentContext: ComponentContext): AddDocumentPage =
        AddDocumentPageComponent(
            componentContext = componentContext,
            onFinished = { router.pop() }
        )

    private sealed class Config : Parcelable {
        @Parcelize
        object Main : Config()

        @Parcelize
        object PermissionDetails : Config()

        @Parcelize
        object DocumentDetails : Config()

        @Parcelize
        object ApplicationDetails : Config()

        @Parcelize
        object AddDocument : Config()
    }
}