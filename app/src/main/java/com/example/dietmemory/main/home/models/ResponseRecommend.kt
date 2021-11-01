package com.example.dietmemory.main.home.models

data class ResponseRecommend(val isSuccess : Boolean, val message : String, val food : ArrayList<RecommendFood>)
