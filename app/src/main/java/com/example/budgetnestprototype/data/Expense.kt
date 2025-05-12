package com.example.budgetnestprototype.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: String,
    val description: String,
    val category: String,
    val date: String,
    val photoUri: String? = null
)
