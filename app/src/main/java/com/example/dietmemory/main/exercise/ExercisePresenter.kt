package com.example.dietmemory.main.exercise

import android.util.Log
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.main.exercise.models.PostExer
import com.example.dietmemory.main.exercise.models.ResponseExer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExercisePresenter : ExerciseContract.Presenter {

    private var _view : ExerciseContract.View ?= null
    private val view get() = _view!!

    override fun tryGetExer() {
        val retrofitInterface = GlobalApplication.sRetrofit.create(ExerciseRetrofitInterface::class.java)
        retrofitInterface.postGetExercise(PostExer(GlobalApplication.year, GlobalApplication.month + 1, GlobalApplication.day)).enqueue(object : Callback<ResponseExer>{
            override fun onResponse(call: Call<ResponseExer>, response: Response<ResponseExer>) {
                if (response.isSuccessful){
                    view.applyExer(response.body()!!.isSuccess, response.body()!!.message, response.body()!!.exer, response.body()!!.userexer)
                }
            }

            override fun onFailure(call: Call<ResponseExer>, t: Throwable) {
                Log.d("tryGetExer", "onFailure...")
            }
        })
    }

    override fun takeView(inputView: ExerciseContract.View) {
        _view = inputView
    }

    override fun dropView() {
        _view = null
    }
}