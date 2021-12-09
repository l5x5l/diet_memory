package com.example.dietmemory.add.food.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.databinding.ItemAddFoodListBinding

class foodAdapter(private val view : FoodAdapterView) : RecyclerView.Adapter<foodAdapter.ViewHolder>(){

    private val dataList = ArrayList<String>()

    class ViewHolder(binding : ItemAddFoodListBinding) : RecyclerView.ViewHolder(binding.root){
        val root = binding.root
        val name = binding.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddFoodListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataList[position]
        holder.root.setOnClickListener {
            view.applyFood(dataList[position])
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(newData : ArrayList<String>){
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

    fun applyTempData(){
        dataList.add("사과")
        dataList.add("닭가슴살")
        dataList.add("감자볶음")
        notifyDataSetChanged()
    }
}