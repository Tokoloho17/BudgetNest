package com.example.budgetnestprototype.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetnestprototype.BudgetGoalsActivity
import com.example.budgetnestprototype.DashboardActivity
import com.example.budgetnestprototype.R
import com.example.budgetnestprototype.data.Expense
import com.example.budgetnestprototype.data.ExpenseRepo
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddExpenseActivity : AppCompatActivity() {
    private var photoUri: Uri? = null
    private lateinit var categoryDropdown: AutoCompleteTextView

    private val pickPhoto = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        photoUri = uri
        findViewById<ImageView>(R.id.ivPhotoPreview).apply{
            setImageURI(uri)
            visibility = if (uri !=null) VISIBLE else GONE
        }
    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        //Category dropdown
        categoryDropdown = findViewById(R.id.etCategory)
        loadCategories()

        // Select date
        findViewById<Button>(R.id.btnSelectDate).setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                  cal.set(year, month, day)
                  val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                  findViewById<Button>(R.id.btnSelectDate).text = sdf.format(cal.time)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        // Select photo
        findViewById<Button>(R.id.btnAttachPhoto).setOnClickListener {
            pickPhoto.launch("image/*")
        }
        //Validation for save expense
        findViewById<Button>(R.id.btnSaveExpense).setOnClickListener {
            val amount = findViewById<TextInputEditText>(R.id.etAmount)
                .text.toString().toDoubleOrNull() ?: 0.0
            val description = findViewById<TextInputEditText>(R.id.etDescription)
                .text.toString()
            val category = categoryDropdown.text.toString()
            val date = findViewById<Button>(R.id.btnSelectDate).text.toString()

            //Save to in-memory repo
            ExpenseRepo.addExpense(Expense(amount, description, category, date, photoUri?.toString()), this)



            //Save
            startActivity(Intent(this,BudgetGoalsActivity::class.java))
            finish()
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2?.setOnClickListener {
         val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        loadCategories() // Refresh category list when returning
    }

    private  fun loadCategories() {
        val sharedPreferences = getSharedPreferences("CategoryPrefs", MODE_PRIVATE)
        val categorySet = sharedPreferences.getStringSet("category_list", setOf()) ?: setOf()
        val categoryList = categorySet.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categoryList)
        categoryDropdown.setAdapter(adapter)

        categoryDropdown.setOnClickListener {
            categoryDropdown.showDropDown()
        }
    }
}