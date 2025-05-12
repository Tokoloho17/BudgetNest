package com.example.budgetnestprototype

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCategoryActivity : AppCompatActivity() {

    private lateinit var etNewCategory: EditText
    private lateinit var btnSaveCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        // Initialize the views
        etNewCategory = findViewById(R.id.etNewCategory)
        btnSaveCategory = findViewById(R.id.btnSaveCategory)

        // Set up button click listener
        btnSaveCategory.setOnClickListener {
            val newCategory = etNewCategory.text.toString().trim() // Trim the input to avoid spaces

            if (newCategory.isNotEmpty()) {
                saveCategory(newCategory) // Save category if not empty
                Toast.makeText(this, "Category saved successfully!", Toast.LENGTH_SHORT).show()
                finish() // Close the activity and return to the previous one
            } else {
                // Show error if input is empty
                etNewCategory.error = "Category name cannot be empty"
            }
        }
    }

    // Function to save the category to SharedPreferences
    private fun saveCategory(category: String) {
        val prefs = getSharedPreferences("CategoryPrefs", Context.MODE_PRIVATE)

        // Retrieve the existing set of categories or create a new mutable set
        val savedList = prefs.getStringSet("category_list", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Add the new category
        savedList.add(category)

        // Save the updated category list
        prefs.edit().putStringSet("category_list", savedList).apply()
    }
}
