package com.decompose.android

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.main.Main

@ExperimentalDecomposeApi
@Composable
fun MainUi(
    component: Main,
    modifier: Modifier = Modifier
) {

    val model by component.model.subscribeAsState()

    Scaffold(
        modifier = modifier,

        topBar = {
            TopBar(
                selectedTab = model.selectedTab,
            )
        },

        bottomBar = {
            BottomNavigationBar(
                selectedTab = model.selectedTab,
                onTabClicked = component::onTabClick
            )
        }
    ) {

        TabContent(
            component = component,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        )

    }
}

@ExperimentalDecomposeApi
@Composable
private fun TabContent(
    component: Main,
    modifier: Modifier = Modifier
) {
    Children(
        routerState = component.routerState
    ) {
        when (val child = it.instance) {
            is Main.Child.Permissions ->
                PermissionTabsUi(
                    component = child.component,
                    modifier = modifier
                )

            is Main.Child.Documents ->
                DocumentTabsUi(
                    component = child.component,
                    modifier = modifier
                )

            is Main.Child.Wallet ->
                WalletPageUi(
                    component = child.component,
                    modifier = modifier
                )
        }
    }
}

@Composable
private fun TopBar(
    selectedTab: Main.Tab
) {
    when (selectedTab) {
        Main.Tab.PERMISSION_TABS ->
            MainTopBar(
                title = "Registrations",
                onProfileClicked = {},
                onSearchClicked = {},
                onQrCodeClicked = {}
            )

        Main.Tab.DOCUMENT_TABS ->
            MainTopBar(
                title = "My Documents",
                onProfileClicked = {},
                onSearchClicked = {},
                onQrCodeClicked = {}
            )

        Main.Tab.WALLET ->
            WalletTopBar(
                title = "Balance",
                onWalletsClick = {}
            )
    }
}

@Composable
fun MainTopBar(
    title: String,
    onProfileClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onQrCodeClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },

        navigationIcon = {
            IconButton(
                onClick = { onProfileClicked() }
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "profile"
                )
            }
        },

        actions = {
            Row {

                IconButton(
                    onClick = { onSearchClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                }

                IconButton(
                    onClick = { onQrCodeClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings, //TODO: change icon to material qr code 2
                        contentDescription = "qrcode"
                    )
                }

            }
        }
    )
}

@Composable
fun WalletTopBar(
    title: String,
    onWalletsClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },

        actions = {
            IconButton(
                onClick = { onWalletsClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite, //TODO: change icon to material account balance wallet
                    contentDescription = "custom wallets"
                )
            }
        }
    )


}

@Composable
fun BottomNavigationBar(
    selectedTab: Main.Tab,
    onTabClicked: (Main.Tab) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = selectedTab == Main.Tab.PERMISSION_TABS,
            onClick = { onTabClicked(Main.Tab.PERMISSION_TABS) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "permissions"
                )
            },
            label = {
                Text(text = "Permissions")
            }
        )
        BottomNavigationItem(
            selected = selectedTab == Main.Tab.DOCUMENT_TABS,
            onClick = { onTabClicked(Main.Tab.DOCUMENT_TABS) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit, //TODO: change icon to material documents
                    contentDescription = "documents"
                )
            },
            label = {
                Text(text = "Documents")
            }
        )
        BottomNavigationItem(
            selected = selectedTab == Main.Tab.WALLET,
            onClick = { onTabClicked(Main.Tab.WALLET) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite, //TODO: change icon to material account balance wallet
                    contentDescription = "wallet"
                )
            },
            label = {
                Text(text = "Balance")
            }
        )
    }
}

@ExperimentalDecomposeApi
@Preview(showSystemUi = true)
@Composable
fun MainUiPreview() {
    MainUi(
        component = MainPreview(),
        modifier = Modifier.fillMaxWidth()
    )
}

class MainPreview : Main {
    override val routerState: Value<RouterState<*, Main.Child>> =
        MutableValue(
            RouterState(
                configuration = ConfigStub(),
                instance = Main.Child.Permissions(
                    PermissionTabsPreview()
                )
            )
        )

    override val model: Value<Main.Model> =
        MutableValue(
            Main.Model(
                selectedTab = Main.Tab.PERMISSION_TABS
            )
        )

    override fun onTabClick(tab: Main.Tab) {}

    private class ConfigStub
}