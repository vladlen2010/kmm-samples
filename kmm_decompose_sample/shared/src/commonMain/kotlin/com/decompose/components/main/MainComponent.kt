package com.decompose.components.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.decompose.components.document_tabs.DocumentTabsComponent
import com.decompose.components.document_tabs.DocumentTabs
import com.decompose.components.main.Main.*
import com.decompose.components.permission_list.Permission
import com.decompose.components.permission_tabs.PermissionTabs
import com.decompose.components.permission_tabs.PermissionTabsComponent
import com.decompose.components.wallet_page.WalletPage
import com.decompose.components.wallet_page.WalletPageComponent

class MainComponent(
    componentContext: ComponentContext,
    private val onPermissionClicked: (Permission) -> Unit,
    private val onApplicationClicked: (String) -> Unit,
    private val onDocumentClicked: (String) -> Unit,
    private val onAddDocumentClicked: () -> Unit,
) : Main, ComponentContext by componentContext {

    private val router =
        router<Config, Child>(
            initialConfiguration = Config.PermissionTabs,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    override val model: Value<Model> =
        router.state.map { state ->
            Model(
                selectedTab = state.activeChild.configuration.toTab()
            )
        }


    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.PermissionTabs -> Child.Permissions(
                permissionPage(componentContext)
            )
            is Config.DocumentTabs -> Child.Documents(
                documentPage(componentContext)
            )
            is Config.Wallet -> Child.Wallet(
                walletPage()
            )
        }

    private fun permissionPage(componentContext: ComponentContext): PermissionTabs =
        PermissionTabsComponent(
            componentContext = componentContext,
            onPermissionClicked = onPermissionClicked
        )

    private fun documentPage(componentContext: ComponentContext): DocumentTabs =
        DocumentTabsComponent(
            componentContext = componentContext,
            onApplicationClicked = onApplicationClicked,
            onDocumentClicked = onDocumentClicked,
            onAddDocument = onAddDocumentClicked
        )

    private fun walletPage(): WalletPage = WalletPageComponent()


    override fun onTabClick(tab: Tab) =
        when (tab) {
            Tab.PERMISSION_TABS -> router.bringToFront(Config.PermissionTabs)
            Tab.DOCUMENT_TABS -> router.bringToFront(Config.DocumentTabs)
            Tab.WALLET -> router.bringToFront(Config.Wallet)
        }

    private fun Config.toTab(): Tab =
        when (this) {
            Config.PermissionTabs -> Tab.PERMISSION_TABS
            Config.DocumentTabs -> Tab.DOCUMENT_TABS
            Config.Wallet -> Tab.WALLET
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object PermissionTabs : Config()

        @Parcelize
        object DocumentTabs : Config()

        @Parcelize
        object Wallet : Config()
    }
}