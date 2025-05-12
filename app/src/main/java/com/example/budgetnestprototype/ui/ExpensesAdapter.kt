package com.example.budgetnestprototype.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetnestprototype.R
import com.example.budgetnestprototype.data.Expense

class ExpensesAdapter(private var expenses: List<Expense>) : RecyclerView.Adapter<ExpensesAdapter.ExpenseViewHolder>() {

    // ViewHolder class to hold the views for each item
    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.tvCategory)
        val amountTextView: TextView = itemView.findViewById(R.id.tvAmount)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDate)
    }

    // Inflate the item layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(itemView)
    }

    // Bind the data to the views in each item
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.categoryTextView.text = expense.category
        holder.amountTextView.text = "R${expense.amount}"
        holder.dateTextView.text = expense.date
    }

    // Return the total number of items
    override fun getItemCount(): Int = expenses.size

    // Update the list of expenses (e.g., for filtering)
    fun updateExpenses(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }
}
