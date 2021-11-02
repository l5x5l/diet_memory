package com.example.dietmemory.main.setting.supplement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dietmemory.databinding.BottomAddSupplementBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SupplementBottomSheet(private val inputView : SettingSupplementFragment) : BottomSheetDialogFragment() {
    private var _binding : BottomAddSupplementBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        _binding = BottomAddSupplementBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener{
            dismiss()
        }

        binding.btnConfirm.setOnClickListener{
            inputView.addSupplement(binding.etName.text.toString())
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}