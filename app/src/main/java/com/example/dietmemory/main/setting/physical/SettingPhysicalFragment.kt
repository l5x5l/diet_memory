package com.example.dietmemory.main.setting.physical

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingPhysicalBinding
import com.example.dietmemory.main.setting.SettingDetailContract

class SettingPhysicalFragment : BaseFragment<FragmentMainSettingPhysicalBinding>(FragmentMainSettingPhysicalBinding::bind, R.layout.fragment_main_setting_physical), SettingDetailContract.View {

    private lateinit var viewModel : SettingPhysicalViewmodel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingPhysicalViewmodel::class.java)

        val exerciseObserver : Observer<Float> = Observer { liveData ->
            binding.tvExerciseIndexValue.text = liveData.toString()
        }
        val heightObserver : Observer<Int> = Observer { liveData ->
            binding.tvHeightValue.text = liveData.toString()
        }
        val weightObserver : Observer<Int> = Observer { liveData ->
            binding.tvWeightValue.text = liveData.toString()
        }


        viewModel.physicalExercise.observe(viewLifecycleOwner, exerciseObserver)
        viewModel.physicalHeight.observe(viewLifecycleOwner, heightObserver)
        viewModel.physicalWeight.observe(viewLifecycleOwner, weightObserver)

        viewModel.getPhysicalInfo()

        setBtn()
    }

    private fun setBtn() {
        binding.btnHeightDelete.setOnClickListener {
            viewModel.changeHeight(false)
        }
        binding.btnHeightPlus.setOnClickListener {
            viewModel.changeHeight(true)
        }
        binding.btnWeightDelete.setOnClickListener {
            viewModel.changeWeight(false)
        }
        binding.btnWeightPlus.setOnClickListener {
            viewModel.changeWeight(true)
        }
        binding.btnExerciseDelete.setOnClickListener {
            viewModel.changeExercise(false)
        }
        binding.btnExercisePlus.setOnClickListener {
            viewModel.changeExercise(true)
        }
    }

    override fun save() {
        viewModel.setPhysicalInfo()
    }
}