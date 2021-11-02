package com.example.dietmemory.main.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dietmemory.databinding.ItemMainEtcExerciseBinding
import com.example.dietmemory.main.exercise.models.RecommendExercise

class ExerciseAdapter(private val context: Context) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemMainEtcExerciseBinding
    private val dataList = ArrayList<RecommendExercise>()

    class ViewHolder(binding : ItemMainEtcExerciseBinding) : RecyclerView.ViewHolder(binding.root){
        val exerImage = binding.ivExercise
        val exerName = binding.tvName
        val background = binding.background
        val layoutMain = binding.layoutMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainEtcExerciseBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.exerName.text = dataList[position].exName
        Glide.with(context).load(dataList[position].fileURL).into(holder.exerImage)
        holder.layoutMain.setOnClickListener {
            if (holder.background.visibility == View.VISIBLE){
                holder.background.visibility = View.GONE
                holder.exerName.visibility = View.GONE
            } else {
                holder.background.visibility = View.VISIBLE
                holder.exerName.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<RecommendExercise>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }
}