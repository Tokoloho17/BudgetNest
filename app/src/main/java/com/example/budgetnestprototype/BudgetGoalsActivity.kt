package com.example.budgetnestprototype

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BudgetGoalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_goals)

        val etGoalAmount = findViewById<EditText>(R.id.etGoalAmount)
        val btnSaveGoal = findViewById<Button>(R.id.btnSaveGoal)

        btnSaveGoal.setOnClickListener {
            val amount = etGoalAmount.text.toString().toDoubleOrNull()
            if (amount != null) {
                val prefs = getSharedPreferences("BudgetGoals", Context.MODE_PRIVATE)
                prefs.edit().putFloat("goalAmount", amount.toFloat()).apply()
                Toast.makeText(this, "Goal saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
