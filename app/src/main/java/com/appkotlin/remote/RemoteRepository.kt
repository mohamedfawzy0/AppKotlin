package com.appkotlin.remote

import com.appkotlin.models.entity.User
import retrofit2.Response
import retrofit2.http.*

interface RemoteRepository {
    suspend fun getAPIUsers(): Response<List<User>>

    suspend fun getAPIUser(id: Int): Response<User>

    suspend fun getAPIUserQuery(id: Int): Response<User>

    suspend fun addAPIUser(user: User): Response<User>

    suspend fun updateAPIUser(user: User,id: Int): Response<User>

    suspend fun deleteAPIUser(id: Int)
}