package com.example.productpickingdemo.database.shared_preferences

interface SharedPreferencesManager {
    fun getMode():Int
    fun setMode(mode: Int)
}