package com.mvikotlin.repository

import com.mvikotlin.response.Post
import com.mvikotlin.response.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object JsonPlaceholderApi {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun loadPosts(): List<Post> = client.get("$BASE_URL/posts").body()
    suspend fun getPostById(id: Long): Post = client.get("$BASE_URL/posts/$id").body()
    suspend fun getProfile(userId: Long): User = client.get("$BASE_URL/users/$userId").body()
}