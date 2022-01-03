package com.decompose.components.permission_list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.permission_list.PermissionList.*

class PermissionListComponent(
    componentContext: ComponentContext,
    permissionType: PermissionType,
    private val onPermissionClicked: (Permission) -> Unit
) : PermissionList, ComponentContext by componentContext {

    private val _model = MutableValue(
        Model(
            items = List(100) { index ->
                Permission(
                    id = "$index",
                    title = "${permissionType.name} $index",
                    type = permissionType
                )
            }
        )
    )

    override val model: Value<Model>  = _model

    override fun onPermissionClicked(permission: Permission) {
        onPermissionClicked.invoke(permission)
    }
}