package com.example.dietmemory.main.setting.supplement

import com.example.dietmemory.main.setting.supplement.models.PostMedicine
import com.example.dietmemory.main.setting.supplement.models.PostMedicineData
import com.example.dietmemory.main.setting.supplement.models.ResponseMedicine
import com.example.dietmemory.main.setting.supplement.models.ResponseMedicineData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SettingSupplementRetrofitInterface {
    @POST("medicine/data")
    fun postMedicineData(@Body params : PostMedicineData) : Call<ResponseMedicineData>

    @POST("medicine")
    fun postMedicine(@Body params : PostMedicine) : Call<ResponseMedicine>
}