package com.pnt.roomdatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pnt.roomdatabase.repository.UserInfoRepository
import com.pnt.roomdatabase.room.UserInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: UserInfoRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    // Creates a LiveData that has values collected from the origin Flow.
    val allWords: LiveData<List<UserInfoEntity>> = repository.allUsers.asLiveData()

    fun insert(userInfo: UserInfoEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.insert(userInfo)
        }
    }

    fun delete(userid: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteById(userid)
            }
        }
    }

    override fun hashCode(): Int {
        var result = repository.hashCode()
        result = 31 * result + allWords.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserViewModel

        if (repository != other.repository) return false
        return allWords == other.allWords
    }
}