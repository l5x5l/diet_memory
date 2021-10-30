package com.example.dietmemory.main.calendar

import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.calendar.models.PostDaySummary
import com.example.dietmemory.main.calendar.models.ResponseDaySummary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarPresenter : CalendarContract.Presenter {

    private var calendarView : CalendarContract.View ?= null

    // 특정 날에 대한 음식 섭취 요약 데이터 요구 및 UI 갱신
    override fun tryGetDateData(year: Int, month: Int, day: Int) {

        val retrofitInterface = GlobalApplication.sRetrofit.create(CalendarRetrofitInterface::class.java)
        retrofitInterface.postDaySummaryData(PostDaySummary(year, month + 1, day)).enqueue(object : Callback<ResponseDaySummary> {
            override fun onResponse(call: Call<ResponseDaySummary>, response: Response<ResponseDaySummary>) {
                if (response.isSuccessful) {
                    if (response.body()!!.isSuccess) {
                        if (response.body()!!.data == null)
                            calendarView!!.showDateData(year, month, day, response.body()!!.endata, response.body()!!.food)
                        else
                            calendarView!!.showDateData(year, month, day, response.body()!!.endata, response.body()!!.food, response.body()!!.data!!)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDaySummary>, t: Throwable) {

            }

        })

        /*calendarView!!.showDateData(year, month, day)*/
    }

    override fun tryGetMonthData(year: Int, month: Int) {
        // 원래 서버로부터 통신하여 가져와야 한다.
        val tempDataList = arrayListOf(0,0,0,1,1,1,2,0,1,2,0,0,0,1,1,1,2,0,1,2)
        calendarView!!.applyMonthData(year, month, tempDataList)
    }

    override fun takeView(inputView: CalendarContract.View) {
        calendarView = inputView
    }

    override fun dropView() {
        calendarView = null
    }

    /*// 해당 달에 대한 데이터 적용
    fun getCalendar(){
        val tempDataList = arrayListOf(0,0,0,1,1,1,2,0,1,2,0,2,1,1)
        calendarView!!.applyMonthData(tempDataList)
    }*/
}