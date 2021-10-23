package com.example.dietmemory.add.food

import com.example.dietmemory.add.food.models.FoodRecordResponse
import com.example.dietmemory.add.food.models.PostFoodRecord
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FoodRetrofitInterface {
    @POST("fdreco")
    fun postFoodRecord(@Body params : PostFoodRecord) : Call<FoodRecordResponse>
}