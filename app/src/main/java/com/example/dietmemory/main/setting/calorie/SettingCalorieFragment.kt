package com.example.dietmemory.main.setting.calorie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingCalorieBinding
import com.example.dietmemory.main.setting.SettingDetailContract

class SettingCalorieFragment : BaseFragment<FragmentMainSettingCalorieBinding>(FragmentMainSettingCalorieBinding::bind, R.layout.fragment_main_setting_calorie), SettingDetailContract.View {
    private lateinit var viewModel : SettingCalorieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingCalorieViewModel::class.java)

        val calorieObserver = Observer<Int>{ liveData ->
            binding.etCalorie.setText(liveData.toString())
        }
        val proteinObserver = Observer<Int> { liveData ->
            binding.tvProteinValue.text = liveData.toString()
        }
        val carboObserver = Observer<Int> { liveData ->
            binding.tvCarboValue.text = liveData.toString()
        }
        val fatObserver = Observer<Int> { liveData ->
            binding.tvFatValue.text = liveData.toString()
        }

        viewModel.calorie.observe(viewLifecycleOwner, calorieObserver)
        viewModel.carbo.observe(viewLifecycleOwner, carboObserver)
        viewModel.protein.observe(viewLifecycleOwner, proteinObserver)
        viewModel.fat.observe(viewLifecycleOwner, fatObserver)

        setBtn()
        //setEditText()

        viewModel.getCalorie()
    }

    override fun save() {
        viewModel.setCalorie()
    }

    private fun setBtn(){
        binding.btnCalorieDecrease.setOnClickListener {
            viewModel.changeCalorieBtn(false)
        }
        binding.btnCalorieIncrease.setOnClickListener {
            viewModel.changeCalorieBtn(true)
        }
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // observer 에서 editText text 변경 -> setEditText 의 afterTextChanged 호출
    // -> 다시 observer 호출 -> setEditText 의 afterTextChanged 다시 호출 -> 한무 반복
    /*private fun setEditText(){
        binding.etCalorie.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().toIntOrNull() == null){
                    viewModel.changeCalorie(3000)
                    //binding.etCalorie.setText(viewModel.calorie.value.toString())
                } else {
                    Log.d("test", "${s.toString().toIntOrNull()}")
                    //testFunction(s.toString())
                   //viewModel.changeCalorie(s.toString().toInt())
                }
                //viewModel.changeCalorie(s.toString().toInt())
            }
        })
    }*/
}