package com.mvikotlin.repository

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import com.mvikotlin.response.Post

class PostRepository(
    private val api: JsonPlaceholderApi
) {

    fun getPosts(forceUpdate: Boolean): Single<List<Post>> {  //TODO: add cache
        return singleFromCoroutine { api.loadPosts() }
    }

    fun getPostById(id: Long): Single<Post> {
        return singleFromCoroutine { api.getPostById(id) }
    }

}