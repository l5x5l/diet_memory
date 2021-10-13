package com.example.dietmemory.login.models

data class LoginResponse(val isSuccess : Boolean, val message : String, val userIdx : Int, val access_token : String)
