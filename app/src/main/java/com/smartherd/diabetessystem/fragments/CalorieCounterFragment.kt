package com.smartherd.diabetessystem.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.activities.CaloriesConsumedInputPage
import com.smartherd.diabetessystem.activities.CreateDiet
import com.smartherd.diabetessystem.activities.Pedometer
import com.smartherd.diabetessystem.activities.PersonalData
import com.smartherd.diabetessystem.databinding.FragmentCalorieCounterBinding


class CalorieCounterFragment : Fragment() {
    private var binding: FragmentCalorieCounterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalorieCounterBinding.inflate(inflater,container,false)
        val view = binding!!.root
        // Inflate the layout for this fragment
        binding!!.personalData.setOnClickListener {
            startActivity(Intent(activity,PersonalData::class.java))
        }
        binding!!.createDiet.setOnClickListener {
            startActivity(Intent(activity,CreateDiet::class.java))
        }
        binding!!.caloriesBurnt.setOnClickListener {
            startActivity(Intent(activity,Pedometer::class.java))
        }
        binding!!.caloriesConsumed.setOnClickListener {
            startActivity(Intent(activity,CaloriesConsumedInputPage::class.java))
        }
        return view
    }
}