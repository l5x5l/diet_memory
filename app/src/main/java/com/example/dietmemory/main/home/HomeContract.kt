package com.example.dietmemory.main.home

import com.example.dietmemory.config.BasePresenter
import com.example.dietmemory.data.FoodData
import com.example.dietmemory.main.home.models.MainResponse

interface HomeContract {
    interface View{
        fun applyTodayData(dataList : MainResponse)
        fun applyWaterIntake(intake : Int, scaleType : Int)
    }
    interface Presenter : BasePresenter<View> {
        fun tryGetTodayData()
        fun changeWaterIntake(intakeType : Int)
    }
}