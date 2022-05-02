plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")

    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            transitiveExport = true
            export("com.arkivanov.decompose:decompose:0.6.0")
            export("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta02")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //Decompose
                implementation("com.arkivanov.decompose:decompose:0.6.0")
                //MVIKotlin
                implementation("com.arkivanov.mvikotlin:mvikotlin:3.0.0-beta02")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:3.0.0-beta02")
                implementation("com.arkivanov.mvikotlin:rx:3.0.0-beta02")
                //Kotlinx serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
                //Reactive
                implementation( "com.badoo.reaktive:reaktive:1.2.1")
                //Ktor
                implementation("io.ktor:ktor-client-core:1.6.7")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.6.7")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.7")
                api("com.arkivanov.decompose:decompose:0.6.0")
                api("com.arkivanov.mvikotlin:mvikotlin-main:3.0.0-beta02")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
    namespace = "com.mvikotlin"
}