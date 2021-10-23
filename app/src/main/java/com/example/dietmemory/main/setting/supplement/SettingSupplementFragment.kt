package com.example.dietmemory.main.setting.supplement

import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingSupplementBinding
import com.example.dietmemory.main.setting.SettingDetailContract

class SettingSupplementFragment : BaseFragment<FragmentMainSettingSupplementBinding>(FragmentMainSettingSupplementBinding::bind, R.layout.fragment_main_setting_restrict), SettingDetailContract.View {
    override fun save() {
        // some save function
    }
}