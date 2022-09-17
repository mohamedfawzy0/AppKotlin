package com.appkotlin.models.local

import com.appkotlin.models.entity.User

interface LocalRepository {
    suspend fun getUsers():List<User>

    suspend fun deleteUser(user: User)

    suspend fun insertOrUpdateUser(user: User)
}