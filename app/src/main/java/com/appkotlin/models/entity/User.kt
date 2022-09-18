package com.appkotlin.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
//    @ColumnInfo(name = "user_name")
    var name:String,
    var message:String,
    var image_id:Int
    )