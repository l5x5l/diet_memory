package com.example.dietmemory.main.setting.supplement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietmemory.databinding.FragmentMainSettingSupplementBinding
import com.example.dietmemory.main.setting.SettingRvView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingSupplementFragment(private val inputView : SettingRvView) : BottomSheetDialogFragment() {
    private var _binding : FragmentMainSettingSupplementBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainSettingSupplementBinding.inflate(inflater, container, false)
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