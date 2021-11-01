package com.example.dietmemory.main.calendar

import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.calendar.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

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
        val retrofitInterface = GlobalApplication.sRetrofit.create(CalendarRetrofitInterface::class.java)
        retrofitInterface.postCalendar(PostCalendar(year, month + 1)).enqueue(object : Callback<ResponseCalendar>{
            override fun onResponse(call: Call<ResponseCalendar>, response: Response<ResponseCalendar>) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        val dayData = changeDayData(year, month, response.body()!!.number)
                        calendarView!!.applyMonthData(year, month, dayData)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseCalendar>, t: Throwable) {

            }
        })
    }

    override fun takeView(inputView: CalendarContract.View) {
        calendarView = inputView
    }

    override fun dropView() {
        calendarView = null
    }


    private fun changeDayData(year : Int, month : Int, inputData : ArrayList<DayInfo>) : ArrayList<Int>{
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        val day = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val dayList = IntArray(day){-1}

        for (data in inputData){
            dayList[data.day - 1] = data.num
        }

        return arrayListOf(*dayList.toTypedArray())
    }
}