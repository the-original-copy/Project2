package com.smartherd.diabetessystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.adapters.ViewPagerAdapter
import com.smartherd.diabetessystem.fragments.CalorieCounterFragment
import com.smartherd.diabetessystem.fragments.PredictionFragment
import com.smartherd.diabetessystem.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CalorieCounterFragment(),"Calorie Based Tracking")
        adapter.addFragment(PredictionFragment(),"Predictor")
        adapter.addFragment(SettingsFragment(),"Reports")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_calories_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_predict_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_reports_24)
    }


}