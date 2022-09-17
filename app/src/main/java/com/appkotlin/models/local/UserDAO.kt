package com.appkotlin.models.local

import androidx.room.*
import com.appkotlin.models.entity.User

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from user_table")
    suspend fun getUsers():List<User>
}