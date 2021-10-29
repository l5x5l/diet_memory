package com.example.dietmemory.main.setting.base

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.config.GlobalApplication
import com.example.dietmemory.databinding.FragmentMainSettingBaseBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.SettingRvView
import com.example.dietmemory.main.setting.adapter.SettingAdapter
import com.example.dietmemory.main.setting.base.models.ResponseWithdrawal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingBaseFragment(private val inputView : SettingRvView) : BaseFragment<FragmentMainSettingBaseBinding>(FragmentMainSettingBaseBinding::bind, R.layout.fragment_main_setting_base) {

    private val settingList = arrayListOf<String>()
    private val getWithdrawalResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK){
            // call withdrawal api
            //(activity as MainActivity).afterLogout() // 원래 이것도 api success 부분에서 호출되어야 한다.
            // api 하나만을 위해 contract, presenter 를 생성하는것은 비효율적이라 그냥 여기서 retrofit 호출
            val retrofitInterface = GlobalApplication.sRetrofit.create(withdrawalRetrofitInterface::class.java)
            retrofitInterface.getWithdrawal().enqueue(object : Callback<ResponseWithdrawal>{
                override fun onResponse(call: Call<ResponseWithdrawal>, response: Response<ResponseWithdrawal>) {
                    if (response.isSuccessful){
                        if(response.body()!!.isSuccess) {
                            Toast.makeText(activity as MainActivity, "회원탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            (activity as MainActivity).afterLogout()
                        } else {
                            Toast.makeText(activity as MainActivity, "회원탈퇴에 실패하였습니다, 잠시 뒤 시도해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseWithdrawal>, t: Throwable) {
                    Log.d("withdrawal", "onFailure...")
                }

            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingList.clear()
        settingList.add(getString(R.string.app_setting))
        settingList.add(getString(R.string.physical_setting))
        settingList.add(getString(R.string.supplement_and_medicine))
        settingList.add(getString(R.string.restrict_food_setting))
        settingList.add(getString(R.string.set_goal_calorie))

        binding.rvDetail.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvDetail.adapter = SettingAdapter(activity as MainActivity, inputView, settingList)

        binding.btnLogout.setOnClickListener {
            GlobalApplication.globalSharedPreferences.edit().remove(GlobalApplication.X_ACCESS_TOKEN).apply()
            (activity as MainActivity).afterLogout()
        }

        binding.btnWithdrawal.setOnClickListener {
            val intent = Intent(activity as MainActivity, WithdrawalPopupActivity::class.java)
            getWithdrawalResult.launch(intent)
        }
    }
}