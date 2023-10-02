package com.pnt.roomdatabase.room

import androidx.room.*

@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id")
    val id : Int = 0,
    val name: String,
    val email: String,
)