package com.appkotlin.models.local

import com.appkotlin.models.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db: UserDatabase) : LocalRepository {
    override suspend fun getUsers() = db.userDAO().getUsers()


    override suspend fun deleteUser(user: User) {
        db.userDAO().deleteUser(user)
    }

    override suspend fun insertOrUpdateUser(user: User) {
        db.userDAO().insertOrUpdateUser(user)

    }
}