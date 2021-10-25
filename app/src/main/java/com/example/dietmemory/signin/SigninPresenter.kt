package com.example.dietmemory.signin

import android.util.Log
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.data.SigninUserData
import com.example.dietmemory.signin.models.ResponseEmailCheck
import com.example.dietmemory.signin.models.ResponseSignUp
import com.example.dietmemory.signin.models.postEmailCheck
import com.example.dietmemory.signin.models.postSignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninPresenter : SigninContract.Presenter {

    private var view : SigninContract.View ?= null
    private val userData = SigninUserData()

    override fun tryEmailConfirm(email : String) {
        val retrofitInterface = GlobalApplication.sRetrofit.create(SigninRetrofitInterface::class.java)
        retrofitInterface.postEmailCheck(postEmailCheck(email)).enqueue(object:Callback<ResponseEmailCheck>{
            override fun onResponse(call: Call<ResponseEmailCheck>, response: Response<ResponseEmailCheck>) {
                if (response.isSuccessful){
                    if (response.body()!!.isSuccess){
                        view!!.applyEmailConfirmResult(true)
                        setEmail(email)
                        // test
                    } else {
                        view!!.applyEmailConfirmResult(false)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseEmailCheck>, t: Throwable) {
                Log.d("tryEmailConfirm", "onFailure...")
            }

        })
    }

    override fun trySendSignInData() {
        // 무지성 비교
        if (userData.name != GlobalApplication.STRING_DEFAULT && userData.email != GlobalApplication.STRING_DEFAULT
                && userData.phoneNum != GlobalApplication.STRING_DEFAULT && userData.password != GlobalApplication.STRING_DEFAULT
                && userData.height != -1 && userData.weight != -1 && userData.sex != GlobalApplication.STRING_DEFAULT && userData.exercise != -1) {
            val exercise: Float
            if (userData.sex == "woman"){
                exercise = when (userData.exercise) {
                    0 -> 1f
                    1 -> 1.12f
                    2 -> 1.27f
                    3 -> 1.45f
                    else -> 1f
                }
            } else {
                exercise = when (userData.exercise) {
                    0 -> 1f
                    1 -> 1.11f
                    2 -> 1.25f
                    3 -> 1.48f
                    else -> 1f
                }
            }
            val signUpData = postSignUp(userData.name, userData.email, userData.phoneNum, userData.password, userData.name, userData.height, userData.weight, exercise, userData.age, userData.sex)
            val retrofitInterface = GlobalApplication.sRetrofit.create(SigninRetrofitInterface::class.java)
            retrofitInterface.postSingUp(signUpData).enqueue(object : Callback<ResponseSignUp>{
                override fun onResponse(call: Call<ResponseSignUp>, response: Response<ResponseSignUp>) {
                    if (response.isSuccessful){
                        if (response.body()!!.isSuccess) {
                            view!!.applySignUpResult(true)
                        } else {
                            view!!.applySignUpResult(false)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                    Log.d("trySignUp", "onFailure...")
                }

            })
            //Log.d("signup", signUpData.toString())
        }
    }

    override fun takeView(inputView: SigninContract.View) {
        view = inputView
    }

    override fun dropView() {
        view = null
    }

    fun setExercise(index : Int){
        userData.exercise = index
    }

    fun setEmail(email : String) {
        userData.email = email
    }

    fun setName(name : String) {
        userData.name = name
    }

    fun setPwd(pwd : String) {
        userData.password = pwd
    }

    fun setPhoneNum(phoneNum : String){
        userData.phoneNum = phoneNum
    }

    fun setHeight(height : Int){
        userData.height = height
    }

    fun setWeight(weight : Int){
        userData.weight = weight
    }

    fun setSex(index : Int){
        when(index){
            0 -> userData.sex="man"
            1 -> userData.sex="woman"
            else -> userData.sex=GlobalApplication.STRING_DEFAULT
        }
    }

    fun setAge(age : Int) {
        userData.age = age
    }
}