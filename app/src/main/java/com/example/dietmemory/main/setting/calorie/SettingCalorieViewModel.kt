package com.example.dietmemory.main.setting.calorie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.setting.calorie.model.PostTcalo
import com.example.dietmemory.main.setting.calorie.model.ResponseTcalo
import com.example.dietmemory.main.setting.calorie.model.ResponseTcaloData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingCalorieViewModel : ViewModel() {
    var calorie = MutableLiveData<Int>()
    var protein = MutableLiveData<Int>()
    var carbo = MutableLiveData<Int>()
    var fat = MutableLiveData<Int>()

    init {
        calorie.value = 0
        protein.value = 0
        carbo.value = 0
        fat.value = 0
    }

    // network
    fun getCalorie(){
        val retrofitInterface = GlobalApplication.sRetrofit.create(SettingCalorieRetrofitInterface::class.java)
        retrofitInterface.getTcaloData().enqueue(object : Callback<ResponseTcaloData>{
            override fun onResponse(
                call: Call<ResponseTcaloData>,
                response: Response<ResponseTcaloData>
            ) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        calorie.value = response.body()!!.data.calo
                        protein.value = response.body()!!.data.protein
                        carbo.value = response.body()!!.data.carbo
                        fat.value = response.body()!!.data.fat
                    }
                }
            }
            override fun onFailure(call: Call<ResponseTcaloData>, t: Throwable) {

            }
        })
    }

    fun setCalorie(){
        val retrofitInterface = GlobalApplication.sRetrofit.create(SettingCalorieRetrofitInterface::class.java)
        retrofitInterface.postTcalo(PostTcalo(calorie.value!!)).enqueue(object : Callback<ResponseTcalo>{
            override fun onResponse(call: Call<ResponseTcalo>, response: Response<ResponseTcalo>) {
                Log.d("change goal calorie", response.isSuccessful.toString())
            }

            override fun onFailure(call: Call<ResponseTcalo>, t: Throwable) {

            }

        })
    }

    fun changeCalorieBtn(isPlus : Boolean){
        if (isPlus) {
            calorie.value = calorie.value!!.plus(10)
        } else {
            calorie.value = calorie.value!!.minus(10)
        }
        changeNutrient()
    }

    /*fun changeCalorie(input : Int){
        calorie.value = input
        changeNutrient()
    }*/

    private fun changeNutrient(){
        carbo.value = (calorie.value!!.toInt() * 0.6).toInt()
        protein.value = (calorie.value!!.toInt() * 0.14).toInt()
        fat.value = (calorie.value!!.toInt() * 0.23).toInt()
    }
}