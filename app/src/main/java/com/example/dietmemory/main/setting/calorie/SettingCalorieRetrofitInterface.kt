package com.example.dietmemory.main.setting.calorie

import com.example.dietmemory.main.setting.calorie.model.PostTcalo
import com.example.dietmemory.main.setting.calorie.model.ResponseTcalo
import com.example.dietmemory.main.setting.calorie.model.ResponseTcaloData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SettingCalorieRetrofitInterface {
    @GET("tcalo/data")
    fun getTcaloData() : Call<ResponseTcaloData>

    @POST("tcalo")
    fun postTcalo(@Body params : PostTcalo) : Call<ResponseTcalo>
}