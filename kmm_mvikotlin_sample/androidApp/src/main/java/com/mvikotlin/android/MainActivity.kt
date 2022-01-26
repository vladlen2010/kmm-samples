package com.mvikotlin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.mvikotlin.Greeting
import com.mvikotlin.components.root.RootComponent
import com.mvikotlin.components.root.integration.RootComponentImpl
import com.mvikotlin.repository.PostRepository
import com.mvikotlin.repository.ProfileRepository

fun greet(): String {
    return Greeting().greeting()
}

@ExperimentalDecomposeApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = root(defaultComponentContext())

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RootScreen(
                        component = component,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    private fun root(componentContext: ComponentContext): RootComponent =
        RootComponentImpl(
            userId = 1,
            componentContext = componentContext,
            storeFactory = LoggingStoreFactory(DefaultStoreFactory()),
            postRepository = PostRepository(),
            profileRepository = ProfileRepository()
        )
}
