package com.smartherd.diabetessystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.adapters.FromDietPagerAdapter
import com.smartherd.diabetessystem.databinding.ActivityFromCreatedDietBinding
import com.smartherd.diabetessystem.fragments.DietMealListFragment
import com.smartherd.diabetessystem.fragments.MealDescriptionInputFragment

class FromCreatedDiet : AppCompatActivity() {
    private var binding: ActivityFromCreatedDietBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFromCreatedDietBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpTabs()
    }
    private fun setUpTabs(){
        val adapter = FromDietPagerAdapter(supportFragmentManager)
        adapter.addFragment(MealDescriptionInputFragment(),"Meal input")
        adapter.addFragment(DietMealListFragment(),"Meal List")
        binding!!.viewPager.adapter = adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
        binding!!.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_input_24)
        binding!!.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_meal_diet_list_24)
    }
}