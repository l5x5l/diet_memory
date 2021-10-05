package com.example.dietmemory.main.home

import com.example.dietmemory.config.GlobalApplication

class HomeModel {
    private var waterIntake = GlobalApplication.globalSharedPreferences.getInt("water", 0)

    fun intakeWater(amount : Int) : Int {
        waterIntake += amount
        // global application 의 sp 수정은 아직
        return waterIntake
    }

}