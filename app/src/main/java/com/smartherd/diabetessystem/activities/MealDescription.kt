package com.smartherd.diabetessystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartherd.diabetessystem.R
import android.view.WindowManager
import com.smartherd.diabetessystem.adapters.MealDescriptionPagerAdapter
import com.smartherd.diabetessystem.databinding.ActivityMealDescriptionBinding
import com.smartherd.diabetessystem.fragments.InternetSearchFragment
import com.smartherd.diabetessystem.fragments.MealDescriptionInputFragment

class MealDescription : AppCompatActivity() {
    private var binding: ActivityMealDescriptionBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDescriptionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpTabs()
    }
    private fun setUpTabs(){
        val adapter = MealDescriptionPagerAdapter(supportFragmentManager)
        adapter.addFragment(InternetSearchFragment(),"Internet Search")
        adapter.addFragment(MealDescriptionInputFragment(),"Meal Description")
        binding!!.viewPager.adapter = adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
        binding!!.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_search_24)
        binding!!.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_input_24)
    }
}