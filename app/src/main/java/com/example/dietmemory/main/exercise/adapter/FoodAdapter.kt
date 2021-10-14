package com.example.dietmemory.main.exercise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dietmemory.data.SummaryFoodData
import com.example.dietmemory.databinding.ItemMainEtcFoodBinding

class FoodAdapter(private val context: Context) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private val showState = ArrayList<Boolean>()
    private val dataList = ArrayList<SummaryFoodData>()
    private lateinit var binding : ItemMainEtcFoodBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ViewHolder(binding : ItemMainEtcFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        val layoutMain = binding.layoutMain
        val foodName = binding.tvFood
        val foodImage = binding.ivFood
        val background = binding.background
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainEtcFoodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layoutMain.setOnClickListener {

        }
        holder.foodName.text = dataList[position].foodName
        Glide.with(context).load(dataList[position].foodImage).into(holder.foodImage)
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<SummaryFoodData>){
        dataList.clear()
        showState.clear()
        dataList.addAll(inputData)
        for (i in 0 until dataList.size){
            showState.add(false)
        }
        notifyDataSetChanged()
    }
}