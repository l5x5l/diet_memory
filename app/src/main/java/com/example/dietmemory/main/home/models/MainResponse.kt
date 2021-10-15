package com.example.dietmemory.main.home.models

data class MainResponse (val Data : NutrientData, val Exer : ArrayList<ExerData>, val Food : ArrayList<FoodData>, val isSuccess : Boolean, val message : String)