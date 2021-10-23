package com.example.dietmemory.main.setting.physical

import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingPhysicalBinding
import com.example.dietmemory.main.setting.SettingDetailContract

class SettingPhysicalFragment : BaseFragment<FragmentMainSettingPhysicalBinding>(FragmentMainSettingPhysicalBinding::bind, R.layout.fragment_main_setting_physical), SettingDetailContract.View {

    override fun save() {
        // some save function
    }
}