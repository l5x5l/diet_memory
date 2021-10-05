package com.example.dietmemory.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.databinding.ActivitySigninBinding
import com.example.dietmemory.signin.detail.SigninDetailFragment
import com.example.dietmemory.signin.personal.SigninPersoncalFragment

class SigninActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate), SigninContract.View{

    private val fragmentList = ArrayList<Fragment>()
    private lateinit var currentPage : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragments()

    }

    private fun setFragments() {
        fragmentList.add(SigninPersoncalFragment())
        fragmentList.add(SigninDetailFragment())

        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, fragmentList[1]).commit()
        supportFragmentManager.beginTransaction().hide(fragmentList[1])
        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, fragmentList[0]).commit()
        currentPage = fragmentList[0]
    }

    override fun changeEmailBtn(confirm: Boolean) {
        if (confirm){

        }
    }
}