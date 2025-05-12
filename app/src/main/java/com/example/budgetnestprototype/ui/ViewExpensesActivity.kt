package com.example.budgetnestprototype.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetnestprototype.R
import com.example.budgetnestprototype.data.Expense

class ViewExpensesActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var expensesRecyclerView: RecyclerView
    private lateinit var expensesAdapter: ExpensesAdapter

    // Actual expenses list will be populated from your data source
    private var expensesList = listOf<Expense>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses)

        searchEditText = findViewById(R.id.searchEditText)
        expensesRecyclerView = findViewById(R.id.expensesRecyclerView)

        // Set up the RecyclerView with the ExpensesAdapter
        expensesAdapter = ExpensesAdapter(expensesList)
        expensesRecyclerView.layoutManager = LinearLayoutManager(this)
        expensesRecyclerView.adapter = expensesAdapter

        // Filter list as user types
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val filteredList = expensesList.filter {
                    it.category.contains(s.toString(), ignoreCase = true) ||
                            it.description.contains(s.toString(), ignoreCase = true)
                }
                expensesAdapter.updateExpenses(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // Later, populate expensesList with your real data from your data source
}
