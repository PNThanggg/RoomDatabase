package com.pnt.roomdatabase.repository

import com.pnt.roomdatabase.room.DataEntryDao
import com.pnt.roomdatabase.room.UserInfoEntity
import kotlinx.coroutines.flow.Flow

class UserInfoRepository(private val dataEntryDao: DataEntryDao) {

    /** Please Note : Room executes all queries on a separate thread.
    Observed Flow will notify the observer when the data has changed.
     **/
    var allUsers: Flow<List<UserInfoEntity>> = dataEntryDao.getAllUsers()

    fun insert(userInfo: UserInfoEntity) {
        dataEntryDao.insertUser(userInfo)
    }

    fun deleteById(uId: Int) {
        dataEntryDao.deleteById(uId)
    }
}