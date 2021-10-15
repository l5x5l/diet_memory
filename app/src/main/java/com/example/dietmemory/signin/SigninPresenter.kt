package com.example.dietmemory.signin

import android.util.Log
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.data.SigninUserData

class SigninPresenter : SigninContract.Presenter {

    private var view : SigninContract.View ?= null
    private val userData = SigninUserData()

    override fun tryEmailConfirm() {
        // retrofit 이용해서 서버 통신 후 결과
        // if response is successful
        view!!.applyEmailConfirmResult(true)
    }

    override fun trySendSignInData() {
        // 회원가입 완료 버튼 클릭 이벤트
        Log.d("signup", userData.toString())
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
}