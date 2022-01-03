package com.decompose.components.permission_tabs

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.decompose.components.friends_page.FriendsPage
import com.decompose.components.web_page.WebPage
import com.decompose.components.work_page.WorkPage

interface PermissionTabs {

    val routerState: Value<RouterState<*, Child>>
    val model: Value<Model>

    fun onChangeTab(tab: Tab)

    enum class Tab {
        WEB, FRIENDS, WORK
    }

    data class Model(
        val selectedTab: Tab = Tab.WEB
    )

    sealed class Child {
        class Web(val component: WebPage) : Child()
        class Friends(val component: FriendsPage) : Child()
        class Work(val component: WorkPage) : Child()
    }
}