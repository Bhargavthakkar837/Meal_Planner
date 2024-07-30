package com.example.mealplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MealSummaryFragment : Fragment() {

    private lateinit var mealNameTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var notesTextView: TextView
    private lateinit var mealTypeTextView: TextView
    private lateinit var dietaryPreferencesTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealNameTextView = view.findViewById(R.id.mealNameTextView)
        ingredientsTextView = view.findViewById(R.id.ingredientsTextView)
        notesTextView = view.findViewById(R.id.notesTextView)
        mealTypeTextView = view.findViewById(R.id.mealTypeTextView)
        dietaryPreferencesTextView = view.findViewById(R.id.dietaryPreferencesTextView)

        arguments?.let {
            mealNameTextView.text = it.getString("mealName")
            ingredientsTextView.text = it.getString("ingredients")
            notesTextView.text = it.getString("notes")
            mealTypeTextView.text = it.getString("mealType")
            dietaryPreferencesTextView.text = it.getString("dietaryPreferences")
        }
    }
}
