package com.example.dietmemory.main.home

import com.example.dietmemory.main.home.models.MainResponse
import com.example.dietmemory.main.home.models.ResponseRecommend
import com.example.dietmemory.main.home.models.postMainData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeRetrofitInterface {
    @POST("main")
    fun postMain(@Body params : postMainData) : Call<MainResponse>

    @POST("frecommend")
    fun postRecommend(@Body params: postMainData) : Call<ResponseRecommend>
}