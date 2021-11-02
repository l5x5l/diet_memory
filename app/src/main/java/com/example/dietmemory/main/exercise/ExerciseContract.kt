package com.example.dietmemory.main.exercise

import com.example.dietmemory.config.BasePresenter
import com.example.dietmemory.main.exercise.models.RecommendExercise
import com.example.dietmemory.main.exercise.models.UserExer

interface ExerciseContract {
    interface View {
        fun applyExer(isSuccess : Boolean, message : String, exer : ArrayList<RecommendExercise> ?= null, userExer: ArrayList<UserExer>? = null)
    }
    interface Presenter : BasePresenter<View>{
        fun tryGetExer()
    }
}