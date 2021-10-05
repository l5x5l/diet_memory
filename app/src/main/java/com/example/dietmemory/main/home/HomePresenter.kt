package com.example.dietmemory.main.home

import com.example.dietmemory.data.FoodData

class HomePresenter : HomeContract.Presenter {

    private var view : HomeContract.View ?= null
    private val model = HomeModel()

    override fun tryGetTodayData() {
        // retrofit2 호출해서 오늘 데이터 수신

        val tempDataList = arrayListOf(FoodData("라면", "점심", "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202108/13/a6f247a0-1d66-47a5-9dae-980e1f9c4eba.jpg"))

        view!!.applyTodayData(tempDataList)
        view!!.applyWaterIntake(model.intakeWater(0), 0)
    }

    override fun changeWaterIntake(intakeType: Int) {
        var water = 0
        water = when (intakeType){
            0 -> model.intakeWater(50)
            1 -> model.intakeWater(100)
            else -> model.intakeWater(500)
        }
        view!!.applyWaterIntake(water, 0)
    }

    override fun takeView(inputView: HomeContract.View) {
        view = inputView
    }

    override fun dropView() {
        view = null
    }
}