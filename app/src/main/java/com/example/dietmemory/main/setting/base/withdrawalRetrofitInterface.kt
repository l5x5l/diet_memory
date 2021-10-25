package com.example.dietmemory.main.setting.base

import com.example.dietmemory.main.setting.base.models.ResponseWithdrawal
import retrofit2.Call
import retrofit2.http.GET

interface withdrawalRetrofitInterface {
    @GET("delete")
    fun getWithdrawal() : Call<ResponseWithdrawal>
}