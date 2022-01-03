package com.decompose.android

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.document_add.AddDocumentPage

@Composable
fun AddDocumentPageUi(
    component: AddDocumentPage,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Document")
                },

                navigationIcon = {
                    IconButton(
                        onClick = { component.onBackPressed() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier) {

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddDocumentUiPreview() {
    AddDocumentPageUi(
        component = AddDocumentPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class AddDocumentPagePreview : AddDocumentPage {
    override fun onBackPressed() {}
}