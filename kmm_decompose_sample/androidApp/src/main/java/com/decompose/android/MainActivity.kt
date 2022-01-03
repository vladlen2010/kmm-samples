package com.decompose.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decompose.Greeting
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import com.decompose.components.root.RootComponent

fun greet(): String {
    return Greeting().greeting()
}

@ExperimentalDecomposeApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = RootComponent(defaultComponentContext())

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RootUi(
                        component = component,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}