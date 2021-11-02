package com.example.dietmemory.main.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dietmemory.R
import com.example.dietmemory.databinding.ItemMainEtcUserExerBinding
import com.example.dietmemory.main.exercise.models.UserExer

class UserExerciseAdapter(private val context: Context) : RecyclerView.Adapter<UserExerciseAdapter.ViewHolder>() {

    private val dataList = ArrayList<UserExer>()
    private lateinit var binding : ItemMainEtcUserExerBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ViewHolder(binding : ItemMainEtcUserExerBinding) : RecyclerView.ViewHolder(binding.root){
        val exerName = binding.tvExerName
        val exerImage = binding.ivPhoto
        val exerTime = binding.tvTime
        val calorie = binding.tvCalorie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainEtcUserExerBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].fileURL).into(holder.exerImage)
        holder.exerName.text = dataList[position].exName
        holder.exerTime.text = context.getString(R.string.value_scale_form, dataList[position].time, "분")
        holder.calorie.text = context.getString(R.string.value_scale_form, dataList[position].calo, "cal")
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<UserExer>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }
}