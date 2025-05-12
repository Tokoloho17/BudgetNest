package com.example.budgetnestprototype.ui

import android.content.Context
import kotlinx.coroutines.flow.Flow
import java.util.Date

class ExpenseRepository(context: Context) {
    private val expenseDao = AppDatabase.getDatabase(context).expenseDao()
    private val categoryDao = AppDatabase.getDatabase(context).categoryDao()
    private val budgetGoalDao = AppDatabase.getDatabase(context).budgetGoalDao()

    // Expense operations
    suspend fun addExpense(expense: Expense) = expenseDao.insert(expense)
    fun getAllExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()
    suspend fun getExpensesBetweenDates(start: Date, end: Date) = expenseDao.getExpensesBetweenDates(start, end)
    suspend fun getCategoryTotal(category: String) = expenseDao.getTotalForCategory(category)

    // Category operations
    suspend fun addCategory(category: Category) = categoryDao.insert(category)
    suspend fun getAllCategories() = categoryDao.getAllCategories()
    suspend fun deleteCategory(name: String) = categoryDao.delete(name)

    // Budget goal operations
    suspend fun addBudgetGoal(goal: BudgetGoal) = budgetGoalDao.insert(goal)
    suspend fun updateBudgetGoal(goal: BudgetGoal) = budgetGoalDao.update(goal)
    suspend fun getAllBudgetGoals() = budgetGoalDao.getAllGoals()
    suspend fun getBudgetGoal(id: Long) = budgetGoalDao.getGoalById(id)

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
