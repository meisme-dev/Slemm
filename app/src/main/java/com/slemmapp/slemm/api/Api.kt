package com.slemmapp.slemm.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class Api {

    final val baseUrl: String;
    final val auth: Auth;

    constructor(baseUrl: String, auth: Auth) {
        this.baseUrl = baseUrl;
        this.auth = auth;
    }

    @Suppress("PropertyName")
    data class Auth(
        val username_or_email: String?,
        val password: String?,
    )

    @Suppress("PropertyName")
    data class Post(
        val id: Int,
        val name: String,
        val body: String,
        val creator_id: Int,
        val community_id: Int,
        val removed: Boolean,
        val locked: Boolean,
        val published: String,
        val updated: String,
        val delete: Boolean,
        val nsfw: Boolean,
        val ap_id: String,
        val local: Boolean,
        val language_id: Int,
        val featured_community: Boolean,
        val featured_local: Boolean
    )

    data class PostList(
        val post: Post
    )

    interface Service {
        @GET("/post")
        suspend fun getPost(): Response<Post>

        @GET("/post/list")
        suspend fun getPosts(): Response<PostList>
    }

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}