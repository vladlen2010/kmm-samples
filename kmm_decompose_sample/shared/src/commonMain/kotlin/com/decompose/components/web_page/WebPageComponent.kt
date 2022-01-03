package com.decompose.components.web_page

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.decompose.components.permission_list.Permission
import com.decompose.components.permission_list.PermissionList
import com.decompose.components.permission_list.PermissionListComponent
import com.decompose.components.permission_list.PermissionType

class WebPageComponent(
    componentContext: ComponentContext,
    onPermissionClicked: (Permission) -> Unit
) : WebPage, ComponentContext by componentContext {

    override val permissionList: PermissionList =
        PermissionListComponent(
            componentContext = childContext("web_permission"),
            permissionType = PermissionType.WEB,
            onPermissionClicked = onPermissionClicked
        )
}