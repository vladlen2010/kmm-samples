package com.decompose.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.permission_list.Permission
import com.decompose.components.permission_list.PermissionList
import com.decompose.components.permission_list.PermissionType

@Composable
fun PermissionListUi(
    component: PermissionList,
    modifier: Modifier = Modifier
) {
    val model by component.model.subscribeAsState()

    LazyColumn(
        modifier = modifier
    ) {
        items(model.items) { permission ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        component.onPermissionClicked(permission)
                    }
            ) {
                Text(
                    text = permission.title,
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
fun PermissionListUiPreview() {
    PermissionListUi(
        component = PermissionListPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class PermissionListPreview : PermissionList {
    override val model: Value<PermissionList.Model> =
        MutableValue(
            PermissionList.Model(
                items = List(25) {
                    Permission(
                        id = "$it",
                        title = "Permission $it",
                        type = PermissionType.WEB
                    )
                }
            )
        )

    override fun onPermissionClicked(permission: Permission) {}
}
