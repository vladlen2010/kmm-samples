package com.decompose.components.permission_tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.decompose.components.friends_page.FriendsPage
import com.decompose.components.friends_page.FriendsPageComponent
import com.decompose.components.permission_list.Permission
import com.decompose.components.permission_tabs.PermissionTabs.*
import com.decompose.components.web_page.WebPage
import com.decompose.components.web_page.WebPageComponent
import com.decompose.components.work_page.WorkPage
import com.decompose.components.work_page.WorkPageComponent

class PermissionTabsComponent(
    componentContext: ComponentContext,
    private val onPermissionClicked: (Permission) -> Unit
) : PermissionTabs, ComponentContext by componentContext {

    private val router =
        router<Config, Child>(
            initialConfiguration = Config.Web,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state
    override val model: Value<Model> = router.state.map { state ->
        Model(
            selectedTab = state.activeChild.configuration.toTab()
        )
    }

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Web -> Child.Web(web(componentContext))
            is Config.Friends -> Child.Friends(friends(componentContext))
            is Config.Work -> Child.Work(work(componentContext))
        }

    private fun web(componentContext: ComponentContext): WebPage =
        WebPageComponent(
            componentContext = componentContext,
            onPermissionClicked = onPermissionClicked
        )

    private fun friends(componentContext: ComponentContext): FriendsPage =
        FriendsPageComponent(
            componentContext = componentContext,
            onPermissionClicked = onPermissionClicked
        )

    private fun work(componentContext: ComponentContext): WorkPage =
        WorkPageComponent(
            componentContext = componentContext,
            onPermissionClicked = onPermissionClicked
        )

    override fun onChangeTab(tab: Tab) =
        when (tab) {
            Tab.WEB -> router.bringToFront(Config.Web)
            Tab.FRIENDS -> router.bringToFront(Config.Friends)
            Tab.WORK -> router.bringToFront(Config.Work)
        }

    private fun Config.toTab(): Tab =
        when (this) {
            Config.Web -> Tab.WEB
            Config.Friends -> Tab.FRIENDS
            Config.Work -> Tab.WORK
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object Web : Config()

        @Parcelize
        object Friends : Config()

        @Parcelize
        object Work : Config()
    }
}