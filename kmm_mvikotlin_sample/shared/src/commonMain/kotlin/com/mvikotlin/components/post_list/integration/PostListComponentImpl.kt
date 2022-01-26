package com.mvikotlin.components.post_list.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.mvikotlin.components.post_list.PostListComponent
import com.mvikotlin.components.post_list.PostListComponent.*
import com.mvikotlin.components.post_list.store.PostListStore.Intent
import com.mvikotlin.components.post_list.store.PostListStoreImpl
import com.mvikotlin.repository.PostRepository
import com.mvikotlin.utils.asValue
import com.mvikotlin.utils.getStore

class PostListComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    postRepository: PostRepository,
    private val output: Consumer<Output>,
) : PostListComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        PostListStoreImpl(
            storeFactory = storeFactory,
            postRepository = postRepository
        ).create()
    }

    override val model: Value<Model> = store.asValue().map(stateToModel)

    override fun onItemClicked(id: Long) {
        output(Output.ItemSelected(id = id))
    }

    override fun onRefresh(isPull: Boolean) {
        store.accept(Intent.Refresh(keepContent = isPull))
    }
}