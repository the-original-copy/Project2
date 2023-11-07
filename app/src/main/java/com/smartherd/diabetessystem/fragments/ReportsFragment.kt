package com.smartherd.diabetessystem.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.activities.CalorieConsumptionReport
import com.smartherd.diabetessystem.activities.CalorieSurplusReport
import com.smartherd.diabetessystem.activities.PredictionReport
import kotlinx.android.synthetic.main.fragment_reports.*


class SettingsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_calorie_consumption_report.setOnClickListener {
            startActivity(Intent(context,CalorieConsumptionReport::class.java))
        }
        btn_calorie_surplus_report.setOnClickListener {
            startActivity(Intent(context,CalorieSurplusReport::class.java))
        }
        btn_prediction_report.setOnClickListener {
            startActivity(Intent(context,PredictionReport::class.java))
        }
    }
}