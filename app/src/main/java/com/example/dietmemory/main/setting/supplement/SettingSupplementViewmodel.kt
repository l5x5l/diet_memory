package com.example.dietmemory.main.setting.supplement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.setting.supplement.models.Medicine
import com.example.dietmemory.main.setting.supplement.models.PostMedicineData
import com.example.dietmemory.main.setting.supplement.models.ResponseMedicineData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingSupplementViewmodel  : ViewModel() {
    var supplementDatas = MutableLiveData<ArrayList<Medicine>>()

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
}