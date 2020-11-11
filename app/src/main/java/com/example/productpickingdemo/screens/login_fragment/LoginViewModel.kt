package com.example.productpickingdemo.screens.login_fragment

import android.app.Application
import com.example.productpickingdemo.base.view_model.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    // TODO: 11.11.2020 for test
    fun test() {
        qrManager.getUserTest()
    }
}
