package com.example.dietmemory.main.exercise.models

data class ResponseExer(val isSuccess : Boolean, val message : String, val exer : ArrayList<RecommendExercise>, val userexer : ArrayList<UserExer>)
