package com.example.dietmemory.main.setting.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentMainSettingBaseBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.SettingRvView
import com.example.dietmemory.main.setting.adapter.SettingAdapter

class SettingBaseFragment(private val inputView : SettingRvView) : BaseFragment<FragmentMainSettingBaseBinding>(FragmentMainSettingBaseBinding::bind, R.layout.fragment_main_setting_base) {

    private val settingList = arrayListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingList.clear()
        settingList.add(getString(R.string.app_setting))
        settingList.add(getString(R.string.physical_setting))
        settingList.add(getString(R.string.supplement_and_medicine))
        settingList.add(getString(R.string.restrict_food_setting))

        binding.rvDetail.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvDetail.adapter = SettingAdapter(activity as MainActivity, inputView, settingList)

        binding.btnLogout.setOnClickListener {
            GlobalApplication.globalSharedPreferences.edit().remove(GlobalApplication.X_ACCESS_TOKEN).apply()
            (activity as MainActivity).afterLogout()
        }
    }
}