package com.decompose.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.decompose.components.friends_page.FriendsPage
import com.decompose.components.permission_list.PermissionList

@Composable
fun FriendsPageUi(
    component: FriendsPage,
    modifier: Modifier = Modifier
) {

    PermissionListUi(
        component = component.permissionList,
        modifier = modifier
    )

}

@Preview(showSystemUi = true)
@Composable
fun FriendsPageUiPreview() {
    FriendsPageUi(
        component = FriendsPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class FriendsPagePreview : FriendsPage {
    override val permissionList: PermissionList = PermissionListPreview()
}