package com.example.dietmemory.main.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dietmemory.databinding.ItemMainEtcFoodBinding
import com.example.dietmemory.main.calendar.models.CalendarFood

class PhotoAdapter(private val context : Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val dataList = ArrayList<CalendarFood>()
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemMainEtcFoodBinding

    class ViewHolder(binding : ItemMainEtcFoodBinding) : RecyclerView.ViewHolder(binding.root){
        val imageView = binding.ivFood
        val foodName = binding.tvFood
        val background = binding.background
        val mainLayout = binding.layoutMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainEtcFoodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].fileURL).into(holder.imageView)
        holder.foodName.text = dataList[position].foodName
        holder.mainLayout.setOnClickListener {
            if (holder.background.visibility == View.GONE){
                holder.background.visibility = View.VISIBLE
                holder.foodName.visibility = View.VISIBLE
            } else {
                holder.background.visibility = View.GONE
                holder.foodName.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<CalendarFood>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }
}