package com.appkotlin.mvvm

import android.util.Log
import androidx.lifecycle.*
import com.appkotlin.models.Repository
import com.appkotlin.models.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityMVVM(private val repository: Repository) : ViewModel() {


    private var usersMutableLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = usersMutableLiveData

    private var usersAPIMutableLiveData = MutableLiveData<List<User>>()
    val usersAPILiveData: LiveData<List<User>> get() = usersAPIMutableLiveData

    private var addUserAPIMutableLiveData = MutableLiveData<User>()
    val addUserAPILiveData: LiveData<User> get() = addUserAPIMutableLiveData


    fun getUsers() = viewModelScope.launch {
        usersMutableLiveData.postValue(repository.getUsers())
    }


    fun addUser(user: User) {
        viewModelScope.launch {
            repository.insertOrUpdateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun getUsersAPI() = viewModelScope.launch {
        var result = repository.getAPIUsers()
        if (result.isSuccessful) {
            if (result.body() != null) {
                usersAPIMutableLiveData.postValue(result.body())
            }
        } else {
            Log.e("error", result.message())
        }
    }

    fun addUserAPI(user: User) = viewModelScope.launch {
        var result = repository.addAPIUser(user)
        if (result.isSuccessful) {
            if (result.body() != null) {
                addUserAPIMutableLiveData.postValue(result.body())
            }
        } else {
            Log.e("error", result.message())
        }
    }

    fun deleteAPIUser(id: Int) {
        viewModelScope.launch {
            repository.deleteAPIUser(id)
        }
    }
}