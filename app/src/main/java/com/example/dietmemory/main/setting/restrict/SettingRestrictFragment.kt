package com.example.dietmemory.main.setting.restrict

import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingRestrictBinding
import com.example.dietmemory.main.setting.SettingDetailContract

class SettingRestrictFragment : BaseFragment<FragmentMainSettingRestrictBinding>(FragmentMainSettingRestrictBinding::bind, R.layout.fragment_main_setting_restrict), SettingDetailContract.View {

    override fun save() {
        // some save function
    }
}