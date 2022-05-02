plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.decompose.android"
        minSdk = 21
        targetSdk = 31
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
    namespace = "com.decompose.android"
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


    //Decompose
    implementation("com.arkivanov.decompose:decompose:0.6.0")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:0.6.0")
}