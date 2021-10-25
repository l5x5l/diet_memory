package com.example.dietmemory.signin.personal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentSigninPersonalBinding
import com.example.dietmemory.signin.SigninActivity
import com.example.dietmemory.signin.SigninPresenter

class SigninPersonalFragment(private val presenter : SigninPresenter) : BaseFragment<FragmentSigninPersonalBinding>(FragmentSigninPersonalBinding::bind, R.layout.fragment_signin_personal) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEditText()

        binding.rgSex.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_man) {
                presenter.setSex(0)
            } else {
                presenter.setSex(1)
            }
        }

        binding.btnCheckDuplicate.setOnClickListener {
            if (checkEmail(binding.etEmail.text.toString())){
                Log.d("email", binding.etEmail.text.toString())
                presenter.tryEmailConfirm(binding.etEmail.text.toString())
            }
        }

    }

    private fun setEditText() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (checkEmail(s.toString())) {
                    binding.etEmail.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                    binding.btnCheckDuplicate.background = ContextCompat.getDrawable(activity as SigninActivity, R.drawable.shape_stroke_darkgreen_radius_4)
                    binding.btnCheckDuplicate.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                    presenter.setEmail(GlobalApplication.STRING_DEFAULT) // 인증은 한 상태에서 이메일을 변경할 경우 자동으로 초기화
                }
                else {
                    binding.etEmail.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    binding.btnCheckDuplicate.background = ContextCompat.getDrawable(activity as SigninActivity, R.drawable.shape_stroke_gray_radius_4)
                    binding.btnCheckDuplicate.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.gray))
                    presenter.setEmail(GlobalApplication.STRING_DEFAULT)
                }
            }
        })
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (checkName(s.toString())) {
                    binding.etName.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                    presenter.setName(s.toString())
                } else {
                    binding.etName.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    presenter.setName(GlobalApplication.STRING_DEFAULT)
                }

            }
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (checkPwd(s.toString())) {
                    binding.etPassword.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                    presenter.setPwd(s.toString())
                } else {
                    binding.etPassword.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    presenter.setPwd(GlobalApplication.STRING_DEFAULT)
                }
            }
        })
        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (checkPhoneNum(s.toString())) {
                    binding.etPhoneNumber.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                    presenter.setPhoneNum(s.toString())
                } else {
                    binding.etPhoneNumber.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    presenter.setPhoneNum(GlobalApplication.STRING_DEFAULT)
                }
            }
        })
        binding.etHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val height = s.toString().toIntOrNull()
                if (height != null) {
                    if (checkHeight(s.toString().toInt())) {
                        binding.etHeight.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                        presenter.setHeight(s.toString().toInt())
                    } else {
                        binding.etHeight.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                        presenter.setHeight(-1)
                    }
                } else {
                    binding.etHeight.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    presenter.setHeight(-1)
                }
            }
        })
        binding.etWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val weight = s.toString().toIntOrNull()
                if (weight != null) {
                    if (checkWeight(weight)) {
                        binding.etWeight.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                        presenter.setWeight(weight)
                    } else {
                        binding.etWeight.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                        presenter.setWeight(-1)
                    }
                } else {
                    binding.etWeight.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    presenter.setWeight(-1)
                }
            }
        })
        binding.etAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val age = s.toString().toIntOrNull()
                if (age != null){
                    if (checkAge(age)){
                        binding.etAge.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.dark_green))
                        presenter.setAge(age)
                    } else {
                        binding.etAge.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                        presenter.setAge(-1)
                    }
                } else {
                    binding.etAge.setTextColor(ContextCompat.getColor(activity as SigninActivity, R.color.orange))
                    presenter.setAge(-1)
                }
            }

        })
    }

    // set EditText conditions
    private fun checkEmail(email : String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkPhoneNum(phoneNum : String) : Boolean {
        return phoneNum.matches(Regex("^[0-9]{3}[0-9]{4}[0-9]{4}\$"))
    }

    private fun checkName(name : String) : Boolean {
        return name.matches(Regex("^[0-9A-Za-z가-힣ㄱ-하-ㅣ]{2,10}$"))
    }

    private fun checkPwd(pwd : String) : Boolean {
        return pwd.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"))
    }

    private fun checkHeight(height : Int) : Boolean {
        return (height in 70..300)
    }

    private fun checkWeight(weight: Int) : Boolean {
        return (weight in 20..1000)
    }

    private fun checkAge(age : Int) : Boolean {
        return (age in 7..130)
    }
}