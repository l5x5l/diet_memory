package com.example.dietmemory.add

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.dietmemory.R
import com.example.dietmemory.add.exercise.ExerciseFragment
import com.example.dietmemory.add.food.FoodFragment
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.databinding.ActivityAddBinding

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate){

    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentList.add(FoodFragment())
        fragmentList.add(ExerciseFragment())

        binding.btnCancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.to_bottom)
        }

        binding.btnFood.setOnClickListener {
            binding.btnFood.background = ContextCompat.getDrawable(this, R.drawable.shape_stroke_black_radius_4)
            binding.btnFood.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.btnExercise.background = ContextCompat.getDrawable(this, R.drawable.shape_stroke_gray_radius_4)
            binding.btnExercise.setTextColor(ContextCompat.getColor(this, R.color.gray))
            supportFragmentManager.beginTransaction().hide(fragmentList[1]).commit()
            supportFragmentManager.beginTransaction().show(fragmentList[0]).commit()
        }

        binding.btnExercise.setOnClickListener {
            binding.btnExercise.background = ContextCompat.getDrawable(this, R.drawable.shape_stroke_black_radius_4)
            binding.btnExercise.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.btnFood.background = ContextCompat.getDrawable(this, R.drawable.shape_stroke_gray_radius_4)
            binding.btnFood.setTextColor(ContextCompat.getColor(this, R.color.gray))
            supportFragmentManager.beginTransaction().hide(fragmentList[0]).commit()
            supportFragmentManager.beginTransaction().show(fragmentList[1]).commit()
        }

        supportFragmentManager.beginTransaction().add(binding.layoutTarget.id, fragmentList[1]).commit()
        supportFragmentManager.beginTransaction().hide(fragmentList[1]).commit()
        supportFragmentManager.beginTransaction().add(binding.layoutTarget.id, fragmentList[0]).commit()
    }
}