package com.smartherd.diabetessystem.fragments

import android.os.Bundle
import com.smartherd.diabetessystem.fragments.DietMealListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.adapters.MealListAdapter
import com.smartherd.diabetessystem.models.Meal
import kotlinx.android.synthetic.main.fragment_diet_meal_list.view.*

class DietMealListFragment : Fragment() {

    private lateinit var adapter: MealListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var mealArrayList: ArrayList<Meal>

    private lateinit var mealName : ArrayList<String>
    private lateinit var mealCategory : ArrayList<String>
    private lateinit var calories : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_meal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView  = view.recyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MealListAdapter(mealArrayList)
        recyclerView.adapter = adapter

    }

    private fun dataInitialize() {
        mealArrayList = arrayListOf<Meal>()
        mealName = arrayListOf(
            getString(R.string.mealNamea),
            getString(R.string.mealNameb),
            getString(R.string.mealNamec)
        )
        mealCategory = arrayListOf(
            getString(R.string.mealCategorya),
            getString(R.string.mealCategoryb),
            getString(R.string.mealCategoryc)
        )
        calories = arrayListOf(
            getString(R.string.caloriesa),
            getString(R.string.caloriesb),
            getString(R.string.caloriesc)
        )

        for(i in mealName.indices){
            val meal = Meal("","",mealName[i],mealCategory[i],calories[i])
            mealArrayList.add(meal)
        }

    }

}