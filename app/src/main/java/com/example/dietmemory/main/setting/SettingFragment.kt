package com.example.dietmemory.main.setting

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.app.SettingAppFragment
import com.example.dietmemory.main.setting.base.SettingBaseFragment
import com.example.dietmemory.main.setting.calorie.SettingCalorieFragment
import com.example.dietmemory.main.setting.physical.SettingPhysicalFragment
import com.example.dietmemory.main.setting.restrict.SettingRestrictFragment
import com.example.dietmemory.main.setting.supplement.SettingSupplementFragment

class SettingFragment : BaseFragment<FragmentMainSettingBinding>(FragmentMainSettingBinding::bind, R.layout.fragment_main_setting), SettingRvView{

    private var currentFragment : SettingDetailContract.View ?= null
    private var settingBaseFragment : SettingBaseFragment ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingBaseFragment = SettingBaseFragment(this)
        childFragmentManager.beginTransaction().add(binding.layoutTarget.id, settingBaseFragment!!).commit()

        binding.btnCancel.setOnClickListener {
            showFragment(5, "")
        }
        binding.btnSave.setOnClickListener {
            if (currentFragment != null){
                currentFragment!!.save()
                if (currentFragment!!::class == SettingSupplementFragment::class){
                    Handler(Looper.getMainLooper()).postDelayed({
                        (activity as MainActivity).applySupplement()
                    }, 1500)
                }
            }
            showFragment(5, "")
        }
    }

    override fun showFragment(idx: Int, detail : String) {
        when(idx){
            0 -> {
                binding.tvDetail.text = getString(R.string.with_line, detail)
                binding.btnSave.visibility = View.VISIBLE
                binding.btnCancel.visibility = View.VISIBLE
                currentFragment = SettingAppFragment()
                childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, currentFragment as SettingAppFragment).commit()
            }
            1 -> {
                binding.tvDetail.text = getString(R.string.with_line, detail)
                binding.btnSave.visibility = View.VISIBLE
                binding.btnCancel.visibility = View.VISIBLE
                currentFragment = SettingPhysicalFragment()
                childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, currentFragment as SettingPhysicalFragment).commit()
            }
            2 -> {
                binding.tvDetail.text = getString(R.string.with_line, detail)
                binding.btnSave.visibility = View.VISIBLE
                binding.btnCancel.visibility = View.VISIBLE
                currentFragment = SettingSupplementFragment()
                childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, currentFragment as SettingSupplementFragment).commit()
            }
            3 -> {
                binding.tvDetail.text = getString(R.string.with_line, detail)
                binding.btnSave.visibility = View.VISIBLE
                binding.btnCancel.visibility = View.VISIBLE
                currentFragment = SettingRestrictFragment()
                childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, currentFragment as SettingRestrictFragment).commit()
            }
            4 -> {
                binding.tvDetail.text = getString(R.string.with_line, detail)
                binding.btnSave.visibility = View.VISIBLE
                binding.btnCancel.visibility = View.VISIBLE
                currentFragment = SettingCalorieFragment()
                childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, currentFragment as SettingCalorieFragment).commit()
            }
            else -> {
                binding.tvDetail.text = detail
                binding.btnSave.visibility = View.GONE
                binding.btnCancel.visibility = View.GONE
                currentFragment = null
                childFragmentManager.beginTransaction().replace(binding.layoutTarget.id, settingBaseFragment!!).commit()
            }
        }
    }
}