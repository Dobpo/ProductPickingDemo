package com.example.productpickingdemo.screens.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.productpickingdemo.base.view_model.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    fun initDatabase() = viewModelScope.launch {
        dbManager.initDatabase()
    }
}