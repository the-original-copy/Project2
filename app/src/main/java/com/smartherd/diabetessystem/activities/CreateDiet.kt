package com.smartherd.diabetessystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.adapters.CreateDietPagerAdapter
import com.smartherd.diabetessystem.databinding.ActivityCreateDietBinding
import com.smartherd.diabetessystem.fragments.DietMealListFragment
import com.smartherd.diabetessystem.fragments.MealDescriptionInputFragment

class CreateDiet : AppCompatActivity() {
    private var binding: ActivityCreateDietBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDietBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpTabs()
    }
    private fun setUpTabs(){
        val adapter = CreateDietPagerAdapter(supportFragmentManager)
        adapter.addFragment(MealDescriptionInputFragment(),"Add Meal")
        adapter.addFragment(DietMealListFragment(),"Meal List")
        binding!!.viewPager.adapter = adapter
        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
        binding!!.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_playlist_create_diet_24)
        binding!!.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_meal_diet_list_24)
    }
}