package com.decompose.android

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.document_tabs.DocumentTabs

@ExperimentalDecomposeApi
@Composable
fun DocumentTabsUi(
    component: DocumentTabs,
    modifier: Modifier = Modifier
) {
    val model by component.model.subscribeAsState()

    Column(
        modifier = modifier
    ) {

        TabRow(
            selectedTabIndex = model.selectedTab.ordinal,
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                selected = model.selectedTab == DocumentTabs.Tab.DOCUMENTS_TAB,
                onClick = {
                    component.onTabClick(DocumentTabs.Tab.DOCUMENTS_TAB)
                },
                text = {
                    Text(text = "Documents")
                }
            )
            Tab(
                selected = model.selectedTab == DocumentTabs.Tab.APPLICATION_TAB,
                onClick = {
                    component.onTabClick(DocumentTabs.Tab.APPLICATION_TAB)
                },
                text = {
                    Text(text = "Applications")
                }
            )
        }

        TabContent(
            component = component,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        AddDocumentButton { component.onAddDocumentClicked() }
    }
}

@ExperimentalDecomposeApi
@Composable
private fun TabContent(
    component: DocumentTabs,
    modifier: Modifier = Modifier
) {
    Children(
        routerState = component.routerState
    ) {
        when (val child = it.instance) {
            is DocumentTabs.Child.Documents ->
                DocumentsPageUi(
                    component = child.component,
                    modifier = modifier
                )
            is DocumentTabs.Child.Applications ->
                ApplicationPageUi(
                    component = child.component,
                    modifier = modifier
                )
        }
    }
}

@ExperimentalDecomposeApi
@Preview(showSystemUi = true)
@Composable
private fun DocumentTabsUiPreview() {
    DocumentTabsUi(
        component = DocumentTabsPreview(),
        modifier = Modifier
            .fillMaxSize()
    )
}

class DocumentTabsPreview : DocumentTabs {

    override val routerState: Value<RouterState<*, DocumentTabs.Child>> =
        MutableValue(
            RouterState(
                configuration = ConfigStub(),
                instance = DocumentTabs.Child.Documents(
                    component = DocumentPagePreview()
                )
            )
        )

    override val model: Value<DocumentTabs.Model> =
        MutableValue(
            DocumentTabs.Model(
                selectedTab = DocumentTabs.Tab.DOCUMENTS_TAB
            )
        )

    override fun onTabClick(tab: DocumentTabs.Tab) {}
    override fun onAddDocumentClicked() {}

    private class ConfigStub
}

