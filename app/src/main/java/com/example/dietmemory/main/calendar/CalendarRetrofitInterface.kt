package com.example.dietmemory.main.calendar

import com.example.dietmemory.main.calendar.models.PostDaySummary
import com.example.dietmemory.main.calendar.models.ResponseDaySummary
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CalendarRetrofitInterface {
    @POST("calender/data")
    fun postDaySummaryData(@Body params : PostDaySummary) : Call<ResponseDaySummary>
}