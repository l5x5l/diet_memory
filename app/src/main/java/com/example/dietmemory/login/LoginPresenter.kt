package com.example.dietmemory.login

import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.login.models.LoginData
import com.example.dietmemory.login.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : LoginContract.Presenter {

    private var _view : LoginContract.View ?= null
    private val view get() = _view!!


    // network
    override fun tryLogin(loginData: LoginData) {
        val loginRetrofitInterface = GlobalApplication.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postLogin2(loginData.email, loginData.pwd).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    if (response.body()?.isSuccess!!){
                        GlobalApplication.globalSharedPreferences.edit().putString(GlobalApplication.X_ACCESS_TOKEN, response.body()?.access_token).apply()
                        view.onSuccessLogin()
                    } else {
                        view.onFailureLogin()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onFailureLogin()
            }
        })
    }

    override fun takeView(inputView: LoginContract.View) {
        _view = inputView
    }

    override fun dropView() {
        _view = null
    }
}