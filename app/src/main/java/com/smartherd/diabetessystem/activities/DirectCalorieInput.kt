package com.smartherd.diabetessystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartherd.diabetessystem.R
import android.view.WindowManager
import com.smartherd.diabetessystem.databinding.ActivityDirectCalorieInputBinding
import kotlinx.android.synthetic.main.activity_pedometer.*

class DirectCalorieInput : AppCompatActivity() {
    private var binding: ActivityDirectCalorieInputBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDirectCalorieInputBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpActionBar()
    }
    private fun setUpActionBar() {
        setSupportActionBar(binding?.toolbarDirectInput)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        binding!!.toolbarDirectInput.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}