package com.example.dietmemory.add.food

import com.example.dietmemory.add.food.models.FoodRecordData
import com.example.dietmemory.config.BasePresenter

interface FoodContract {
    interface View{
        fun applyFoodRecord(food : FoodRecordData)
    }

    interface Presenter : BasePresenter<View>{
        fun tryGetFoodRecord(fileUrl : String)
    }

}