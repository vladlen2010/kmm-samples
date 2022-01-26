package com.mvikotlin.components.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.mvikotlin.components.edit.EditProfileComponent
import com.mvikotlin.components.edit.integration.EditProfileComponentImpl
import com.mvikotlin.components.main.MainComponent
import com.mvikotlin.components.main.integration.MainComponentImpl
import com.mvikotlin.components.root.RootComponent
import com.mvikotlin.components.root.RootComponent.Child
import com.mvikotlin.repository.PostRepository
import com.mvikotlin.repository.ProfileRepository
import com.mvikotlin.utils.Consumer


class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val main: (ComponentContext, Consumer<MainComponent.Output>) -> MainComponent,
    private val editProfile: (ComponentContext, Consumer<EditProfileComponent.Output>, Long) -> EditProfileComponent,
) : RootComponent, ComponentContext by componentContext {

    constructor(
        userId: Long,
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        postRepository: PostRepository,
        profileRepository: ProfileRepository,
    ) : this(
        componentContext = componentContext,
        main = { childContext, output ->
            MainComponentImpl(
                userId = userId,
                componentContext = childContext,
                storeFactory = storeFactory,
                postRepository = postRepository,
                profileRepository = profileRepository,
                output = output
            )
        },
        editProfile = { childContext, output, id ->
            EditProfileComponentImpl(
                userId = id, //TODO: use userId from constructor
                componentContext = childContext,
                storeFactory = storeFactory,
                profileRepository = profileRepository,
                output = output
            )
        }
    )

    private val router =
        router<Config, Child>(
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext) =
        when (config) {
            is Config.Main -> Child.Main(main(componentContext, Consumer(::onMainOutput)))
            is Config.EditProfile -> Child.EditProfile(
                editProfile(componentContext, Consumer(::onEditProfileOutput), config.userId)
            )
        }

    private fun onMainOutput(output: MainComponent.Output): Unit =
        when (output) {
            is MainComponent.Output.EditProfile -> router.push(Config.EditProfile(output.userId))
        }

    private fun onEditProfileOutput(output: EditProfileComponent.Output) {
        when (output) {
            is EditProfileComponent.Output.Finished -> router.pop()
        }
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object Main : Config()

        @Parcelize
        data class EditProfile(val userId: Long) : Config()
    }
}