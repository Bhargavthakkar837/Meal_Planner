package com.example.mealplanner

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mealNameEditText: EditText
    private lateinit var ingredientsEditText: EditText
    private lateinit var notesEditText: EditText
    private lateinit var mealTypeSpinner: Spinner
    private lateinit var dietaryPreferencesRadioGroup: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        mealNameEditText = findViewById(R.id.mealNameEditText)
        ingredientsEditText = findViewById(R.id.ingredientsEditText)
        notesEditText = findViewById(R.id.notesEditText)
        mealTypeSpinner = findViewById(R.id.mealTypeSpinner)
        dietaryPreferencesRadioGroup = findViewById(R.id.dietaryPreferencesRadioGroup)
        submitButton = findViewById(R.id.submitButton)
        clearButton = findViewById(R.id.clearButton)

        // Set up Spinner adapter
        ArrayAdapter.createFromResource(
            this,
            R.array.meal_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mealTypeSpinner.adapter = adapter
        }

        // Set up button click listeners
        submitButton.setOnClickListener {
            // Handle the submission logic here
            val mealName = mealNameEditText.text.toString()
            val ingredients = ingredientsEditText.text.toString()
            val notes = notesEditText.text.toString()
            val mealType = mealTypeSpinner.selectedItem.toString()
            val selectedDietaryPreference = when (dietaryPreferencesRadioGroup.checkedRadioButtonId) {
                R.id.vegetarianRadioButton -> "Vegetarian"
                R.id.veganRadioButton -> "Vegan"
                R.id.nonVegetarianRadioButton -> "Non-Vegetarian"
                else -> "No preference"
            }

            // Create an instance of the MealSummaryFragment and pass the data
            val fragment = MealSummaryFragment().apply {
                arguments = Bundle().apply {
                    putString("mealName", mealName)
                    putString("ingredients", ingredients)
                    putString("notes", notes)
                    putString("mealType", mealType)
                    putString("dietaryPreferences", selectedDietaryPreference)
                }
            }

            // Replace the fragmentContainerView with MealSummaryFragment
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                addToBackStack(null) // Optionally add to back stack
                commit()
            }

        }

        clearButton.setOnClickListener {
            // Clear the form inputs
            mealNameEditText.text.clear()
            ingredientsEditText.text.clear()
            notesEditText.text.clear()
            mealTypeSpinner.setSelection(0)
            dietaryPreferencesRadioGroup.clearCheck()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_meal -> {
                // Handle add meal action
                true
            }
            R.id.action_view_meal_plan -> {
                // Handle view meal plan action
                true
            }
            R.id.action_app_info -> {
                // Handle app info action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
