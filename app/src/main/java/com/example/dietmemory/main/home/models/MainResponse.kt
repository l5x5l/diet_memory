package com.example.dietmemory.main.home.models

data class MainResponse (val isSuccess : Boolean, val message : String, val userIdx : Int, val Data : NutrientData, val Exer : ArrayList<ExerData>, val Food : ArrayList<FoodData>, val Medicine : ArrayList<MedicineData>, val foodlook : ArrayList<FoodLookData>)