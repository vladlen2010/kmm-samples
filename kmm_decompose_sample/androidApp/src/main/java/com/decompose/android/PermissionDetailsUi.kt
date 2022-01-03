package com.decompose.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.permission_details.PermissionDetails

@Composable
fun PermissionDetailsUi(
    component: PermissionDetails,
    modifier: Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Permission Details")
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
fun PermissionDetailsUiPreview() {
    PermissionDetailsUi(
        component = PermissionDetailsPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class PermissionDetailsPreview : PermissionDetails {
    override fun onBackPressed() {}
}