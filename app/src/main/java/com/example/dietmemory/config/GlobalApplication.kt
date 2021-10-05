package com.example.dietmemory.config

import android.app.Application
import android.content.SharedPreferences

class GlobalApplication : Application() {
    companion object {
        lateinit var globalSharedPreferences : SharedPreferences
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val STRING_DEFAULT = "__DEFAULT__"
    }

    override fun onCreate() {
        super.onCreate()
        globalSharedPreferences = applicationContext.getSharedPreferences("dietMemory", MODE_PRIVATE)
    }
}