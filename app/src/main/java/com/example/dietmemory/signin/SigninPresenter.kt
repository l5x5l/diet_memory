package com.example.dietmemory.signin

class SigninPresenter : SigninContract.Presenter {

    private var view : SigninContract.View ?= null

    override fun tryEmailConfirm() {
        // retrofit 이용해서 서버 통신 후 결과
        // if response is successful
        view!!.applyEmailConfirmResult(true)
    }

    override fun trySendSignInData() {
        // 회원가입 완료 버튼 클릭 이벤트
    }

    override fun takeView(inputView: SigninContract.View) {
        view = inputView
    }

    override fun dropView() {
        view = null
    }
}