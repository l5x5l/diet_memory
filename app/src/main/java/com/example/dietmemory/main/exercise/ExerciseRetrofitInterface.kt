package com.example.dietmemory.main.exercise

import com.example.dietmemory.main.exercise.models.PostExer
import com.example.dietmemory.main.exercise.models.ResponseExer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ExerciseRetrofitInterface {
    @POST("exer")
    fun postGetExercise(@Body params : PostExer) : Call<ResponseExer>
}