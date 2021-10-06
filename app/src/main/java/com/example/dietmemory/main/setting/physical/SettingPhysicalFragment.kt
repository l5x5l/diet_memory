package com.example.dietmemory.main.setting.physical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietmemory.databinding.FragmentMainSettingPhysicalBinding
import com.example.dietmemory.main.setting.SettingRvView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingPhysicalFragment(private val inputView : SettingRvView) : BottomSheetDialogFragment() {
    private var _binding : FragmentMainSettingPhysicalBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainSettingPhysicalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            inputView.showFragment(4)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}