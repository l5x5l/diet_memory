package com.example.dietmemory.main.setting.physical

import com.example.dietmemory.main.setting.physical.model.PostInfochange
import com.example.dietmemory.main.setting.physical.model.ResponseInfochange
import com.example.dietmemory.main.setting.physical.model.ResponseInfochangeData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SettingPhysicalRetrofitInterface {
    @POST("infochange")
    fun postInfochange(@Body params : PostInfochange) : Call<ResponseInfochange>

    @GET("infochange/data")
    fun postInfochangeData() : Call<ResponseInfochangeData>
}