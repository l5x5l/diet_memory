package com.example.dietmemory.main.home

import com.example.dietmemory.config.BasePresenter
import com.example.dietmemory.data.FoodData
import com.example.dietmemory.main.home.models.MainResponse

interface HomeContract {
    interface View{
        fun applyTodayData(dataList : MainResponse, totalCal : Int = 0, totalCar : Int = 0, totalFat : Int = 0, totalPro : Int = 0)
        fun applyWaterIntake(intake : Int, scaleType : Int)
    }
    interface Presenter : BasePresenter<View> {
        fun tryGetTodayData()
        fun changeWaterIntake(intakeType : Int)
    }
}