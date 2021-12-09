package com.example.dietmemory.add.food

import com.example.dietmemory.add.food.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FoodRetrofitInterface {
    @POST("fdreco")
    fun postFoodRecord(@Body params : PostFoodRecord) : Call<FoodRecordResponse>

    @POST("addfood")
    fun postAddFood(@Body params : PostAddFood) : Call<AddFoodResponse>

    @GET("addfood/auto")
    fun getAutoFood(@Query("foodName")foodName : String) : Call<FoodAutoResponse>

    @GET("addfood/food")
    fun getFoodInfo(@Query("foodName")foodName : String) : Call<FoodInfoResponse>
}