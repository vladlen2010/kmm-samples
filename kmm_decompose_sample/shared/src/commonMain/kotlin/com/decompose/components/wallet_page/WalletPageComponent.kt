package com.decompose.components.wallet_page

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.wallet_page.WalletPage.*

class WalletPageComponent : WalletPage {

    private val _model =
        MutableValue(
            Model(
                amount = "1000000000"
            )
        )

    override val model: Value<Model> = _model


}