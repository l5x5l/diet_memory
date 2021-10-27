package com.example.dietmemory.add.food

import com.example.dietmemory.add.food.models.AddFoodResponse
import com.example.dietmemory.add.food.models.FoodRecordResponse
import com.example.dietmemory.add.food.models.PostAddFood
import com.example.dietmemory.add.food.models.PostFoodRecord
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FoodRetrofitInterface {
    @POST("fdreco")
    fun postFoodRecord(@Body params : PostFoodRecord) : Call<FoodRecordResponse>

    @POST("addfood")
    fun postAddFood(@Body params : PostAddFood) : Call<AddFoodResponse>
}