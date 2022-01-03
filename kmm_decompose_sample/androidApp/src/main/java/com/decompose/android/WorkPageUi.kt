package com.decompose.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.permission_list.PermissionList
import com.decompose.components.work_page.WorkPage

@Composable
fun WorkPageUi(
    component: WorkPage,
    modifier: Modifier = Modifier
) {

    PermissionListUi(
        component = component.permissionList,
        modifier = modifier
    )

}

@Preview(showSystemUi = true)
@Composable
fun WorkPageUiPreview() {
    WorkPageUi(
        component = WorkPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class WorkPagePreview : WorkPage {
    override val permissionList: PermissionList = PermissionListPreview()
}