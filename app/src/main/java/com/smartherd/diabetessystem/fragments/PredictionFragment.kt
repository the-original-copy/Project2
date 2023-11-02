package com.smartherd.diabetessystem.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.activities.PredictDiabetesPage
import com.smartherd.diabetessystem.databinding.FragmentPredictionBinding
import kotlinx.android.synthetic.main.fragment_prediction.*


class PredictionFragment : Fragment() {
    private var binding: FragmentPredictionBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPredictionBinding.inflate(inflater,container,false)
        val view = binding!!.root
        binding!!.rlPredict.setOnClickListener {
            startActivity(Intent(activity,PredictDiabetesPage::class.java))
        }
        // Inflate the layout for this fragment
        return view
    }
}