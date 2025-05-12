
#  BudgetNestPrototype

**BudgetNestPrototype** is a personal budgeting app built with Kotlin for Android. It allows users to track their spending, set budgeting goals, and manage expenses across different categories. This app is ideal for students, young professionals, and anyone aiming to better understand and control their finances.

---

##  Table of Contents

- [Features](#features)
- [Screens and Activities](#screens-and-activities)
  - [Authentication](#authentication)
  - [Dashboard](#dashboard)
  - [Add Category](#add-category)
  - [Add Expense](#add-expense)
  - [Budget Goals](#budget-goals)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Tech Stack](#tech-stack)
- [Setup Instructions](#setup-instructions)
- [Future Improvements](#future-improvements)
- [License](#license)

---

##  Features

-  User login and registration
-  Create and manage expense entries
-  Define and manage expense categories
-  Set monthly budgeting goals
-  Intuitive, clean dashboard interface
-  Planned support for searching and viewing past expenses

---

##  Screens and Activities

###  Authentication

**Activities:**
- `LoginActivityActivity`
- `SignUpActivity`

Users start the app by either signing in or creating a new account. These screens validate the basic login flow. While authentication is local in this version, future updates may include Firebase or OAuth.

---

###  Dashboard

**Activity:**
- `DashboardActivity`

This is the central navigation hub of the app. Users can access all major features through buttons on the dashboard:

- **Create Category:** Launches the Add Category screen.
- **Create Expense:** Opens a form to log a new expense.
- **Budget Goals:** Navigates to a screen to set monthly financial limits.
- **View Expenses:** *(In development)* Will show a searchable list of past expenses.

---

###  Add Category

**Activity:**
- `AddCategoryActivity`

This screen allows users to define their own expense categories such as:

- Groceries
- Transport
- Education
- Utilities

These categories appear in dropdowns when logging expenses to help organize records.

---

###  Add Expense

**Activity:**
- `AddExpenseActivity`

Allows users to log a new expense with the following fields:

- **Amount**: (e.g., R100.00)
- **Description**: (e.g., Taxi fare to school)
- **Category**: Select from previously added categories
- **Date**: Automatically or manually entered

Each record is stored temporarily in memory for now. Persistent storage will be implemented in the next iteration.

---

###  Budget Goals

**Activity:**
- `BudgetGoalsActivity`

Users can define monthly spending goals. These are useful for:

- Preventing overspending
- Tracking savings targets
- Comparing actual vs. planned expenditure

The goals are category-based or total-based.

---

##  Architecture

The project follows a simplified **Model-View-Controller** approach:

- **Model:** `Expense.kt` (data class for expenses)
- **View:** XML layout files for each activity
- **Controller:** Kotlin Activities managing user interaction and app logic

---

##  Project Structure

```
com.example.budgetnestprototype
│
├── auth/
│   ├── LoginActivityActivity.kt
│   └── SignUpActivity.kt
│
├── data/
│   ├── Expense.kt         # Data class for expense objects
│   └── ExpenseRepo.kt     # Placeholder for storage logic (future)
│
├── ui/
│   ├── AddExpenseActivity.kt
│   ├── AddCategoryActivity.kt
│   ├── BudgetGoalsActivity.kt
│   ├── DashboardActivity.kt
│
└── test/
    ├── ExampleUnitTest.kt
    └── ExampleInstrumentedTest.kt
```

---

##  Tech Stack

| Tool / Language | Purpose                         |
|-----------------|---------------------------------|
| Kotlin          | Primary programming language    |
| Android Studio  | Development IDE                 |
| XML             | UI Layouts                      |
| Intents         | Activity transitions            |
| RecyclerView    | (Planned) For listing expenses  |

---

##  Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/YOUR_USERNAME/BudgetNestPrototype.git
   ```

2. **Open the project in Android Studio.**

3. **Let Gradle sync the project and resolve dependencies.**

4. **Run the app** using an emulator or a real Android device.

---

##  Future Improvements

- [ ] Add Room database for persistent expense storage
- [ ] Implement ViewExpensesActivity with full search/filter capability
- [ ] Enable editing and deleting expense entries
- [ ] Add currency formatting and localization
- [ ] Implement secure login with Firebase Auth
- [ ] Monthly summary screen with downloadable reports

---

##  License

This project is licensed under the MIT License. You may use, modify, and distribute this app with attribution.

---

##  Contributions

Contributions are welcome! Feel free to fork the repo, create a feature branch, and open a pull request.
