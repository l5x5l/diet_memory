package com.example.dietmemory.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.data.FoodData
import com.example.dietmemory.databinding.FragmentMainHomeBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.home.adapter.FoodAdapter

class HomeFragment : BaseFragment<FragmentMainHomeBinding>(FragmentMainHomeBinding::bind, R.layout.fragment_main_home), HomeContract.View {

    private val presenter = HomePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        binding.rvTodayIntake.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvTodayIntake.adapter = FoodAdapter(activity as MainActivity)

        presenter.tryGetTodayData()
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }

    override fun applyTodayData(dataList : ArrayList<FoodData>) {
        (binding.rvTodayIntake.adapter as FoodAdapter).applyData(dataList)
    }


}