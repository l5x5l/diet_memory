package com.example.dietmemory.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dietmemory.databinding.ItemMainHomeFoodBinding
import com.example.dietmemory.main.home.models.FoodData

class FoodAdapter(private val context: Context) : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    private val dataList = ArrayList<FoodData>()
    private lateinit var binding : ItemMainHomeFoodBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ViewHolder(binding : ItemMainHomeFoodBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.ivFood
        val foodName = binding.tvFoodName
        val time = binding.tvTime
    }

    fun applyData(inputData : ArrayList<FoodData>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainHomeFoodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].fileURL).into(holder.image)
        holder.foodName.text = dataList[position].foodName
        holder.time.text = dataList[position].time.toString()
    }

    override fun getItemCount(): Int = dataList.size
}