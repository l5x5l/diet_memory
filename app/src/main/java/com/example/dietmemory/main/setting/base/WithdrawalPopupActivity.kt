package com.example.dietmemory.main.setting.base

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import androidx.core.content.ContextCompat
import com.example.dietmemory.R
import com.example.dietmemory.databinding.ActivityPopupWithdrawalBinding

class WithdrawalPopupActivity : Activity() {

    private lateinit var binding : ActivityPopupWithdrawalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityPopupWithdrawalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etWithdrawal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                changeWithdrawalBtn(s.toString() == "회원탈퇴" || s.toString() == "ghldnjsxkfxhl")
            }
        })

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnWithdrawal.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        binding.btnWithdrawal.isClickable = false

    }

    fun changeWithdrawalBtn(enable : Boolean) {
        if (enable) {
            binding.btnWithdrawal.isClickable = true
            binding.btnWithdrawal.setTextColor(ContextCompat.getColor(this, R.color.orange))
        } else {
            binding.btnWithdrawal.isClickable = false
            binding.btnWithdrawal.setTextColor(ContextCompat.getColor(this, R.color.gray))
        }
    }
}