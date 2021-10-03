package com.example.dietmemory.config

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> (private val inflate : (LayoutInflater) -> B) : AppCompatActivity() {
    protected lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun goToActivitySimple(activity : Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    fun showCustomToastMessage(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}