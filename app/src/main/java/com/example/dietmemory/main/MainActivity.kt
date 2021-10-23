package com.example.dietmemory.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dietmemory.R
import com.example.dietmemory.add.AddActivity
import com.example.dietmemory.config.BaseActivity
import com.example.dietmemory.databinding.ActivityMainBinding
import com.example.dietmemory.login.LoginActivity
import com.example.dietmemory.main.calendar.CalendarFragment
import com.example.dietmemory.main.exercise.ExerciseFragment
import com.example.dietmemory.main.home.HomeFragment
import com.example.dietmemory.main.setting.SettingFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private var homeFragment : HomeFragment ?= null
    private var calendarFragment : CalendarFragment ?= null
    private var settingFragment : SettingFragment ?= null
    private var exerciseFragment : ExerciseFragment ?= null
    private lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeFragment = HomeFragment()
        currentFragment = homeFragment as HomeFragment
        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, currentFragment).commit()

        binding.bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_home -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).commit()
                    supportFragmentManager.beginTransaction().show(homeFragment!!).commit()
                    currentFragment = homeFragment!!
                }
                R.id.bottom_calendar -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).commit()
                    if (calendarFragment == null){
                        calendarFragment = CalendarFragment()
                        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, calendarFragment!!).commit()
                    } else {
                        supportFragmentManager.beginTransaction().show(calendarFragment!!).commit()
                    }
                    currentFragment = calendarFragment!!
                }
                R.id.bottom_setting -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).commit()
                    if (settingFragment == null){
                        settingFragment = SettingFragment()
                        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, settingFragment!!).commit()
                    } else {
                        supportFragmentManager.beginTransaction().show(settingFragment!!).commit()
                    }
                    currentFragment = settingFragment!!
                }
                R.id.bottom_exercise -> {
                    supportFragmentManager.beginTransaction().hide(currentFragment).commit()
                    if (exerciseFragment == null){
                        exerciseFragment = ExerciseFragment()
                        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, exerciseFragment!!).commit()
                    } else {
                        supportFragmentManager.beginTransaction().show(exerciseFragment!!).commit()
                    }
                    currentFragment = exerciseFragment!!
                }
            }
            true
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
            overridePendingTransition(R.anim.from_bottom, R.anim.none)
        }
    }

    fun afterLogout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    // 설정 페이지에서 app 설정 변경완료시 호출될 예정
    fun applyShowCal() {
        if (homeFragment != null) homeFragment!!.applyShowCal()
    }
}