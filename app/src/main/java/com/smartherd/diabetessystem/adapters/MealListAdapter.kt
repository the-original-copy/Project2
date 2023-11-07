package com.smartherd.diabetessystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.models.Meal

class MealListAdapter(private val mealList: ArrayList<Meal>) : RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {
    class MealViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvName : TextView = itemView.findViewById(R.id.tv_meal_name)
        val tvMealCategory: TextView = itemView.findViewById(R.id.tv_meal_category)
        val tvMealCalories: TextView = itemView.findViewById(R.id.tv_meal_calories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.diet_meal_list_item,parent,false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentItem = mealList[position]
        holder.tvName.setText(currentItem.mealName)
        holder.tvMealCategory.setText(currentItem.mealCategory)
        holder.tvMealCalories.setText(currentItem.calories)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}