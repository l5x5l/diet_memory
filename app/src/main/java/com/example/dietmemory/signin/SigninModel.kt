package com.example.dietmemory.signin

import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.data.SigninUserData

class SigninModel {
    private val userData = SigninUserData()

    fun setExercise(index : Int){
        userData.exercise = index
    }

    fun setEmail(email : String) {
        userData.email = email
    }

    fun setName(name : String) {
        userData.name = name
    }

    fun setPwd(pwd : String) {
        userData.password = pwd
    }

    fun setPhoneNum(phoneNum : String){
        userData.phoneNum = phoneNum
    }

    fun setHeight(height : Int){
        userData.height = height
    }

    fun setWeight(weight : Int){
        userData.weight = weight
    }
}