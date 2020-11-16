package com.example.productpickingdemo.database.shared_preferences

import android.content.Context
import android.content.SharedPreferences

const val SCAN_MODE = "scan_mode"

class SharedPreferencesImpl(context: Context) : SharedPreferencesManager {
    private var sPref: SharedPreferences =
        context.getSharedPreferences("com.example.productpickingdemo", Context.MODE_PRIVATE)

    override fun getMode(): Int {
        return sPref.getInt(SCAN_MODE, 0)
    }

    override fun setMode(mode: Int) {
        sPref.edit().putInt(SCAN_MODE, mode).apply()
    }
}