package com.decompose.components.friends_page

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.decompose.components.permission_list.Permission
import com.decompose.components.permission_list.PermissionList
import com.decompose.components.permission_list.PermissionListComponent
import com.decompose.components.permission_list.PermissionType

class FriendsPageComponent(
    componentContext: ComponentContext,
    onPermissionClicked: (Permission) -> Unit
) : FriendsPage, ComponentContext by componentContext {

    override val permissionList: PermissionList =
        PermissionListComponent(
            componentContext = childContext("friends_permission"),
            permissionType = PermissionType.FRIENDS,
            onPermissionClicked = onPermissionClicked
        )
}