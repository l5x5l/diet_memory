package com.example.dietmemory.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dietmemory.databinding.ItemRecommendFoodBinding
import com.example.dietmemory.main.home.models.RecommendFood

class FoodRecommendAdapter(private val context : Context) : RecyclerView.Adapter<FoodRecommendAdapter.ViewHolder>() {

    private lateinit var binding : ItemRecommendFoodBinding
    private val dataList = ArrayList<RecommendFood>()
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ViewHolder(binding : ItemRecommendFoodBinding) : RecyclerView.ViewHolder(binding.root){
        val main = binding.itemMain
        val image = binding.ivPhoto
        val foodName = binding.tvFoodName
        val calorie = binding.tvCalorie
        val backgroundView = binding.viewBackground
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemRecommendFoodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.main.setOnClickListener{
            if (holder.backgroundView.visibility == View.GONE){
                holder.backgroundView.visibility = View.VISIBLE
                holder.foodName.visibility = View.VISIBLE
                holder.calorie.visibility = View.VISIBLE
            } else {
                holder.backgroundView.visibility = View.GONE
                holder.foodName.visibility = View.GONE
                holder.calorie.visibility = View.GONE
            }
        }
        Glide.with(context).load(dataList[position].fileURL).into(holder.image)
        holder.foodName.text = dataList[position].foodName
        holder.calorie.text = dataList[position].calo.toString()
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<RecommendFood>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }
}