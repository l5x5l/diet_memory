package com.example.dietmemory.main.setting.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietmemory.databinding.DialogMainSettingAppBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AppDialog : BottomSheetDialogFragment(){
    private var _binding : DialogMainSettingAppBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogMainSettingAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}