package com.example.budgetnestprototype.data

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ExpenseRepo {
    private const val PREFS_NAME = "ExpensePrefs"
    private const val KEY_EXPENSES = "expenses"

    private val json = Json { ignoreUnknownKeys = true }

    fun addExpense(expense: Expense, context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val expenses = getExpenses(context).toMutableList()
        expenses.add(expense)
        val encoded = json.encodeToString(expenses)
        prefs.edit().putString(KEY_EXPENSES, encoded).apply()
    }

    fun getExpenses(context: Context): List<Expense> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val stored = prefs.getString(KEY_EXPENSES, null)
        return if (!stored.isNullOrEmpty()) {
            try {
                json.decodeFromString(stored)
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    fun clearExpenses(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_EXPENSES).apply()
    }
}

