package com.example.dietmemory.main.home

import com.example.dietmemory.config.BasePresenter
import com.example.dietmemory.data.FoodData

interface HomeContract {
    interface View{
        fun applyTodayData(dataList : ArrayList<FoodData>)
    }
    interface Presenter : BasePresenter<View> {
        fun tryGetTodayData()
    }
}