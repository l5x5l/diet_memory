package com.example.dietmemory.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.ActivitySplashBinding
import com.example.dietmemory.login.LoginActivity
import com.example.dietmemory.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인이 안된 경우
        if (GlobalApplication.globalSharedPreferences.getString(GlobalApplication.X_ACCESS_TOKEN, null) == null){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 1500)
        } else { // 로그인이 된 경우
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1500)
        }
    }
}