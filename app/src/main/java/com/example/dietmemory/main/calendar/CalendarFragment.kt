package com.example.dietmemory.main.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainCalendarBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.calendar.adapter.CalendarAdapter

class CalendarFragment : BaseFragment<FragmentMainCalendarBinding>(FragmentMainCalendarBinding::bind, R.layout.fragment_main_calendar), CalendarContract.View {

    private val presenter = CalendarPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        binding.rvCalendar.layoutManager = GridLayoutManager(activity as MainActivity, 7)
        binding.rvCalendar.adapter = CalendarAdapter(activity as MainActivity, presenter)

        presenter.getCalendar()

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
    }

    override fun applyData(arrayList: ArrayList<Int>) {
        (binding.rvCalendar.adapter as CalendarAdapter).changeData(arrayList)
    }
}