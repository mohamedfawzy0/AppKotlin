package com.appkotlin.models.remote

import com.appkotlin.models.entity.User
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {
    @GET("mohamedfawzy0/repo/users")
    suspend fun getAPIUsers(): Response<List<User>>

    @GET("mohamedfawzy0/repo/users/{id}")
    suspend fun getAPIUser(@Path("id") id: Int): Response<User>

    @GET("mohamedfawzy0/repo/users/")
    suspend fun getAPIUserQuery(@Query("id") id: Int): Response<User>

    @POST("mohamedfawzy0/repo/users")
    suspend fun addAPIUser(@Body user: User): Response<User>

    @PUT("mohamedfawzy0/repo/users/{id}")
    suspend fun updateAPIUser(@Body user: User, @Path("id") id: Int): Response<User>

    @DELETE("mohamedfawzy0/repo/users/{id}")
    suspend fun deleteAPIUser(@Path("id") id: Int)

}