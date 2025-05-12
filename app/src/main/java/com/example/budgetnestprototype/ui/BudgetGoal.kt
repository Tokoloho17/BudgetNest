package com.example.budgetnestprototype.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_goals")
data class BudgetGoal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val deadline: Long? = null  // Unix timestamp
)
