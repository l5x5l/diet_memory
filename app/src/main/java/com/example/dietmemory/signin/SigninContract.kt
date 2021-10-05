package com.example.dietmemory.signin

import com.example.dietmemory.config.BasePresenter

interface SigninContract {
    interface View {
        fun changeEmailBtn(confirm : Boolean)
    }
    interface Presenter : BasePresenter<View>{
        fun tryEmailConfirm()
        fun trySendSignInData()
    }
}