package com.example.dietmemory.add.food

import android.util.Log
import com.example.dietmemory.add.food.models.FoodRecordResponse
import com.example.dietmemory.add.food.models.PostFoodRecord
import com.example.dietmemory.config.GlobalApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodPresenter : FoodContract.Presenter {

    private var view : FoodContract.View?= null
    private var lastFoodIdx : Int = -1

    override fun tryGetFoodRecord(fileUrl: String) {
        val retrofitInterface = GlobalApplication.sRetrofit.create(FoodRetrofitInterface::class.java)
        retrofitInterface.postFoodRecord(PostFoodRecord(fileUrl)).enqueue(object:Callback<FoodRecordResponse>{
            override fun onResponse(call: Call<FoodRecordResponse>, response: Response<FoodRecordResponse>) {
                if (response.isSuccessful){
                    if (response.body()?.isSuccess == "true"){
                        view!!.applyFoodRecord(response.body()!!.Food)
                        lastFoodIdx = response.body()!!.Food.idx
                    } else {
                        Log.d("food record", "server's error?")
                    }
                }
                else {
                    Log.d("food record", "onFailure")
                }
            }

            override fun onFailure(call: Call<FoodRecordResponse>, t: Throwable) {
                Log.d("food record onFailure", t.toString())
            }

        })
    }

    override fun takeView(inputView: FoodContract.View) {
        view = inputView
    }

    override fun dropView() {
        view = null
    }
}