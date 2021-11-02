package com.example.dietmemory.main.setting.supplement

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentMainSettingSupplementBinding
import com.example.dietmemory.main.MainActivity
import com.example.dietmemory.main.setting.SettingDetailContract
import com.example.dietmemory.main.setting.supplement.adapter.SupplementAdapter
import com.example.dietmemory.main.setting.supplement.models.EditMedicine

class SettingSupplementFragment : BaseFragment<FragmentMainSettingSupplementBinding>(FragmentMainSettingSupplementBinding::bind, R.layout.fragment_main_setting_supplement), SettingDetailContract.View {

    private lateinit var viewModel : SettingSupplementViewmodel

    override fun save() {
        viewModel.setMedicine((binding.rvSupplement.adapter as SupplementAdapter).getData())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingSupplementViewmodel::class.java)

        binding.rvSupplement.layoutManager = LinearLayoutManager(activity as MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvSupplement.adapter = SupplementAdapter(activity as MainActivity)


        val supplementObserver = Observer<ArrayList<EditMedicine>> { LiveData ->
            (binding.rvSupplement.adapter as SupplementAdapter).applyData(LiveData)
        }
        viewModel.supplementDatas.observe(viewLifecycleOwner, supplementObserver)



        binding.btnAddSupplement.setOnClickListener {
            val dialogFragment = SupplementBottomSheet(this)
            dialogFragment.show(childFragmentManager, dialogFragment.tag)
        }

        viewModel.getMedicine()
    }

    fun addSupplement(name : String){
        (binding.rvSupplement.adapter as SupplementAdapter).addData(name)
    }
}