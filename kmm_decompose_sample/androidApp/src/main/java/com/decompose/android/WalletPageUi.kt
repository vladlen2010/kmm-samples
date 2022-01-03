package com.decompose.android

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.decompose.components.wallet_page.WalletPage

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WalletPageUi(
    component: WalletPage,
    modifier: Modifier = Modifier
) {
    val model by component.model.subscribeAsState()

    Box(
        modifier = modifier
    ) {

        Text(
            text = "Balance:\n${model.amount}",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun WalletPageUiPreview() {
    WalletPageUi(
        component = WalletPagePreview(),
        modifier = Modifier.fillMaxSize()
    )
}

class WalletPagePreview : WalletPage {
    override val model: Value<WalletPage.Model> =
        MutableValue(
            WalletPage.Model(
                amount = "1000"
            )
        )
}

