package com.appkotlin.models

import com.appkotlin.models.entity.User
import com.appkotlin.models.local.LocalRepository
import com.appkotlin.models.remote.RemoteRepository

class RepositoryImp(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : Repository {
    override suspend fun getAPIUsers() = remoteRepository.getAPIUsers()

    override suspend fun getAPIUser(id: Int) = remoteRepository.getAPIUser(id)

    override suspend fun getAPIUserQuery(id: Int) = remoteRepository.getAPIUserQuery(id)

    override suspend fun addAPIUser(user: User) = remoteRepository.addAPIUser(user)

    override suspend fun updateAPIUser(user: User, id: Int) = remoteRepository.updateAPIUser(user,id)

    override suspend fun deleteAPIUser(id: Int) {
        remoteRepository.deleteAPIUser(id)
    }

    override suspend fun getUsers() = localRepository.getUsers()

    override suspend fun deleteUser(user: User) {
        localRepository.deleteUser(user)
    }

    override suspend fun insertOrUpdateUser(user: User) {
        localRepository.insertOrUpdateUser(user)
    }
}