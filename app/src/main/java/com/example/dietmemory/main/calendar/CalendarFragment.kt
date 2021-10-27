package com.example.dietmemory.main.calendar

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainCalendarBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.calendar.adapter.CalendarAdapter
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : BaseFragment<FragmentMainCalendarBinding>(FragmentMainCalendarBinding::bind, R.layout.fragment_main_calendar), CalendarContract.View {

    private val presenter = CalendarPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        binding.rvCalendar.layoutManager = GridLayoutManager(activity as MainActivity, 7)
        binding.rvCalendar.adapter = CalendarAdapter(activity as MainActivity, presenter)

        presenter.tryGetMonthData(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH))

        // 테스트
        binding.btnNextMonth.setOnClickListener {
            (binding.rvCalendar.adapter as CalendarAdapter).changeToNextMonth()
        }
        binding.btnPrevMonth.setOnClickListener {
            (binding.rvCalendar.adapter as CalendarAdapter).changeToPrevMonth()
        }
    }

    override fun showDateData(year : Int, month : Int, day : Int) {
        (activity as MainActivity).showCustomToastMessage("today : $year, ${month + 1}, $day")
        binding.viewDaySummary.tvDate.text = getString(R.string.date_form, year, month + 1, day)
        binding.viewDaySummary.root.visibility = View.VISIBLE
    }

    override fun applyMonthData(year : Int, month : Int, arrayList: ArrayList<Int>) {
        (binding.rvCalendar.adapter as CalendarAdapter).changeData(arrayList)
        binding.tvDate.text = getString(R.string.date_form_partial, year, month + 1)
    }
}