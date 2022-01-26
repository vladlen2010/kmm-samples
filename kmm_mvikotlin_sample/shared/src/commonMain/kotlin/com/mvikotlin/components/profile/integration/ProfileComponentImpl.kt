package com.mvikotlin.components.profile.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.mvikotlin.components.profile.ProfileComponent
import com.mvikotlin.components.profile.ProfileComponent.Model
import com.mvikotlin.components.profile.ProfileComponent.Output
import com.mvikotlin.components.profile.store.ProfileStore.Intent
import com.mvikotlin.components.profile.store.ProfileStoreImpl
import com.mvikotlin.repository.ProfileRepository
import com.mvikotlin.utils.asValue

class ProfileComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    profileRepository: ProfileRepository,
    private val userId: Long,
    private val output: Consumer<Output>,
) : ProfileComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        ProfileStoreImpl(
            userId = userId,
            storeFactory = storeFactory,
            profileRepository = profileRepository
        ).create()
    }

    override val model: Value<Model> = store.asValue().map(stateToModel)

    override fun onEditClicked() {
        output(Output.Edit(userId = userId))
    }

    override fun onReload() {
        store.accept(Intent.Reload)
    }
}