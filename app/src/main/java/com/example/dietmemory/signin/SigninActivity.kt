package com.example.dietmemory.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.databinding.ActivitySigninBinding
import com.example.dietmemory.signin.detail.SigninDetailFragment
import com.example.dietmemory.signin.personal.SigninPersonalFragment

class SigninActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate), SigninContract.View{

    private val fragmentList = ArrayList<Fragment>()
    private val presenter = SigninPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.takeView(this)

        setFragments()

        setButtons()

    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    private fun setButtons(){
        binding.btnNext.setOnClickListener {
            binding.btnNext.visibility = View.GONE
            binding.btnComplete.visibility = View.VISIBLE
            binding.btnPrev.setTextColor(ContextCompat.getColor(this, R.color.dark_green))
            supportFragmentManager.beginTransaction().hide(fragmentList[0]).commit()
            supportFragmentManager.beginTransaction().show(fragmentList[1]).commit()
        }
        binding.btnPrev.setOnClickListener {
            binding.btnComplete.visibility = View.GONE
            binding.btnNext.visibility = View.VISIBLE
            binding.btnPrev.setTextColor(ContextCompat.getColor(this, R.color.gray))
            supportFragmentManager.beginTransaction().hide(fragmentList[1]).commit()
            supportFragmentManager.beginTransaction().show(fragmentList[0]).commit()
        }
        binding.btnComplete.setOnClickListener {
            presenter.trySendSignInData()
            finish()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setFragments() {
        fragmentList.add(SigninPersonalFragment(presenter))
        fragmentList.add(SigninDetailFragment(presenter))

        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, fragmentList[1]).commit()
        supportFragmentManager.beginTransaction().hide(fragmentList[1]).commit()
        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, fragmentList[0]).commit()
    }

    override fun applyEmailConfirmResult(confirm: Boolean) {
        if (confirm){
            Toast.makeText(this, "이메일 확인 완료!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "중복된 이메일입니다", Toast.LENGTH_SHORT).show()
        }
    }

    override fun applySignUpResult(result: Boolean) {
        if (result) {
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "정보를 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
    }
}