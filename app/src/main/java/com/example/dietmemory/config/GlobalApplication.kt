package com.example.dietmemory.config

import android.app.Application
import android.content.SharedPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GlobalApplication : Application() {
    companion object {
        val API_URL = "https://diet-plan.run.goorm.io/"
        lateinit var globalSharedPreferences : SharedPreferences
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val SHOW_CAL = "SHOW-CALORIE"
        const val UNIT_TYPE = "UNIT-TYPE"
        const val STRING_DEFAULT = "__DEFAULT__"
        lateinit var sRetrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofitInstance()
        globalSharedPreferences = applicationContext.getSharedPreferences("dietMemory", MODE_PRIVATE)
    }

    private fun initRetrofitInstance() {
        val client : OkHttpClient = OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(XAccessTokenInterceptor()).build()

        sRetrofit = Retrofit.Builder()
                .baseUrl(API_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}