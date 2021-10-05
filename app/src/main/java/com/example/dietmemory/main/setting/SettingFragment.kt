package com.example.dietmemory.main.setting

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.adapter.SettingAdapter

class SettingFragment : BaseFragment<FragmentMainSettingBinding>(FragmentMainSettingBinding::bind, R.layout.fragment_main_setting), SettingRvView{

    private val settingList = arrayListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingList.add(getString(R.string.app_setting))
        settingList.add(getString(R.string.physical_setting))
        settingList.add(getString(R.string.supplement_and_medicine))
        settingList.add(getString(R.string.restrict_food_setting))

        binding.rvDetail.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvDetail.adapter = SettingAdapter(activity as MainActivity, this, settingList)
    }

    override fun showDialog(idx: Int) {

    }
}