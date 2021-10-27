package com.example.dietmemory.add.exercise

import com.example.dietmemory.config.BasePresenter

interface ExerciseContract {
    interface View {
        fun applyPostExer(isSuccess : Boolean)
        fun applyTimeChange(time : Int, cal : Int)
        fun applyExerChange(exName : String, time : Int, cal : Int)
    }

    interface Presenter : BasePresenter<View> {
        fun tryPostExer(fileURL : String)
        fun updateTime(isPlus : Boolean)
        fun changeExercise(exName : String, cal : Int)
    }
}