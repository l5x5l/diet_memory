package com.example.dietmemory.config

import android.app.Application
import android.content.SharedPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class GlobalApplication : Application() {
    companion object {
        val API_URL = "https://diet-plan.run.goorm.io/"
        lateinit var globalSharedPreferences : SharedPreferences
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        const val SHOW_CAL = "SHOW-CALORIE"
        const val UNIT_TYPE = "UNIT-TYPE"
        const val STRING_DEFAULT = "__DEFAULT__"
        const val WATER_INTAKE = "WATER-INTAKE"
        lateinit var sRetrofit: Retrofit

        // 날짜가 바뀌었을 때 true 로 세팅되는 flag
        var dayChanged = false
        lateinit var currentCalendar : Calendar
        var year = 0
        var month = 0
        var day = 0
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofitInstance()
        globalSharedPreferences = applicationContext.getSharedPreferences("dietMemory", MODE_PRIVATE)

        currentCalendar = Calendar.getInstance()
        year = currentCalendar.get(Calendar.YEAR)
        month = currentCalendar.get(Calendar.MONTH)
        day = currentCalendar.get(Calendar.DAY_OF_MONTH)
        if (day != globalSharedPreferences.getInt("day", 0) ||
                month != globalSharedPreferences.getInt("month", 0) ||
                year != globalSharedPreferences.getInt("year", 0)){
            val editor = globalSharedPreferences.edit()
            editor.putInt("day", day)
            editor.putInt("month", month)
            editor.putInt("year", year)
            editor.apply()
            dayChanged = true
        }
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