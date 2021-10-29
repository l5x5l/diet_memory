package com.example.dietmemory.main.setting.physical

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.setting.physical.model.PostInfochange
import com.example.dietmemory.main.setting.physical.model.ResponseInfochange
import com.example.dietmemory.main.setting.physical.model.ResponseInfochangeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingPhysicalViewmodel : ViewModel() {
    var physicalExercise = MutableLiveData<Float>()
    var physicalWeight = MutableLiveData<Int>()
    var physicalHeight = MutableLiveData<Int>()
    var complete = MutableLiveData<Int>()           // 0 : init, 1 : success, 2 : failure

    init {
        physicalExercise.value = 0f
        physicalWeight.value = 0
        physicalHeight.value = 0
        complete.value = 0
    }

    // network
    fun setPhysicalInfo() {
        val retrofitInterface = GlobalApplication.sRetrofit.create(SettingPhysicalRetrofitInterface::class.java)
        retrofitInterface.postInfochange(PostInfochange(physicalWeight.value!!, physicalHeight.value!!, physicalExercise.value!!)).enqueue(object : Callback<ResponseInfochange>{
            override fun onResponse(
                call: Call<ResponseInfochange>,
                response: Response<ResponseInfochange>
            ) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        complete.value = 1
                    } else {
                        complete.value = 2
                    }
                } else {
                    complete.value = 2
                }
            }

            override fun onFailure(call: Call<ResponseInfochange>, t: Throwable) {
                complete.value = 2
            }
        })
    }

    fun getPhysicalInfo(){
        val retrofitInterface = GlobalApplication.sRetrofit.create(SettingPhysicalRetrofitInterface::class.java)
        retrofitInterface.postInfochangeData().enqueue(object : Callback<ResponseInfochangeData>{
            override fun onResponse(
                call: Call<ResponseInfochangeData>,
                response: Response<ResponseInfochangeData>
            ) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        physicalExercise.value = response.body()!!.data.exercise
                        physicalHeight.value = response.body()!!.data.height
                        physicalWeight.value = response.body()!!.data.weight
                    }
                }
            }

            override fun onFailure(call: Call<ResponseInfochangeData>, t: Throwable) {

            }
        })
    }

    fun changeExercise(isPlus : Boolean){
        if (isPlus) physicalExercise.value = physicalExercise.value?.plus(0.01f)
        else physicalExercise.value = physicalExercise.value?.minus(0.01f)
    }

    fun changeHeight(isPlus: Boolean){
        if (isPlus) physicalHeight.value = physicalHeight.value?.plus(1)
        else physicalHeight.value = physicalHeight.value?.minus(1)
    }

    fun changeWeight(isPlus : Boolean){
        if (isPlus) physicalWeight.value = physicalWeight.value?.plus(1)
        else physicalWeight.value = physicalWeight.value?.minus(1)
    }
}