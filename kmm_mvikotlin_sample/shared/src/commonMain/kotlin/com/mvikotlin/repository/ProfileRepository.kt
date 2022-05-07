package com.mvikotlin.repository

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import com.mvikotlin.response.User

class ProfileRepository(
    private val api: JsonPlaceholderApi
) {
    fun getProfile(userId: Long): Single<User> { //TODO: add cache
        return singleFromCoroutine { api.getProfile(userId) }
    }
}