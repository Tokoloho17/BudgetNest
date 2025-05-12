package com.example.budgetnestprototype.ui

import androidx.room.*

@Dao
interface BudgetGoalDao {
    @Insert
    suspend fun insert(goal: BudgetGoal)

    @Update
    suspend fun update(goal: BudgetGoal)

    @Query("SELECT * FROM budget_goals ORDER BY deadline ASC")
    suspend fun getAllGoals(): List<BudgetGoal>

    @Query("SELECT * FROM budget_goals WHERE id = :id")
    suspend fun getGoalById(id: Long): BudgetGoal?
}