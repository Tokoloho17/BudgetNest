package com.example.budgetnestprototype.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val name: String,
    val budgetLimit: Double? = null
)
