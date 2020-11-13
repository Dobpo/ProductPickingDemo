package com.example.productpickingdemo.screens.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    fun initDatabase() = viewModelScope.launch {
        dbManager.initDatabase()
    }

    fun getUsers() {
        CoroutineScope(IO).launch {
            val users = dbManager.getUsers()

            withContext(Main) {
                usersLiveData.postValue(users)
            }
        }
    }
}