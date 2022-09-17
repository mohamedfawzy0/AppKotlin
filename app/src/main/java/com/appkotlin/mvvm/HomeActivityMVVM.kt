package com.appkotlin.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appkotlin.models.entity.User
import com.appkotlin.models.local.LocalRepositoryImp
import com.appkotlin.models.local.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityMVVM(application: Application) : AndroidViewModel(application) {

    private var localRepositoryImp: LocalRepositoryImp
    private var usersMutableLiveData = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> get() = usersMutableLiveData

    init {
        var db = UserDatabase.getInstance(application)
        localRepositoryImp = LocalRepositoryImp(db)
    }

    fun getUsers() = viewModelScope.launch(Dispatchers.IO) {
        usersMutableLiveData.postValue(localRepositoryImp.getUsers())
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImp.insertOrUpdateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImp.deleteUser(user)
        }
    }
}