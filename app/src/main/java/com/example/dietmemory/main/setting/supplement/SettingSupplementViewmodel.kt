package com.example.dietmemory.main.setting.supplement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.setting.supplement.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingSupplementViewmodel  : ViewModel() {
    var supplementDatas = MutableLiveData<ArrayList<EditMedicine>>()
    var resultCode = MutableLiveData<Int>()

    // network
    fun getMedicine(){
        val retrofitInterface = GlobalApplication.sRetrofit.create(SettingSupplementRetrofitInterface::class.java)
        retrofitInterface.postMedicineData(PostMedicineData(GlobalApplication.year, GlobalApplication.month, GlobalApplication.day)).enqueue(object : Callback<ResponseMedicineData>{
            override fun onResponse(call: Call<ResponseMedicineData>, response: Response<ResponseMedicineData>) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        supplementDatas.postValue(response.body()!!.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMedicineData>, t: Throwable) {

            }

        })
    }

    // network
    fun setMedicine(newData : ArrayList<EditMedicine>){
        val retrofitInterface = GlobalApplication.sRetrofit.create(SettingSupplementRetrofitInterface::class.java)
        retrofitInterface.postMedicine(PostMedicine(newData)).enqueue(object : Callback<ResponseMedicine>{
            override fun onResponse(call: Call<ResponseMedicine>, response: Response<ResponseMedicine>) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        resultCode.value = 200
                    } else {
                        resultCode.value = 500
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMedicine>, t: Throwable) {

            }

        })
    }
}