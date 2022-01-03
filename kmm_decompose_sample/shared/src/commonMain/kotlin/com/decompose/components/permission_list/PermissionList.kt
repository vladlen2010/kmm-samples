package com.decompose.components.permission_list

import com.arkivanov.decompose.value.Value

interface PermissionList {

    val model: Value<Model>

    fun onPermissionClicked(permission: Permission)

    data class Model(
        val items: List<Permission>
    )
}

data class Permission( //TODO: transfer to domain layer
    val id: String,
    val title: String,
    val type: PermissionType
)
enum class PermissionType { //TODO: transfer to domain layer
    WEB, FRIENDS, WORK
}