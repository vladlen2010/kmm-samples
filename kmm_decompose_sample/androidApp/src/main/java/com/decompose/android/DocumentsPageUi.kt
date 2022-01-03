package com.decompose.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.document_list.DocumentList
import com.decompose.components.document_page.DocumentsPage

@Composable
fun DocumentsPageUi(
    component: DocumentsPage,
    modifier: Modifier = Modifier,
) {
    DocumentsListUi(
        component = component.documentList,
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun DocumentPageUiPreview() {
    DocumentsPageUi(
        component = DocumentPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class DocumentPagePreview : DocumentsPage {
    override val documentList: DocumentList = DocumentListPreview()
}