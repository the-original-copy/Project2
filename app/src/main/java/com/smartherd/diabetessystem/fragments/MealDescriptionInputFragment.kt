package com.smartherd.diabetessystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smartherd.diabetessystem.R
import kotlinx.android.synthetic.main.fragment_meal_description_input.view.*


class MealDescriptionInputFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_meal_description_input,container,false)
        view.meal_categories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // view?.meal_category_input?.text = adapterView!!.getItemAtPosition(position).toString()
                // Toast.makeText(context,"You selected: ${adapterView!!.getItemAtPosition(position).toString()}",Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        } 
        // Inflate the layout for this fragment
        return view
    }
}