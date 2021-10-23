package com.example.dietmemory.main.setting.app

import android.os.Bundle
import android.view.View
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentMainSettingAppBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.SettingDetailContract

class SettingAppFragment : BaseFragment<FragmentMainSettingAppBinding>(FragmentMainSettingAppBinding::bind, R.layout.fragment_main_setting_app), SettingDetailContract.View{

    private var checkedIdx = GlobalApplication.globalSharedPreferences.getInt(GlobalApplication.UNIT_TYPE, 1)
    private var useCal = GlobalApplication.globalSharedPreferences.getBoolean(GlobalApplication.SHOW_CAL, true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (GlobalApplication.globalSharedPreferences.getBoolean(GlobalApplication.SHOW_CAL, true)){
            binding.cbCalorieYes.isChecked = true
        } else {
            binding.cbCalorieNo.isChecked = true
        }

        when (GlobalApplication.globalSharedPreferences.getInt(GlobalApplication.UNIT_TYPE, 1)){
            1 -> { binding.cbChangeUnit1.isChecked = true }
            2 -> { binding.cbChangeUnit2.isChecked = true }
            3 -> { binding.cbChangeUnit3.isChecked = true }
            4 -> { binding.cbChangeUnit4.isChecked = true }
            else -> { binding.cbChangeUnit1.isChecked = true }
        }

        setBtn()
    }

    // 무지성
    private fun setBtn() {
        // checkbox 를 radio button 처럼 사용 (디자인 때문에)
        binding.cbCalorieYes.setOnClickListener {
            binding.cbCalorieYes.isChecked = true
            binding.cbCalorieNo.isChecked = false
            useCal = true
        }

        binding.cbCalorieNo.setOnClickListener {
            binding.cbCalorieYes.isChecked = false
            binding.cbCalorieNo.isChecked = true
            useCal = false
        }

        binding.cbChangeUnit1.setOnClickListener {
            binding.cbChangeUnit1.isChecked = true
            binding.cbChangeUnit2.isChecked = false
            binding.cbChangeUnit3.isChecked = false
            binding.cbChangeUnit4.isChecked = false
            checkedIdx = 1
        }
        binding.cbChangeUnit2.setOnClickListener {
            binding.cbChangeUnit1.isChecked = false
            binding.cbChangeUnit2.isChecked = true
            binding.cbChangeUnit3.isChecked = false
            binding.cbChangeUnit4.isChecked = false
            checkedIdx = 2
        }
        binding.cbChangeUnit3.setOnClickListener {
            binding.cbChangeUnit1.isChecked = false
            binding.cbChangeUnit2.isChecked = false
            binding.cbChangeUnit3.isChecked = true
            binding.cbChangeUnit4.isChecked = false
            checkedIdx = 3
        }
        binding.cbChangeUnit4.setOnClickListener {
            binding.cbChangeUnit1.isChecked = false
            binding.cbChangeUnit2.isChecked = false
            binding.cbChangeUnit3.isChecked = false
            binding.cbChangeUnit4.isChecked = true
            checkedIdx = 4
        }

    }

    override fun save() {
        val editor = GlobalApplication.globalSharedPreferences.edit()
        editor.putBoolean(GlobalApplication.SHOW_CAL, useCal).apply()
        editor.putInt(GlobalApplication.UNIT_TYPE, checkedIdx).apply()
        (activity as MainActivity).applyShowCal()
    }
}