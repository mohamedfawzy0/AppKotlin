package com.appkotlin.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "user_name")
    var userName:String,
    var data:String,
    var img_id:Int
    )