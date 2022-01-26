package com.mvikotlin.components.main.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.mvikotlin.components.main.MainComponent
import com.mvikotlin.components.main.MainComponent.Output
import com.mvikotlin.components.main.MainComponent.Child
import com.mvikotlin.components.main.MainComponent.Model
import com.mvikotlin.components.main.MainComponent.Tab
import com.mvikotlin.components.post_root.PostRootComponent
import com.mvikotlin.components.post_root.integration.PostRootComponentImpl
import com.mvikotlin.components.profile.ProfileComponent
import com.mvikotlin.components.profile.ProfileComponent.Output.Edit
import com.mvikotlin.components.profile.integration.ProfileComponentImpl
import com.mvikotlin.repository.PostRepository
import com.mvikotlin.repository.ProfileRepository
import com.mvikotlin.utils.Consumer

class MainComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val postRoot: (ComponentContext) -> PostRootComponent,
    private val profile: (ComponentContext, Consumer<ProfileComponent.Output>) -> ProfileComponent,
    private val output: Consumer<Output>,
) : MainComponent, ComponentContext by componentContext {

    constructor(
        userId: Long,
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        postRepository: PostRepository,
        profileRepository: ProfileRepository,
        output: Consumer<Output>,
    ) : this(
        componentContext = componentContext,
        postRoot = { childContext ->
            PostRootComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                postRepository = postRepository,
            )
        },
        profile = { childContext, out ->
            ProfileComponentImpl(
                userId = userId,
                componentContext = childContext,
                storeFactory = storeFactory,
                profileRepository = profileRepository,
                output = out
            )
        },
        output = output
    )

    private val router: Router<Config, Child> = router(
        initialConfiguration = Config.Post,
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

    override fun onTabClicked(tab: Tab) {
        when (tab) {
            Tab.POST_ROOT -> router.bringToFront(Config.Post)
            Tab.PROFILE -> router.bringToFront(Config.Profile)
        }
    }

    private fun createChild(config: Config, componentContext: ComponentContext) =
        when (config) {
            Config.Post -> Child.PostRoot(postRoot(componentContext))
            Config.Profile -> Child.Profile(profile(componentContext, Consumer(::onMainOutput)))
        }

    private fun onMainOutput(out: ProfileComponent.Output): Unit =
        when (out) {
            is Edit -> output(Output.EditProfile(userId = out.userId))
        }


    private fun Config.toTab(): Tab =
        when (this) {
            Config.Post -> Tab.POST_ROOT
            Config.Profile -> Tab.PROFILE
        }

    sealed class Config : Parcelable {
        @Parcelize
        object Post : Config()

        @Parcelize
        object Profile : Config()
    }
}
