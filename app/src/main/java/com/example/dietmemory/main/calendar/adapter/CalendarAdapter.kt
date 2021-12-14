package com.example.dietmemory.main.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dietmemory.R
import com.example.dietmemory.databinding.ItemMainCalendarBinding
import com.example.dietmemory.main.calendar.BaseCalendar
import com.example.dietmemory.main.calendar.CalendarContract
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(private val context : Context, private val presenter : CalendarContract.Presenter) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){

    private val dataList = ArrayList<Int>() // 해당 날에 대한 섭취 요약 데이터
    private val calendar = BaseCalendar() // 달력 자체에 대한 데이터이므로 이곳에 생성
    private lateinit var binding : ItemMainCalendarBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        calendar.initBaseCalendar {  }
    }

    class ViewHolder(binding : ItemMainCalendarBinding) : RecyclerView.ViewHolder(binding.root){
        val borderTop = binding.borderTop
        val borderBottom = binding.borderBottom
        val borderLeft = binding.borderLeft
        val borderRight = binding.borderRight
        val date = binding.tvDate
        //val checked = binding.ivCheck
        val layoutMain = binding.layoutMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMainCalendarBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position / 7 == 0) holder.borderTop.visibility = View.GONE
        if (position / 7 == 5) holder.borderBottom.visibility = View.GONE
        if (position % 7 == 0) holder.borderLeft.visibility = View.GONE
        if (position % 7 == 6) holder.borderRight.visibility = View.GONE

        // 데이터가 해당 달의 데이터만 가져온다 가정
        holder.date.text = calendar.data[position].toString()

        if (position < calendar.prevMonthTailOffset || position >= calendar.prevMonthTailOffset + calendar.currentMonthMaxDate){
            holder.date.setTextColor(ContextCompat.getColor(context, R.color.gray))
        } else {
            holder.date.setTextColor(ContextCompat.getColor(context, R.color.black))
            if (dataList.size > position - calendar.prevMonthTailOffset + 1){
                when (dataList[position - calendar.prevMonthTailOffset + 1]){
                    0 -> holder.layoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green))
                    1 -> holder.layoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                    2 -> holder.layoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                    else -> holder.layoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                }

                holder.layoutMain.setOnClickListener{
                    presenter.tryGetDateData(calendar.calendar.get(Calendar.YEAR), calendar.calendar.get(Calendar.MONTH), calendar.data[position])
                    //Log.d("current vs click", "${GlobalApplication.month} / ${GlobalApplication.day} vs ${calendar.calendar.get(Calendar.MONTH)} / ${calendar.data[position]}")
                }
            }
        }
    }

    override fun getItemCount(): Int = 42

    fun changeData(inputDateList : ArrayList<Int>){
        dataList.clear()
        dataList.add(2) // 가장 첫 데이터가 씹혀서 임시로 추가함
        dataList.addAll(inputDateList)
        notifyDataSetChanged()
    }

    private fun refreshView(calendar: Calendar){
        notifyDataSetChanged()
        presenter.tryGetMonthData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH))
        // presenter 호출해 rv 외 요소 수정
    }

    fun changeToPrevMonth(){
        calendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth(){
        calendar.changeToNextMonth {
            refreshView(it)
        }
    }


}