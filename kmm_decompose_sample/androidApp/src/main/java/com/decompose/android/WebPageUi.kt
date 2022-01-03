package com.decompose.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.permission_list.PermissionList
import com.decompose.components.web_page.WebPage

@Composable
fun WebPageUi(
    component: WebPage,
    modifier: Modifier = Modifier
) {

    PermissionListUi(
        component = component.permissionList,
        modifier = modifier
    )

}

@Preview(showSystemUi = true)
@Composable
fun WebPageUiPreview() {
    WebPageUi(
        component = WebPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class WebPagePreview : WebPage {
    override val permissionList: PermissionList = PermissionListPreview()
}