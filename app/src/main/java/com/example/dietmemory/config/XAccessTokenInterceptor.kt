package com.example.dietmemory.config

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder : Request.Builder = chain.request().newBuilder()
        val jwtToken : String ?= GlobalApplication.globalSharedPreferences.getString(GlobalApplication.X_ACCESS_TOKEN, null)
        if (jwtToken != null){
            builder.addHeader("Authorization", "Bearer $jwtToken")
        }
        return chain.proceed(builder.build())
    }
}