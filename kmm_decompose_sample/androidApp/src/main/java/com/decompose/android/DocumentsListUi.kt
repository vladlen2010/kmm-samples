package com.decompose.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.document_list.Document
import com.decompose.components.document_list.DocumentList

@Composable
fun DocumentsListUi(
    component: DocumentList,
    modifier: Modifier = Modifier,
) {
    val model by component.model.subscribeAsState()

    LazyColumn(
        modifier = modifier
    ) {
        items(model.documents) { document ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        component.onItemClicked(document.id)
                    }
            ) {
                Text(
                    text = document.title,
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DocumentsListUiPreview() {
    DocumentsListUi(
        component = DocumentListPreview(),
        modifier = Modifier
            .fillMaxSize()
    )
}

class DocumentListPreview() : DocumentList {
    override val model: Value<DocumentList.Model> =
        MutableValue(
            DocumentList.Model(
                List(25) {
                    Document(
                        id = "$it",
                        title = "Document $it"
                    )
                }
            )
        )

    override fun onItemClicked(itemId: String) {}
}
