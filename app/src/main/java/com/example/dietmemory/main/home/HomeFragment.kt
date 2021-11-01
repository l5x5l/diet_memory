package com.example.dietmemory.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentMainHomeBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.home.adapter.FoodAdapter
import com.example.dietmemory.main.home.adapter.FoodRecommendAdapter
import com.example.dietmemory.main.home.adapter.FoodRecommendDecoration
import com.example.dietmemory.main.home.adapter.SupplementAdapter
import com.example.dietmemory.main.home.models.MainResponse
import com.example.dietmemory.main.home.models.RecommendFood

class HomeFragment : BaseFragment<FragmentMainHomeBinding>(FragmentMainHomeBinding::bind, R.layout.fragment_main_home), HomeContract.View {

    private val presenter = HomePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        binding.rvTodayIntake.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvTodayIntake.adapter = FoodAdapter(activity as MainActivity)

        binding.viewWater.btnMiniCup.setOnClickListener { presenter.changeWaterIntake(0) }
        binding.viewWater.btnSoloCup.setOnClickListener { presenter.changeWaterIntake(1) }
        binding.viewWater.btnBottle.setOnClickListener { presenter.changeWaterIntake(2) }

        binding.viewRecommendFood.rvRecommend.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.viewRecommendFood.rvRecommend.adapter = FoodRecommendAdapter(activity as MainActivity)
        binding.viewRecommendFood.rvRecommend.addItemDecoration(FoodRecommendDecoration(activity as MainActivity))
        binding.viewRecommendFood.recommendMain.setOnClickListener {
            presenter.tryGetRecommendData()
        }

        binding.viewSupplements.rvSupplement.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.viewSupplements.rvSupplement.adapter = SupplementAdapter(activity as MainActivity)


        applyShowCal()

        presenter.tryGetTodayData()
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }

    override fun applyTodayData(dataList : MainResponse, totalCal : Int, totalCar : Int, totalFat : Int, totalPro : Int) {
        if (dataList.Food.size == 0){
            binding.viewAddInduce.layoutMain.visibility = View.VISIBLE
            (binding.rvTodayIntake.adapter as FoodAdapter).applyData(arrayListOf())
            binding.viewRecommendFood.recommendMain.visibility = View.GONE
        } else {
            binding.viewAddInduce.layoutMain.visibility = View.GONE
            (binding.rvTodayIntake.adapter as FoodAdapter).applyData(dataList.Food)
            binding.viewRecommendFood.recommendMain.visibility = View.VISIBLE
        }
        binding.viewIntakeInfo.tvTargetCalorie.text = dataList.Data.enCalo.toString()
        binding.viewIntakeInfo.tvIntakeCalorie.text = totalCal.toString()
        binding.viewIntakeInfo.tvIntakeCarbohydrate.text = getString(R.string.intake_slash_target, totalCar, dataList.Data.enCarbo)
        binding.viewIntakeInfo.tvIntakeProtein.text = getString(R.string.intake_slash_target, totalPro, dataList.Data.enProtein)
        binding.viewIntakeInfo.tvIntakeFat.text = getString(R.string.intake_slash_target, totalFat, dataList.Data.enFat)

        (binding.viewSupplements.rvSupplement.adapter as SupplementAdapter).applyData(dataList.Medicine)
    }

    override fun applyWaterIntake(intake: Int, scaleType: Int) {
        binding.viewWater.tvIntakeWater.text = (activity as MainActivity).getString(R.string.value_scale_form, intake, "ml")
    }

    override fun applyRecommendData(available: Boolean, message: String, foods: ArrayList<RecommendFood>) {
        if (available){
            binding.viewRecommendFood.btnRecommend.visibility = View.GONE
            (binding.viewRecommendFood.rvRecommend.adapter as FoodRecommendAdapter).applyData(foods)
            binding.viewRecommendFood.rvRecommend.visibility = View.VISIBLE
        } else {
            binding.viewRecommendFood.btnRecommend.text = message
        }
    }

    // 섫정에서 칼로리 표시 유무를 변경한 경우
    fun applyShowCal(){
        if (GlobalApplication.globalSharedPreferences.getBoolean(GlobalApplication.SHOW_CAL, true)){
            binding.viewIntakeInfo.cvNotUseCalorie.visibility = View.GONE
            binding.viewIntakeInfo.cvCalorie.visibility = View.VISIBLE
            binding.viewIntakeInfo.cvNutrient.visibility = View.VISIBLE
        } else {
            binding.viewIntakeInfo.cvNotUseCalorie.visibility = View.VISIBLE
            binding.viewIntakeInfo.cvCalorie.visibility = View.GONE
            binding.viewIntakeInfo.cvNutrient.visibility = View.GONE
        }
    }

    fun applyFoodChange(){
        presenter.tryGetTodayData()
    }

}