package com.example.dietmemory.main.exercise

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainExerciseBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.exercise.adapter.ExerciseAdapter
import com.example.dietmemory.main.exercise.adapter.ExerciseDecoration
import com.example.dietmemory.main.exercise.adapter.UserExerciseAdapter
import com.example.dietmemory.main.exercise.models.RecommendExercise
import com.example.dietmemory.main.exercise.models.UserExer

class ExerciseFragment : BaseFragment<FragmentMainExerciseBinding>(FragmentMainExerciseBinding::bind, R.layout.fragment_main_exercise), ExerciseContract.View {

    private val presenter = ExercisePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        binding.rvExercise.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvExercise.adapter = UserExerciseAdapter(activity as MainActivity)

        binding.rvTotalExercise.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTotalExercise.adapter = ExerciseAdapter(activity as MainActivity)
        binding.rvTotalExercise.addItemDecoration(ExerciseDecoration(activity as MainActivity))

        binding.viewAddInduce.tvDietEmpty.text = getString(R.string.exercise_empty)
        binding.viewAddInduce.tvDietEmpty2.text = getString(R.string.exercise_empty2)

        presenter.tryGetExer()
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }

    override fun applyExer(isSuccess: Boolean, message: String, exer: ArrayList<RecommendExercise>?, userExer: ArrayList<UserExer>?) {
        if (isSuccess){
            (binding.rvTotalExercise.adapter as ExerciseAdapter).applyData(exer!!)
            if (userExer == null || userExer.size == 0){
                binding.viewAddInduce.layoutMain.visibility = View.VISIBLE
                binding.viewExercise.tvCalorieValue.text = 0.toString()
            }else {
                binding.viewAddInduce.layoutMain.visibility = View.GONE
                (binding.rvExercise.adapter as UserExerciseAdapter).applyData(userExer)
                var totalCal = 0
                for (exercise in userExer) {
                    totalCal += exercise.calo
                }
                binding.viewExercise.tvCalorieValue.text = totalCal.toString()
            }
        } else {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun applyExerciseChange(){
        presenter.tryGetExer()
    }

}