package com.example.productpickingdemo.screens.location

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Location
import javax.inject.Inject

class LocationViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    val locationLiveData: MutableLiveData<Location> = MutableLiveData()

    fun getLocation(locationId: Int): LiveData<Location> {
        return dbManager.getLocation(locationId)
    }
}