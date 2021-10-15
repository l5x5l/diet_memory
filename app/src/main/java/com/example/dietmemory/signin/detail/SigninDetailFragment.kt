package com.example.dietmemory.signin.detail

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentSigninDetailBinding
import com.example.dietmemory.signin.SigninContract
import com.example.dietmemory.signin.SigninPresenter

class SigninDetailFragment(private val presenter : SigninPresenter) : BaseFragment<FragmentSigninDetailBinding>(FragmentSigninDetailBinding::bind, R.layout.fragment_signin_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rgExerciseQuestion.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_exercise_question1 -> presenter.setExercise(0)
                R.id.radio_exercise_question2 -> presenter.setExercise(1)
                R.id.radio_exercise_question3 -> presenter.setExercise(2)
                else -> presenter.setExercise(3)
            }
        }

        binding.switchCalorie.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val editor = GlobalApplication.globalSharedPreferences.edit()
                editor.putBoolean("calorie", true).apply()
            } else {
                val editor = GlobalApplication.globalSharedPreferences.edit()
                editor.putBoolean("calorie", false).apply()
            }
        }
    }
}