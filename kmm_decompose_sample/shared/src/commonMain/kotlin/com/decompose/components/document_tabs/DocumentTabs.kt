package com.decompose.components.document_tabs

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.decompose.components.application_page.ApplicationPage
import com.decompose.components.document_page.DocumentsPage

interface DocumentTabs {

    val routerState: Value<RouterState<*, Child>>
    val model: Value<Model>

    fun onTabClick(tab: Tab)

    fun onAddDocumentClicked()

    data class Model(
        val selectedTab: Tab = Tab.DOCUMENTS_TAB
    )

    enum class Tab {
        DOCUMENTS_TAB,
        APPLICATION_TAB
    }

    sealed class Child {
        class Documents(val component: DocumentsPage) : Child()
        class Applications(val component: ApplicationPage) : Child()
    }
}