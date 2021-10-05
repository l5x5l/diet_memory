package com.example.dietmemory.add

import android.os.Bundle
import com.example.dietmemory.R
import com.example.dietmemory.add.food.FoodFragment
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.databinding.ActivityAddBinding

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnCancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.to_bottom)
        }

        supportFragmentManager.beginTransaction().add(binding.layoutTarget.id, FoodFragment()).commit()
    }
}