package com.example.dietmemory.main.setting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.databinding.ItemMainSettingDetailBinding
import com.example.dietmemory.main.setting.SettingRvView

class SettingAdapter(context : Context, private val view : SettingRvView, private val dataList : ArrayList<String>) : RecyclerView.Adapter<SettingAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemMainSettingDetailBinding

    class ViewHolder(binding : ItemMainSettingDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        val layoutMain = binding.layoutMain
        val text = binding.tvDetail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainSettingDetailBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layoutMain.setOnClickListener {
            view.showFragment(position, dataList[position])
        }
        holder.text.text = dataList[position]
    }

    override fun getItemCount(): Int = dataList.size
}