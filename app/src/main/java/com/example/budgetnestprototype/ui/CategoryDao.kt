package com.example.budgetnestprototype.ui

import androidx.room.*

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories ORDER BY name ASC")
    suspend fun getAllCategories(): List<Category>

    @Query("DELETE FROM categories WHERE name = :name")
    suspend fun delete(name: String)
}
