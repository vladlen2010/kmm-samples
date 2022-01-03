package com.decompose.components.work_page

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.decompose.components.permission_list.Permission
import com.decompose.components.permission_list.PermissionList
import com.decompose.components.permission_list.PermissionListComponent
import com.decompose.components.permission_list.PermissionType

class WorkPageComponent(
    componentContext: ComponentContext,
    onPermissionClicked: (Permission) -> Unit
) : WorkPage, ComponentContext by componentContext {

    override val permissionList: PermissionList =
        PermissionListComponent(
            componentContext = childContext("work_permission"),
            permissionType = PermissionType.WORK,
            onPermissionClicked = onPermissionClicked
        )
}