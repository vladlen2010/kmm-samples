package com.decompose.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.document_details.DocumentDetails

@Composable
fun DocumentDetailsUi(
    component: DocumentDetails,
    modifier: Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Document Details")
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

@Composable
@Preview
fun DocumentDetailsUiPreview() {
    DocumentDetailsUi(
        component = DocumentDetailsPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class DocumentDetailsPreview : DocumentDetails {
    override fun onBackPressed() {}
}