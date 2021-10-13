package com.example.dietmemory.add.food

import android.os.Bundle
import android.view.View
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentAddFoodBinding

class FoodFragment : BaseFragment<FragmentAddFoodBinding>(FragmentAddFoodBinding::bind, R.layout.fragment_add_food) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPhoto.setOnClickListener {

        }
    }
}