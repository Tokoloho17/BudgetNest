package com.example.budgetnestprototype

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetnestprototype.ui.AddExpenseActivity
import com.example.budgetnestprototype.ui.ViewExpensesActivity  // Import ViewExpensesActivity
import com.example.budgetnestprototype.AddCategoryActivity
import com.example.budgetnestprototype.BudgetGoalsActivity
import com.example.budgetnestprototype.RewardsActivity

class DashboardActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Create Category button
        val btnCategory = findViewById<Button>(R.id.btnCategory)
        btnCategory?.setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }

        // Create Expense button
        val btnExpenses = findViewById<Button>(R.id.btnExpenes)
        btnExpenses?.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // Budget Goals button
        val btnBudgetGoals = findViewById<Button>(R.id.btnBudgetGoals)
        btnBudgetGoals?.setOnClickListener {
            val intent = Intent(this, BudgetGoalsActivity::class.java)
            startActivity(intent)
        }

        // View Expenses button (Added this to open ViewExpensesActivity)
        val btnViewExpenses = findViewById<Button>(R.id.btnViewExpenses)
        btnViewExpenses?.setOnClickListener {
            val intent = Intent(this, ViewExpensesActivity::class.java)
            startActivity(intent)
        }

        // Rewards button
        val btnRewards = findViewById<Button>(R.id.btnRewards)
        btnRewards?.setOnClickListener {
            val intent = Intent(this, RewardsActivity::class.java)
            startActivity(intent)
        }
    }
}
