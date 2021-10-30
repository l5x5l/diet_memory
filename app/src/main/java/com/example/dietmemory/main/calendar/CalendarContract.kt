package com.example.dietmemory.main.calendar

import com.example.dietmemory.config.BasePresenter
import com.example.dietmemory.main.calendar.models.CalendarFood
import com.example.dietmemory.main.calendar.models.GoalData
import com.example.dietmemory.main.calendar.models.IntakeData

interface CalendarContract {
    interface View{
        fun showDateData(year : Int, month : Int, day : Int, endata : GoalData, food : ArrayList<CalendarFood>, data : IntakeData = IntakeData(0,0,0,0))
        fun applyMonthData(year : Int, month : Int, arrayList: ArrayList<Int>)

    }

    interface Presenter : BasePresenter<View> {
        fun tryGetDateData(year : Int, month : Int, day : Int)
        fun tryGetMonthData(year : Int, month : Int)
    }

}