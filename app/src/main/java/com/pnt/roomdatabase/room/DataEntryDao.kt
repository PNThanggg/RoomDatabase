package com.pnt.roomdatabase.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DataEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userInfo: UserInfoEntity): Long

    @Query("SELECT * FROM user_info")
    fun getAllUsers(): Flow<List<UserInfoEntity>>

    @Query("delete from user_info where user_id = :u_id")
//    @Delete
    fun deleteById(u_id: Int)
}