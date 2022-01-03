package com.decompose.components.document_tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.decompose.components.application_page.ApplicationPage
import com.decompose.components.application_page.ApplicationPageComponent
import com.decompose.components.document_tabs.DocumentTabs.*
import com.decompose.components.document_page.DocumentsPageComponent
import com.decompose.components.document_page.DocumentsPage

class DocumentTabsComponent(
    componentContext: ComponentContext,
    private val onApplicationClicked: (String) -> Unit,
    private val onDocumentClicked: (String) -> Unit,
    private val onAddDocument: () -> Unit,
) : DocumentTabs, ComponentContext by componentContext {

    private val router =
        router<Config, Child>(
            initialConfiguration = Config.Documents,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    override val model: Value<Model> =
        router.state.map { state ->
            Model(
                selectedTab = state.activeChild.configuration.toTab()
            )
        }

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Documents -> Child.Documents(documents(componentContext))
            is Config.Applications -> Child.Applications(applications(componentContext))
        }

    private fun documents(componentContext: ComponentContext): DocumentsPage =
        DocumentsPageComponent(
            componentContext = componentContext,
            onDocumentClick = { documentId -> onDocumentClicked(documentId) },
        )


    private fun applications(componentContext: ComponentContext): ApplicationPage =
        ApplicationPageComponent(
            componentContext = componentContext,
            onApplicationClick = { applicationId -> onApplicationClicked(applicationId) },
        )

    override fun onTabClick(tab: Tab) =
        when (tab) {
            Tab.DOCUMENTS_TAB -> router.bringToFront(Config.Documents)
            Tab.APPLICATION_TAB -> router.bringToFront(Config.Applications)
        }

    override fun onAddDocumentClicked() {
        onAddDocument()
    }

    private fun Config.toTab(): Tab =
        when (this) {
            is Config.Documents -> Tab.DOCUMENTS_TAB
            is Config.Applications -> Tab.APPLICATION_TAB
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object Documents : Config()

        @Parcelize
        object Applications : Config()
    }
}
