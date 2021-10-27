package com.example.dietmemory.add.exercise

import com.example.dietmemory.add.exercise.models.ExerResponse
import com.example.dietmemory.add.exercise.models.PostExer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ExerciseRetrofitInterface {
    @POST("addexer")
    fun postExercise(@Body params : PostExer) : Call<ExerResponse>
}