plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.mvikotlin.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha08"
    }
    namespace = "com.mvikotlin.android"
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")

    implementation("androidx.compose.ui:ui:1.2.0-alpha08")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.2.0-alpha08")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.2.0-alpha08")
    // Material Design
    implementation("androidx.compose.material:material:1.2.0-alpha08")

    //Accompanist
    implementation("com.google.accompanist:accompanist-swiperefresh:0.22.0-rc")

    //Decompose
    implementation("com.arkivanov.decompose:decompose:0.6.0")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:0.6.0")

    //MVIKotlin
    implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta02")
    implementation("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta02")
    implementation("com.arkivanov.mvikotlin:mvikotlin-logging:3.0.0-beta02")
    implementation("com.arkivanov.mvikotlin:mvikotlin-timetravel:3.0.0-beta02")
}