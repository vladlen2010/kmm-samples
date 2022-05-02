buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.android.tools.build:gradle:7.2.0-rc01")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    // Suppress Compose Kotlin compiler compatibility warning
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            val options = listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
            )
            freeCompilerArgs = freeCompilerArgs + options
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}