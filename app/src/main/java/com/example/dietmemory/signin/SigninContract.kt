package com.example.dietmemory.signin

import com.example.dietmemory.config.BasePresenter

interface SigninContract {
    interface View {
        fun applyEmailConfirmResult(confirm : Boolean)
        fun applySignUpResult(result : Boolean)
    }
    interface Presenter : BasePresenter<View>{
        fun tryEmailConfirm(email : String)
        fun trySendSignInData() // presenter 클래스의 데이터 사용
    }
}