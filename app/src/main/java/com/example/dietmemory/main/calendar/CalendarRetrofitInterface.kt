package com.example.dietmemory.main.calendar

import com.example.dietmemory.main.calendar.models.PostCalendar
import com.example.dietmemory.main.calendar.models.PostDaySummary
import com.example.dietmemory.main.calendar.models.ResponseCalendar
import com.example.dietmemory.main.calendar.models.ResponseDaySummary
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CalendarRetrofitInterface {
    @POST("calender/data")
    fun postDaySummaryData(@Body params : PostDaySummary) : Call<ResponseDaySummary>

    @POST("calender")
    fun postCalendar(@Body params : PostCalendar) : Call<ResponseCalendar>
}