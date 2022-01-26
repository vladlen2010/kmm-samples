package com.mvikotlin.components.post_root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.mvikotlin.components.post_details.PostDetailsComponent
import com.mvikotlin.components.post_details.PostDetailsComponent.Output.Finished
import com.mvikotlin.components.post_details.integration.PostDetailsComponentImpl
import com.mvikotlin.components.post_list.PostListComponent
import com.mvikotlin.components.post_list.PostListComponent.Output.ItemSelected
import com.mvikotlin.components.post_list.integration.PostListComponentImpl
import com.mvikotlin.components.post_root.PostRootComponent
import com.mvikotlin.components.post_root.PostRootComponent.Child
import com.mvikotlin.repository.PostRepository
import com.mvikotlin.utils.Consumer

class PostRootComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val postList: (ComponentContext, Consumer<PostListComponent.Output>) -> PostListComponent,
    private val postDetails: (ComponentContext, Consumer<PostDetailsComponent.Output>, Long) -> PostDetailsComponent,
) : PostRootComponent, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        postRepository: PostRepository,
    ) : this(
        componentContext = componentContext,
        postList = { childContext, output ->
            PostListComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                postRepository = postRepository,
                output = output
            )
        },
        postDetails =  { childContext, output, postId ->
            PostDetailsComponentImpl(
                postId = postId,
                componentContext = childContext,
                storeFactory = storeFactory,
                postRepository = postRepository,
                output = output
            )
        }
    )


    private val router: Router<Config, Child> = router(
        initialConfiguration = Config.PostList,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext) =
        when (config) {
            is Config.PostDetails -> Child.PostDetails(
                postDetails(componentContext, Consumer(::onPostDetailsOutput), config.postId)
            )
            Config.PostList -> Child.PostList(
                postList(componentContext, Consumer(::onPostListOutput))
            )
        }

    private fun onPostDetailsOutput(output: PostDetailsComponent.Output) {
        when (output) {
            Finished -> router.pop()
        }
    }

    private fun onPostListOutput(output: PostListComponent.Output) {
        when (output) {
            is ItemSelected -> router.push(Config.PostDetails(output.id))
        }
    }

    sealed class Config : Parcelable {
        @Parcelize
        object PostList : Config()

        @Parcelize
        data class PostDetails(val postId: Long) : Config()
    }
}