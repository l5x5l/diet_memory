package com.example.dietmemory.main.calendar

import com.example.dietmemory.config.BasePresenter

interface CalendarContract {
    interface View{
        fun showDateData(year : Int, month : Int, day : Int)
        fun applyData(arrayList: ArrayList<Int>)
    }

    interface Presenter : BasePresenter<View> {
        fun tryGetDateData(year : Int, month : Int, day : Int)

    }

}