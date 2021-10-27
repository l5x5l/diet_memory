package com.example.dietmemory.add.exercise

import android.util.Log
import com.example.dietmemory.add.exercise.models.ExerResponse
import com.example.dietmemory.add.exercise.models.PostExer
import com.example.dietmemory.config.GlobalApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExercisePresenter : ExerciseContract.Presenter {

    private var view : ExerciseContract.View ?= null
    private var exerciseName : String = ""
    private var exerciseCal : Int = 0
    private var _time : Int = 0

    override fun tryPostExer(fileURL: String) {
        val retrofitInterface = GlobalApplication.sRetrofit.create(ExerciseRetrofitInterface::class.java)
        retrofitInterface.postExercise(PostExer(exerciseName, GlobalApplication.year, GlobalApplication.month, GlobalApplication.day, _time, fileURL)).enqueue(object : Callback<ExerResponse>{
            override fun onResponse(call: Call<ExerResponse>, response: Response<ExerResponse>) {
                if (response.isSuccessful){
                    view!!.applyPostExer(response.body()!!.isSuccess)
                }
            }

            override fun onFailure(call: Call<ExerResponse>, t: Throwable) {
                Log.d("tryPostExer", "onFailure...")
            }

        })
    }

    override fun takeView(inputView: ExerciseContract.View) {
        view = inputView
    }

    override fun dropView() {
        view = null
    }

    override fun updateTime(isPlus : Boolean){
        if (isPlus){
            _time += 10
        } else {
            if (_time >= 10){
                _time -= 10
            }
        }
        view!!.applyTimeChange(_time, exerciseCal)
    }

    override fun changeExercise(exName: String, cal: Int) {
        exerciseName = exName
        exerciseCal = cal
        view!!.applyExerChange(exName, _time, exerciseCal)
    }
}