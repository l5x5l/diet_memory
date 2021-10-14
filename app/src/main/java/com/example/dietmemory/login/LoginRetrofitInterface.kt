package com.example.dietmemory.login


import com.example.dietmemory.login.models.LoginData
import com.example.dietmemory.login.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Query

interface LoginRetrofitInterface {
    @POST("login")
    fun postLogin(@Body params : LoginData) : Call<LoginResponse>

    @POST("login")
    fun postLogin2(@Query("email") email : String, @Query("pwd") pwd : String) : Call<LoginResponse>
}