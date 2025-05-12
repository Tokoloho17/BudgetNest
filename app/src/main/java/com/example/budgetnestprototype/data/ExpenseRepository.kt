package com.example.budgetnestprototype.data

import android.content.Context

class ExpenseRepository private constructor(context: Context) {

    private val dao = ExpenseDatabase.getDatabase(context).expenseDao()

    suspend fun addExpense(expense: Expense) = dao.insertExpense(expense)

    suspend fun getExpenses(): List<Expense> = dao.getAllExpenses()

    suspend fun clearExpenses() = dao.clearAllExpenses()

    companion object {
        @Volatile
        private var INSTANCE: ExpenseRepository? = null

        fun getInstance(context: Context): ExpenseRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = ExpenseRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
