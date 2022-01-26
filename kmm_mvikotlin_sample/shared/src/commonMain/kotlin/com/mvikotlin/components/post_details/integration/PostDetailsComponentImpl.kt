package com.mvikotlin.components.post_details.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.mvikotlin.components.post_details.PostDetailsComponent
import com.mvikotlin.components.post_details.PostDetailsComponent.Model
import com.mvikotlin.components.post_details.PostDetailsComponent.Output
import com.mvikotlin.components.post_details.store.PostDetailsStore
import com.mvikotlin.components.post_details.store.PostDetailsStore.Intent
import com.mvikotlin.components.post_details.store.PostDetailsStoreImpl
import com.mvikotlin.repository.PostRepository
import com.mvikotlin.utils.asValue
import com.mvikotlin.utils.getStore

class PostDetailsComponentImpl(
    postId: Long,
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    postRepository: PostRepository,
    private val output: Consumer<Output>,
) : PostDetailsComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        PostDetailsStoreImpl(
            postId = postId,
            storeFactory = storeFactory,
            postRepository = postRepository
        ).create()
    }

    override val model: Value<Model> = store.asValue().map(stateToModel)

    override fun onCloseClick() {
        output(Output.Finished)
    }

    override fun reload() {
        store.accept(Intent.Reload)
    }
}