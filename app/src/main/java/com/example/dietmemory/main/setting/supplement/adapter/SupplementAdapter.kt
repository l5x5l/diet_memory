package com.example.dietmemory.main.setting.supplement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.databinding.ItemMainSettingSupplementBinding
import com.example.dietmemory.main.setting.supplement.models.Medicine

class SupplementAdapter(context : Context) : RecyclerView.Adapter<SupplementAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val dataList = ArrayList<Medicine>()
    private lateinit var binding: ItemMainSettingSupplementBinding

    class ViewHolder(binding : ItemMainSettingSupplementBinding) : RecyclerView.ViewHolder(binding.root){
        val supplementName = binding.tvBottomName
        val count = binding.tvBottomValue
        val incBtn = binding.btnBottomPlus
        val decBtn = binding.btnBottomDelete
        val delBtn = binding.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainSettingSupplementBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.supplementName.text = dataList[position].medicine
        holder.count.text = dataList[position].number.toString()
        holder.incBtn.setOnClickListener {
            dataList[position].number += 1
            notifyItemChanged(position)
        }
        holder.decBtn.setOnClickListener {
            if (dataList[position].number >= 1) dataList[position].number -= 1
            notifyItemChanged(position)
        }
        holder.delBtn.setOnClickListener {
            dataList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<Medicine>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }

    fun getData() : ArrayList<Medicine>{
        return dataList
    }
}