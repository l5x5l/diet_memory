package com.example.dietmemory.add.food

import android.util.Log
import com.example.dietmemory.add.food.models.*
import com.example.dietmemory.config.GlobalApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodPresenter : FoodContract.Presenter {

    private var view : FoodContract.View?= null
    private var lastFoodIdx : Int = -1
    private var uri : String? = null
    private val retrofitInterface = GlobalApplication.sRetrofit.create(FoodRetrofitInterface::class.java)

    override fun tryGetFoodRecord(fileUrl: String) {
        uri = fileUrl
        val retrofitInterface = GlobalApplication.sRetrofit.create(FoodRetrofitInterface::class.java)
        retrofitInterface.postFoodRecord(PostFoodRecord(fileUrl)).enqueue(object:Callback<FoodRecordResponse>{
            override fun onResponse(call: Call<FoodRecordResponse>, response: Response<FoodRecordResponse>) {
                if (response.isSuccessful){
                    if (response.body()?.isSuccess == "true"){
                        view!!.applyFoodRecord(response.body()!!.Food)
                        lastFoodIdx = response.body()!!.Food.idx
                    } else {
                        view!!.applyFoodRecord(null)
                        Log.d("food record", "server's error?")
                    }
                }
                else {
                    view!!.applyFoodRecord(null)
                    Log.d("food record", "onFailure")
                }
            }

            override fun onFailure(call: Call<FoodRecordResponse>, t: Throwable) {
                view!!.applyFoodRecord(null)
                Log.d("food record onFailure", t.toString())
            }

        })
    }

    override fun tryPostAddFood(intakeTime: Int, foodName: String) {
        if (uri != null){
            val retrofitInterface = GlobalApplication.sRetrofit.create(FoodRetrofitInterface::class.java)
            retrofitInterface.postAddFood(PostAddFood(GlobalApplication.year, GlobalApplication.month + 1, GlobalApplication.day, intakeTime, uri!!, foodName)).enqueue(object : Callback<AddFoodResponse>{
                override fun onResponse(call: Call<AddFoodResponse>, response: Response<AddFoodResponse>) {
                    if (response.isSuccessful) {
                        view!!.applyPostAddFood(response.body()!!.isSuccess)
                    } else {
                        view!!.applyPostAddFood(false)
                    }
                }

                override fun onFailure(call: Call<AddFoodResponse>, t: Throwable) {
                    Log.d("tryPostAddFood", "onFailure...")
                }
            })
        } else {
            val retrofitInterface = GlobalApplication.sRetrofit.create(FoodRetrofitInterface::class.java)
            retrofitInterface.postAddFood(PostAddFood(GlobalApplication.year, GlobalApplication.month + 1, GlobalApplication.day, intakeTime, "null", foodName)).enqueue(object : Callback<AddFoodResponse>{
                override fun onResponse(call: Call<AddFoodResponse>, response: Response<AddFoodResponse>) {
                    if (response.isSuccessful) {
                        view!!.applyPostAddFood(response.body()!!.isSuccess)
                    } else {
                        view!!.applyPostAddFood(false)
                    }
                }

                override fun onFailure(call: Call<AddFoodResponse>, t: Throwable) {
                    Log.d("tryPostAddFood", "onFailure...")
                }
            })
        }
    }

    override fun tryGetFoodAuto(word: String) {
        retrofitInterface.getAutoFood(word).enqueue(object : Callback<FoodAutoResponse>{
            override fun onResponse(call: Call<FoodAutoResponse>, response: Response<FoodAutoResponse>) {
                if (response.isSuccessful){
                    val tempList = arrayListOf<String>()
                    for (food in response.body()!!.Name){
                        tempList.add(food.foodName)
                    }
                    view!!.applyFoodAuto(tempList)
                }
            }

            override fun onFailure(call: Call<FoodAutoResponse>, t: Throwable) {
                Log.d("tryGetFoodAuto", "onFailure...")
            }

        })

    }

    override fun tryGetFoodInfo(foodName: String) {
        retrofitInterface.getFoodInfo(foodName).enqueue(object : Callback<FoodInfoResponse>{
            override fun onResponse(call: Call<FoodInfoResponse>, response: Response<FoodInfoResponse>) {
                view!!.applyFoodInfo(response.body()!!)
            }

            override fun onFailure(call: Call<FoodInfoResponse>, t: Throwable) {
                Log.d("tryGetFoodInfo", "onFailure...")
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