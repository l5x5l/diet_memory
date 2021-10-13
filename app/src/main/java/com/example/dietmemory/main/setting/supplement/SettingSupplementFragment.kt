package com.example.dietmemory.main.setting.supplement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietmemory.databinding.FragmentMainSettingSupplementBinding
import com.example.dietmemory.main.setting.SettingDetailContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingSupplementFragment : BottomSheetDialogFragment(), SettingDetailContract.View {
    private var _binding : FragmentMainSettingSupplementBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainSettingSupplementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun save() {
        // some save function
    }
}