package com.example.productpickingdemo.screens.location

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    val locationLiveData: MutableLiveData<Location> = MutableLiveData()

    fun getLocation(locationId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val location = dbManager.getLocation(locationId)

            withContext(Dispatchers.Main) {
                locationLiveData.postValue(location)
            }
        }
    }
}