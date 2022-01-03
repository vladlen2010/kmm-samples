package com.decompose.components.main

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.decompose.components.wallet_page.WalletPage
import com.decompose.components.document_tabs.DocumentTabs
import com.decompose.components.permission_tabs.PermissionTabs

interface Main {

    val routerState: Value<RouterState<*, Child>>
    val model: Value<Model>

    fun onTabClick(tab: Tab)

    data class Model(
        val selectedTab: Tab = Tab.PERMISSION_TABS
    )

    enum class Tab {
        PERMISSION_TABS,
        DOCUMENT_TABS,
        WALLET
    }

    sealed class Child {
        class Permissions(val component: PermissionTabs) : Child()
        class Documents(val component: DocumentTabs): Child()
        class Wallet(val component: WalletPage): Child()
    }
}