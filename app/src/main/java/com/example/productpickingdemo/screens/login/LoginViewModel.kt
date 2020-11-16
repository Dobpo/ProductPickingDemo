package com.example.productpickingdemo.screens.login

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    fun initUsers() = CoroutineScope(IO).launch {
        dbManager.initUsers()
    }

    fun getUsers(): LiveData<List<User>> {
        return dbManager.getUsers()
    }

    fun printDb() {
        CoroutineScope(IO).launch {
            dbManager.printDb()
        }
    }
}