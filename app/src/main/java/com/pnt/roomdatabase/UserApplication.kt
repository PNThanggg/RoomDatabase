package com.pnt.roomdatabase

import android.app.Application
import com.pnt.roomdatabase.repository.UserInfoRepository
import com.pnt.roomdatabase.room.UserInfoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application(){

    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { UserInfoDatabase.getDatabase(this) }
    val repository by lazy { UserInfoRepository(database.dataEntryDao()) }
}