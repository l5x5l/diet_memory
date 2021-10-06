package com.example.dietmemory.main.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.adapter.SettingAdapter
import com.example.dietmemory.main.setting.app.SettingAppFragment
import com.example.dietmemory.main.setting.base.SettingBaseFragment
import com.example.dietmemory.main.setting.physical.SettingPhysicalFragment
import com.example.dietmemory.main.setting.restrict.SettingRestrictFragment
import com.example.dietmemory.main.setting.supplement.SettingSupplementFragment

class SettingFragment : BaseFragment<FragmentMainSettingBinding>(FragmentMainSettingBinding::bind, R.layout.fragment_main_setting), SettingRvView{

    private lateinit var currentFragment : Fragment
    private var settingBaseFragment : SettingBaseFragment ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingBaseFragment = SettingBaseFragment(this)
        currentFragment = settingBaseFragment!!
        childFragmentManager.beginTransaction().add(binding.layoutTarget.id, settingBaseFragment!!).commit()

    }

    override fun showFragment(idx: Int) {
        currentFragment = when(idx){
            0 -> { SettingAppFragment(this) }
            1 -> { SettingPhysicalFragment(this) }
            2 -> {SettingSupplementFragment(this)}
            3 -> {SettingRestrictFragment(this)}
            else -> {settingBaseFragment!!}
        }
        childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, currentFragment).commit()
    }
}