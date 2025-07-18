# 📘 BookCart Web Automation Project

This repository contains an automation testing project built with **Katalon Studio**, created as part of a QA exercise. The tested web application is:

🔗 [https://bookcart.azurewebsites.net/](https://bookcart.azurewebsites.net/)

A simple book shopping platform with essential features such as registration, login, product viewing, cart management, and checkout.

---

## ✅ Features Tested

| #  | Functionality       | Positive Test Case                      | Negative Test Case                         |
|----|---------------------|-----------------------------------------|---------------------------------------------|
| 1  | Register            | `Register_Valid` — valid new user       | `Register_DuplicateEmail` — email already used |
| 2  | Login               | `Login_Valid` — correct credentials     | `Login_InvalidPassword` — wrong password    |
| 3  | Search Book         | `SearchBook_ValidKeyword`               | `SearchBook_InvalidKeyword`                |
| 4  | View Book Detail    | `ViewBookDetail_AfterLogin`             | `ViewBookDetail_WithoutLogin`              |
| 5  | Add to Cart         | `AddToCart_AfterLogin`                  | `AddToCart_WithoutLogin`                   |
| 6  | Checkout            | `Checkout_AfterLogin`                   | `Checkout_Without_Login`                   |

---

## 🧪 Test Structure

### 🔹 Test Suite Collection
- `BookCart_Collection`: contains **3 Test Suites**, including:
  - `TS_Login_DataDriven` (with Data Driven Testing - 3 sets of login data).
  - `TS_SearchBook_DataDriven` (with Internal Database / Raw Data).
  - `TS_FunctionalTests` (standard positive/negative flows).

### 🔹 Data Driven Testing
- Implemented using login test case with 3 variations of credentials (valid, invalid password, invalid username and password).
- Implemented using search book test case with 2 variations of book title (valid and invalid).

### 🔹 Conditional Logic
- **If-Else Statements** and **Switch-Case** used inside:
  - Login logic: conditionally checks whether the user is successfully logged in.
  - Search Book validation: handles search outcomes based on specific book titles.

---

## 🌐 Multi-Browser Execution
This project is executed using:
- **Chrome**
- **Safari**
- via Test Suite Collection runner

---

## 📌 Notes
- 🧪 Make sure to run the project using the `Test Suite` or `Test Suite Collection`.
- 🔑 Ensure browsers (especially Safari) have **WebDriver remote automation enabled** before running.
- 📦 Tests are modular and designed for reusability in future automation tasks.
