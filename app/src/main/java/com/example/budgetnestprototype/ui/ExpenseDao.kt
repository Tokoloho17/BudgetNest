package com.example.budgetnestprototype.ui

import androidx.room.*
import java.util.Date

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAllExpenses(): List<Expense>

    @Query("SELECT * FROM expenses WHERE date BETWEEN :start AND :end ORDER BY date DESC")
    suspend fun getExpensesBetweenDates(start: Date, end: Date): List<Expense>

    @Query("SELECT SUM(amount) FROM expenses WHERE category = :category")
    suspend fun getTotalForCategory(category: String): Double
}
