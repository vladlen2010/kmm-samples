package com.mvikotlin

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}