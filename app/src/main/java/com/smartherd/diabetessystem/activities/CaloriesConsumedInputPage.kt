package com.smartherd.diabetessystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.databinding.ActivityCaloriesConsumedInputPageBinding
import kotlinx.android.synthetic.main.activity_pedometer.*

class CaloriesConsumedInputPage : AppCompatActivity() {
    private var binding: ActivityCaloriesConsumedInputPageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaloriesConsumedInputPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpActionBar()
        binding!!.mealDescription.setOnClickListener {
            startActivity(Intent(this,MealDescription::class.java))
        }
    }
    private fun setUpActionBar() {
        setSupportActionBar(binding?.toolbarCalorieInput)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        binding!!.toolbarCalorieInput.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}