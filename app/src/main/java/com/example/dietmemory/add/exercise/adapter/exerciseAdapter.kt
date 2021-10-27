package com.example.dietmemory.add.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.R
import com.example.dietmemory.add.exercise.models.ExerciseRvData
import com.example.dietmemory.databinding.ItemAddExerciseListBinding

class exerciseAdapter(private val context: Context, private val onclick : (String, Int) -> Unit) : RecyclerView.Adapter<exerciseAdapter.ViewHolder>(){

    private lateinit var binding : ItemAddExerciseListBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val exerciseList = ArrayList<ExerciseRvData>()

    init {
        exerciseList.add(ExerciseRvData("걷기", 40))
        exerciseList.add(ExerciseRvData("아령운동", 68))
        exerciseList.add(ExerciseRvData("스쿼트", 72))
        exerciseList.add(ExerciseRvData("턱걸이", 84))
        exerciseList.add(ExerciseRvData("훌라후프", 42))
    }

    class ViewHolder(binding : ItemAddExerciseListBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvExercise
        val time = binding.tvCalorieForTime
        val main = binding.layoutMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemAddExerciseListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.main.setOnClickListener {
            onclick(exerciseList[position].name, exerciseList[position].cal)
        }
        holder.name.text = exerciseList[position].name
        holder.time.text = context.getString(R.string.calorie_per_10, exerciseList[position].cal)
    }

    override fun getItemCount(): Int = exerciseList.size

    fun applyDate(newData : ArrayList<ExerciseRvData>){
        exerciseList.clear()
        exerciseList.addAll(newData)
        notifyDataSetChanged()
    }
}