package com.example.budgetnestprototype.data

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseAdd(
    val amount: Double,
    val description: String,
    val category: String,
    val date: String,
    val photoUri: String? = null
)
