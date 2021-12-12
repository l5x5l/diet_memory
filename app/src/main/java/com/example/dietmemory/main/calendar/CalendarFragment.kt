package com.example.dietmemory.main.calendar

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentMainCalendarBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.calendar.adapter.CalendarAdapter
import com.example.dietmemory.main.calendar.adapter.PhotoAdapter
import com.example.dietmemory.main.calendar.models.CalendarFood
import com.example.dietmemory.main.calendar.models.GoalData
import com.example.dietmemory.main.calendar.models.IntakeData
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

        binding.viewDaySummary.rvPhotos.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.viewDaySummary.rvPhotos.adapter = PhotoAdapter(activity as MainActivity)
    }

    override fun showDateData(year : Int, month : Int, day : Int, endata : GoalData, food : ArrayList<CalendarFood>, data : IntakeData) {
        //(activity as MainActivity).showCustomToastMessage("today : $year, ${month + 1}, $day")
        binding.viewDaySummary.tvDate.text = getString(R.string.date_form, year, month + 1, day)
        binding.viewDaySummary.tvCalorie.text = getString(R.string.form_nutrient_value, "칼로리", data.calo, endata.enCalo)
        binding.viewDaySummary.tvCarbohydrate.text = getString(R.string.form_nutrient_value, "탄수화물", data.carbo, endata.enCarbo)
        binding.viewDaySummary.tvFat.text = getString(R.string.form_nutrient_value, "지방", data.fat, endata.enFat)
        binding.viewDaySummary.tvProtein.text = getString(R.string.form_nutrient_value, "단백질", data.protein, endata.enProtein)
        if (GlobalApplication.globalSharedPreferences.getBoolean(GlobalApplication.SHOW_CAL, true)){
            binding.viewDaySummary.tvProtein.visibility = View.VISIBLE
            binding.viewDaySummary.tvCalorie.visibility = View.VISIBLE
            binding.viewDaySummary.tvCarbohydrate.visibility = View.VISIBLE
            binding.viewDaySummary.tvFat.visibility = View.VISIBLE
        } else {
            binding.viewDaySummary.tvProtein.visibility = View.GONE
            binding.viewDaySummary.tvCalorie.visibility = View.GONE
            binding.viewDaySummary.tvCarbohydrate.visibility = View.GONE
            binding.viewDaySummary.tvFat.visibility = View.GONE
        }
        binding.viewDaySummary.root.visibility = View.VISIBLE
        (binding.viewDaySummary.rvPhotos.adapter as PhotoAdapter).applyData(food)
    }

    override fun applyMonthData(year : Int, month : Int, arrayList: ArrayList<Int>) {
        (binding.rvCalendar.adapter as CalendarAdapter).changeData(arrayList)
        binding.tvDate.text = getString(R.string.date_form_partial, year, month + 1)
    }

    fun applyDataChange(){
        presenter.tryGetMonthData(GlobalApplication.year, GlobalApplication.month)
    }

    fun hideDateData(){
        binding.viewDaySummary.root.visibility = View.GONE
    }
}