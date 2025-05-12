package com.example.budgetnestprototype.ui

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val description: String,
    val category: String,
    val date: Date,  // Changed from String to Date for better querying
    val photoUri: String? = null
)
