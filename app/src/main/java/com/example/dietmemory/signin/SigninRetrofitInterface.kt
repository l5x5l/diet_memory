package com.example.dietmemory.signin

import com.example.dietmemory.signin.models.ResponseEmailCheck
import com.example.dietmemory.signin.models.ResponseSignUp
import com.example.dietmemory.signin.models.postEmailCheck
import com.example.dietmemory.signin.models.postSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SigninRetrofitInterface {
    @POST("signup")
    fun postSingUp(@Body params : postSignUp) : Call<ResponseSignUp>

    @POST("signup/check")
    fun postEmailCheck(@Body params : postEmailCheck) : Call<ResponseEmailCheck>
}