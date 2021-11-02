package com.example.dietmemory.main.setting.supplement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.databinding.ItemMainSettingSupplementBinding
import com.example.dietmemory.main.setting.supplement.models.EditMedicine

class SupplementAdapter(context: Context) : RecyclerView.Adapter<SupplementAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val dataList = ArrayList<EditMedicine>()
    private lateinit var binding: ItemMainSettingSupplementBinding

    class ViewHolder(binding: ItemMainSettingSupplementBinding) : RecyclerView.ViewHolder(binding.root){
        val supplementName = binding.tvBottomName
        val count = binding.tvBottomValue
        val incBtn = binding.btnBottomPlus
        val decBtn = binding.btnBottomDelete
        val delBtn = binding.btnDelete
        val removeLine = binding.layoutIsRemove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainSettingSupplementBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataList[position].update == 3){
            holder.removeLine.visibility = View.VISIBLE
            holder.supplementName.isEnabled = false
        } else {
            holder.removeLine.visibility = View.GONE
            holder.supplementName.isEnabled = true
        }

        holder.removeLine.setOnClickListener {
            if (dataList[position].update == 3){
                dataList[position].update = 2
                notifyItemChanged(position)
            }
        }

        holder.supplementName.text = dataList[position].medicine
        holder.count.text = dataList[position].number.toString()
        holder.incBtn.setOnClickListener {
            if (dataList[position].update != 3) {
                dataList[position].number += 1
                notifyItemChanged(position)
            }
        }
        holder.decBtn.setOnClickListener {
            if (dataList[position].update != 3){
                if (dataList[position].number >= 1) {
                    dataList[position].number -= 1
                    notifyItemChanged(position)
                }
            }
        }
        holder.delBtn.setOnClickListener {
            //dataList.removeAt(position)
            //notifyItemRemoved(position)
            dataList[position].update = 3
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData: ArrayList<EditMedicine>){
        dataList.clear()
        dataList.addAll(inputData)
        for (data in dataList){
            data.update = 2
        }
        notifyDataSetChanged()
    }

    fun addData(supplementName : String) {
        dataList.add(EditMedicine(supplementName, 1, 1))
        notifyItemInserted(dataList.size - 1)
    }

    fun getData() : ArrayList<EditMedicine>{
        return dataList
    }
}