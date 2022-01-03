package com.decompose.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.slide
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.permission_tabs.PermissionTabs

@ExperimentalDecomposeApi
@Composable
fun PermissionTabsUi(
    component: PermissionTabs,
    modifier: Modifier = Modifier
) {

    val model by component.model.subscribeAsState()

    Column(
        modifier = modifier
    ) {

        TabRow(
            selectedTabIndex = model.selectedTab.ordinal,
            modifier = Modifier.fillMaxWidth()
        ) {

            Tab(
                selected = model.selectedTab == PermissionTabs.Tab.WEB,
                onClick = {
                    component.onChangeTab(PermissionTabs.Tab.WEB)
                },
                text = {
                    Text(text = "Web")
                }
            )

            Tab(
                selected = model.selectedTab == PermissionTabs.Tab.FRIENDS,
                onClick = {
                    component.onChangeTab(PermissionTabs.Tab.FRIENDS)
                },
                text = {
                    Text(text = "Friends")
                }
            )

            Tab(
                selected = model.selectedTab == PermissionTabs.Tab.WORK,
                onClick = {
                    component.onChangeTab(PermissionTabs.Tab.WORK)
                },
                text = {
                    Text(text = "Work")
                }
            )

        }


        TabContent(
            component = component,
            modifier = Modifier.fillMaxSize()
        )
    }


}

@ExperimentalDecomposeApi
@Composable
private fun TabContent(
    component: PermissionTabs,
    modifier: Modifier = Modifier
) {
    Children(routerState = component.routerState) {
        when (val child = it.instance) {
            is PermissionTabs.Child.Web ->
                WebPageUi(
                    component = child.component,
                    modifier = modifier
                )
            is PermissionTabs.Child.Friends ->
                FriendsPageUi(
                    component = child.component,
                    modifier = modifier
                )
            is PermissionTabs.Child.Work ->
                WorkPageUi(
                    component = child.component,
                    modifier = modifier
                )
        }
    }
}

@ExperimentalDecomposeApi
@Preview(showSystemUi = true)
@Composable
fun PermissionTabsUiPreview() {
    PermissionTabsUi(
        component = PermissionTabsPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class PermissionTabsPreview : PermissionTabs {
    override val routerState: Value<RouterState<*, PermissionTabs.Child>> =
        MutableValue(
            RouterState(ConfigStub(), PermissionTabs.Child.Web(WebPagePreview()))
        )

    override val model: Value<PermissionTabs.Model> =
        MutableValue(
            PermissionTabs.Model(
                selectedTab = PermissionTabs.Tab.WEB
            )
        )

    override fun onChangeTab(tab: PermissionTabs.Tab) {}
    private class ConfigStub
}