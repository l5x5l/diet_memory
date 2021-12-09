package com.example.dietmemory.add.food

import com.example.dietmemory.add.food.models.FoodInfoResponse
import com.example.dietmemory.add.food.models.FoodRecordData
import com.example.dietmemory.config.BasePresenter

interface FoodContract {
    interface View{
        fun applyFoodRecord(food : FoodRecordData)
        fun applyPostAddFood(isSuccess : Boolean)
        fun applyFoodAuto(foods : ArrayList<String>)
        fun applyFoodInfo(food : FoodInfoResponse)
    }

    interface Presenter : BasePresenter<View>{
        fun tryGetFoodRecord(fileUrl : String)
        fun tryPostAddFood(intakeTime : Int, foodName : String)
        fun tryGetFoodAuto(word : String)
        fun tryGetFoodInfo(foodName : String)
    }

}