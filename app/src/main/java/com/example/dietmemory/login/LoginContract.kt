package com.example.dietmemory.login

import com.example.dietmemory.config.BasePresenter
import com.example.dietmemory.login.models.LoginData

interface LoginContract {
    interface View {
        fun onSuccessLogin()
        fun onFailureLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun tryLogin(loginData: LoginData)
    }
}