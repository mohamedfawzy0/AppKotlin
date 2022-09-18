package com.appkotlin.remote

import com.appkotlin.models.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepositoryImp(private val api: ServiceAPI) : RemoteRepository {
    override suspend fun getAPIUsers() = api.getAPIUsers()

    override suspend fun getAPIUser(id: Int) = api.getAPIUser(id)

    override suspend fun getAPIUserQuery(id: Int) = api.getAPIUserQuery(id)

    override suspend fun addAPIUser(user: User) = api.addAPIUser(user)


    override suspend fun updateAPIUser(user: User, id: Int) = api.updateAPIUser(user, id)


    override suspend fun deleteAPIUser(id: Int) {
        api.deleteAPIUser(id)

    }
}