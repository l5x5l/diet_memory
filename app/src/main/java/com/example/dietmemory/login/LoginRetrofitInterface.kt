package com.example.dietmemory.login


import com.example.dietmemory.login.models.LoginData
import com.example.dietmemory.login.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface LoginRetrofitInterface {
    @POST("login")
    fun postLogin(@Body params : LoginData) : Call<LoginResponse>
}