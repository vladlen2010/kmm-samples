package com.mvikotlin.components.edit.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.mvikotlin.components.edit.EditProfileComponent
import com.mvikotlin.components.edit.EditProfileComponent.Output
import com.mvikotlin.components.edit.store.EditProfileStore.Intent
import com.mvikotlin.components.edit.store.EditProfileStoreImpl
import com.mvikotlin.repository.ProfileRepository
import com.mvikotlin.utils.asValue
import com.mvikotlin.utils.getStore

class EditProfileComponentImpl(
    private val userId: Long,
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    profileRepository: ProfileRepository,
    private val output: Consumer<Output>,
) : EditProfileComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        EditProfileStoreImpl(
            userId = userId,
            storeFactory = storeFactory,
            profileRepository = profileRepository
        ).create()
    }

    override val model = store.asValue().map(stateToModel)

    override fun onUsernameChanged(username: String) {
        store.accept(
            Intent.UsernameChanged(userId = userId, username = username)
        )
    }

    override fun onCloseClick() {
        output(Output.Finished)
    }

//    init {
//        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
//            store.labels
//                .map {
//                    when (it) {
//                        Label.Error -> output(Output.Finished)
//                    }
//                }
//        }
//    }
}