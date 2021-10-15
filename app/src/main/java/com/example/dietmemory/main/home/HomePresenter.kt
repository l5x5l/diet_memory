package com.example.dietmemory.main.home

import android.util.Log
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.data.FoodData
import com.example.dietmemory.main.home.models.MainResponse
import com.example.dietmemory.main.home.models.postMainData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter : HomeContract.Presenter {

    private var view : HomeContract.View ?= null
    private val model = HomeModel()

    override fun tryGetTodayData() {
        // retrofit2 호출해서 오늘 데이터 수신
        val retrofitInterface = GlobalApplication.sRetrofit.create(HomeRetrofitInterface::class.java)
        retrofitInterface.postMain(postMainData(2021, 10, 5)).enqueue(object:Callback<MainResponse>{
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                if (response.isSuccessful){
                    Log.d("main success", response.body()!!.message)
                    view!!.applyTodayData(response.body()!!)
                } else {
                    Log.d("main fail", response.body()!!.message)
                }
            }
            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.d("main onFailure", t.toString())
            }
        })

        //val tempDataList = arrayListOf(FoodData("라면", "점심", "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202108/13/a6f247a0-1d66-47a5-9dae-980e1f9c4eba.jpg"))

        //view!!.applyTodayData(tempDataList)
        view!!.applyWaterIntake(model.intakeWater(0), 0)
    }

    override fun changeWaterIntake(intakeType: Int) {
        val water = when (intakeType){
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