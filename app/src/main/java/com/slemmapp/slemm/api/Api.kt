package com.slemmapp.slemm.api

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


class Api(private val baseUrl: String, private val auth: AuthBody) {

    data class AuthBody(
        @SerializedName("username_or_email") val usernameOrEmail: String?,
        @SerializedName("password") val password: String?,
    )

    data class AuthResponse(
        @SerializedName("jwt") val authToken: String?,
        @SerializedName("registration_created") val registrationCreated: Boolean?,
        @SerializedName("verify_email_sent") val verifyEmailSent: Boolean?
    )

    data class Post(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("body") val body: String?,
        @SerializedName("creator_id") val creatorId: Int?,
        @SerializedName("community_id") val communityId: Int?,
        @SerializedName("removed") val removed: Boolean?,
        @SerializedName("locked") val locked: Boolean?,
        @SerializedName("published") val published: String?,
        @SerializedName("updated") val updated: String?,
        @SerializedName("deleted") val deleted: Boolean?,
        @SerializedName("nsfw") val nsfw: Boolean?,
        @SerializedName("ap_id") val apId: String?,
        @SerializedName("local") val local: Boolean?,
        @SerializedName("language_id") val languageId: Int?,
        @SerializedName("featured_community") val featuredCommunity: Boolean?,
        @SerializedName("featured_local") val featuredLocal: Boolean?,
        @SerializedName("url") val url: String?,
        @SerializedName("embed_title") val embedTitle: String?,
        @SerializedName("embed_description") val embedDescription: String?,
        @SerializedName("thumbnail_url") val thumbnailUrl: String?
    )

    data class Creator(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("banned") val banned: Boolean?,
        @SerializedName("published") val published: String?,
        @SerializedName("actor_id") val actorId: String?,
        @SerializedName("local") val local: Boolean?,
        @SerializedName("deleted") val deleted: Boolean?,
        @SerializedName("admin") val admin: Boolean?,
        @SerializedName("bot_account") val bot: Boolean?,
        @SerializedName("instance_id") val instanceId: Int?
    )

    data class Comment(
        @SerializedName("id") val id: Int?,
        @SerializedName("creator_id") val creatorId: Int?,
        @SerializedName("post_id") val postId: Int?,
        @SerializedName("content") val content: String?,
        @SerializedName("removed") val removed: Boolean?,
        @SerializedName("published") val published: String?,
        @SerializedName("deleted") val deleted: Boolean?,
        @SerializedName("ap_id") val apId: String?,
        @SerializedName("local") val local: Boolean?,
        @SerializedName("path") val path: String?,
        @SerializedName("distinguished") val distinguished: Boolean?,
        @SerializedName("language_id") val languageId: Int?
    )

    data class CommentInfo(
        @SerializedName("comment") val comment: Comment,
        @SerializedName("post") val post: Post?,
        @SerializedName("creator") val creator: Creator?,
        @SerializedName("community") val community: Community?,
        @SerializedName("creator_banned_from_community") val creatorBanned: Boolean?,
        @SerializedName("counts") val counts: Counts?,
        @SerializedName("subscribed") val subscribed: String?,
        @SerializedName("saved") val saved: Boolean?,
        @SerializedName("creator_blocked") val creatorBlocked: Boolean?
    )

    data class CommentList(
        @SerializedName("comments") var comments: SnapshotStateList<CommentInfo>
    )

    data class Community(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("removed") val removed: Boolean?,
        @SerializedName("published") val published: String?,
        @SerializedName("updated") val updated: String?,
        @SerializedName("deleted") val deleted: Boolean?,
        @SerializedName("nsfw") val nsfw: Boolean?,
        @SerializedName("actor_id") val actorId: String?,
        @SerializedName("local") val local: Boolean?,
        @SerializedName("icon") val icon: String?,
        @SerializedName("hidden") val hidden: Boolean?,
        @SerializedName("posting_restricted_to_mods") val restricted: Boolean?,
        @SerializedName("instance_id") val instanceId: Int?
    )

    data class Counts(
        @SerializedName("id") val id: Int?,
        @SerializedName("post_id") val postId: Int?,
        @SerializedName("comments") val comments: Int?,
        @SerializedName("score") val score: Int?,
        @SerializedName("upvotes") val upvotes: Int?,
        @SerializedName("downvotes") val downvotes: Int?,
        @SerializedName("published") val published: String?,
        @SerializedName("newest_comment_time_necro") val newestCommentNecro: String?,
        @SerializedName("newest_comment_time") val newestComment: String?,
        @SerializedName("featured_community") val featured: Boolean?,
        @SerializedName("featured_local") val featuredLocal: Boolean?,
        @SerializedName("hot_rank") val hotRank: Int?,
        @SerializedName("hot_rank_active") val hotRankActive: Int?
    )

    data class PostInfo(
        @SerializedName("post") val post: Post?,
        @SerializedName("creator") val creator: Creator?,
        @SerializedName("community") val community: Community?,
        @SerializedName("creator_banned_from_community") val creatorBanned: Boolean?,
        @SerializedName("counts") val counts: Counts?,
        @SerializedName("subscribed") val subscribed: String?,
        @SerializedName("saved") val saved: Boolean?,
        @SerializedName("read") val read: Boolean?,
        @SerializedName("creator_blocked") val creatorBlocked: Boolean?,
        @SerializedName("unread_comments") val unread: Int?,
    )

    data class PostList(
        @SerializedName("posts") var posts: SnapshotStateList<PostInfo>
    )

    interface Service {
        @Headers("Content-Type: application/json")
        @GET("post")
        suspend fun getPost(): Response<Post>

        @Headers("Content-Type: application/json")
        @GET("post/list")
        suspend fun getPosts(@Query("page") page: String?): Response<PostList>

        @Headers("Content-Type: application/json")
        @GET("comment/list")
        suspend fun getComments(@Query("post_id") page: String?): Response<CommentList>

        @Headers("Content-Type: application/json")
        @POST("user/login")
        suspend fun login(@Body body: AuthBody): Response<AuthResponse>
    }

    fun getInstance(): Retrofit {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .header("User-Agent", "Slemm")
                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()
        return Retrofit.Builder().baseUrl(this.baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}