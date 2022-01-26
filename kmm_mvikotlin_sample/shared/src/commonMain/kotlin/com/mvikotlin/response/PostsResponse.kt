package com.mvikotlin.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<Post>
)

@Serializable
data class Post(
    @SerialName("id") val id: Long,
    @SerialName("userId") val userId: Long,
    @SerialName("title") val title: String,
    @SerialName("body") val body: String
)