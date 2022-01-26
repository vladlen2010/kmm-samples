package com.mvikotlin.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.mvikotlin.android.post.PostRootPreview
import com.mvikotlin.android.post.PostRootScreen
import com.mvikotlin.android.profile.ProfileScreen
import com.mvikotlin.components.main.MainComponent
import com.mvikotlin.components.main.MainComponent.Tab
import com.mvikotlin.components.main.MainComponent.Child
import com.mvikotlin.components.main.MainComponent.Model

@Composable
fun MainScreen(
    component: MainComponent,
    modifier: Modifier = Modifier,
) {

    val model by component.model.subscribeAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                selectedTab = model.selectedTab,
                onTabClicked = component::onTabClicked
            )
        }
    ) {
        TabContent(
            component = component,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        )
    }
}


@Composable
fun TabContent(
    component: MainComponent,
    modifier: Modifier = Modifier,
) {
    Children(routerState = component.routerState) {
        when (val child = it.instance) {
            is Child.PostRoot ->
                PostRootScreen(
                    component = child.component,
                    modifier = modifier
                )
            is Child.Profile -> {
                ProfileScreen(
                    component = child.component,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedTab: Tab,
    onTabClicked: (Tab) -> Unit,
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = selectedTab == Tab.POST_ROOT,
            onClick = { onTabClicked(Tab.POST_ROOT) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "posts"
                )
            },
            label = {
                Text("Posts")
            }
        )
        BottomNavigationItem(
            selected = selectedTab == Tab.PROFILE,
            onClick = { onTabClicked(Tab.PROFILE) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "profile"
                )
            },
            label = {
                Text("Profile")
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    MainScreen(
        component = MainPreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class MainPreview : MainComponent {
    override val routerState: Value<RouterState<*, Child>> =
        MutableValue(
            RouterState(
                configuration = ConfigStub(),
                instance = Child.PostRoot(
                    PostRootPreview()
                )
            )
        )
    override val model: Value<Model> =
        MutableValue(
            Model(
                selectedTab = Tab.POST_ROOT
            )
        )

    override fun onTabClicked(tab: Tab) {}

    private class ConfigStub
}