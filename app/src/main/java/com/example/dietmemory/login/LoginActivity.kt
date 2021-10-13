package com.example.dietmemory.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.databinding.ActivityLoginBinding
import com.example.dietmemory.login.models.LoginData
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.signin.SigninActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginContract.View {

    private val presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.takeView(this)

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            presenter.tryLogin(LoginData( binding.etEmail.text.toString(), binding.etPassword.text.toString()))
        }
    }

    override fun onSuccessLogin() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onFailureLogin() {
        Toast.makeText(this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
    }
}