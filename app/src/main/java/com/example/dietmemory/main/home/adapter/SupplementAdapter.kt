package com.example.dietmemory.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.R
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.ItemMainHomeSupplementBinding
import com.example.dietmemory.main.home.models.MedicineData

class SupplementAdapter(private val context: Context) : RecyclerView.Adapter<SupplementAdapter.ViewHolder>() {
    private val dataList = ArrayList<MedicineData>()
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemMainHomeSupplementBinding

    class ViewHolder(binding : ItemMainHomeSupplementBinding) : RecyclerView.ViewHolder(binding.root){
        val medicineName = binding.tvBottomName
        val btnInc = binding.btnIncrease
        val btnDec = binding.btnDecrease
        val medicineCount = binding.tvSupplementCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainHomeSupplementBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnDec.setOnClickListener {
            val count = GlobalApplication.globalSharedPreferences.getInt(dataList[position].medicine, 0)
            if (count >= 1){
                GlobalApplication.globalSharedPreferences.edit().putInt(dataList[position].medicine, count - 1).apply()
                holder.medicineCount.text = context.getString(R.string.form_count, count - 1)
            }
        }
        holder.btnInc.setOnClickListener {
            val count = GlobalApplication.globalSharedPreferences.getInt(dataList[position].medicine, 0)
            GlobalApplication.globalSharedPreferences.edit().putInt(dataList[position].medicine, count + 1).apply()
            holder.medicineCount.text = context.getString(R.string.form_count, count + 1)
        }
        holder.medicineName.text = dataList[position].medicine
        holder.medicineCount.text = context.getString(R.string.form_count, GlobalApplication.globalSharedPreferences.getInt(dataList[position].medicine, 0))
    }

    override fun getItemCount(): Int = dataList.size

    fun applyData(inputData : ArrayList<MedicineData>){
        dataList.clear()
        dataList.addAll(inputData)
        notifyDataSetChanged()
    }
}