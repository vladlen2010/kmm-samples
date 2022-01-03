package com.decompose.components.wallet_page

import com.arkivanov.decompose.value.Value

interface WalletPage {

    val model : Value<Model>

    data class Model(
        val amount: String
    )

}