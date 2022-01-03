package com.decompose.android

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AddDocumentButton(onAddDocumentClicked: () -> Unit) {
    Button(
        onClick = onAddDocumentClicked,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .defaultMinSize(
                minHeight = 48.dp
            )
    ) {
        Text(
            text = "AddDocument",
            textAlign = TextAlign.Center
        )
    }
}