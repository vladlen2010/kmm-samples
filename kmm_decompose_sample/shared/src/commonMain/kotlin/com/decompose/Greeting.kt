package com.decompose

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}