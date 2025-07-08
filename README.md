# BookCart Web Automation Project

This project is created as part of the automation testing exercise using **Katalon Studio**. The web application under test is [https://bookcart.azurewebsites.net/](https://bookcart.azurewebsites.net/), a simple book shopping platform that simulates features similar to a boarding house booking system.

---

## 🔍 Website Functionalities Tested

The following six features are tested using automation:

| Feature                 | Positive Test Case                                            | Negative Test Case                                                        |
| ----------------------- | ------------------------------------------------------------- | ------------------------------------------------------------------------- |
| **1. Register**         | `Register_Valid` — register with valid and unique email       | `Register_DuplicateEmail` — attempt to register with an existing email    |
| **2. Login**            | `Login_Valid` — login using correct email & password          | `Login_InvalidPassword` — login with incorrect password                   |
| **3. Search Book**      | `SearchBook_ValidKeyword` — search using a valid book keyword | `SearchBook_InvalidKeyword` — search using a random or invalid keyword    |
| **4. View Book Detail** | `ViewBookDetail_AfterLogin` — view book details after login   | `ViewBookDetail_WithoutLogin` — attempt to view book details before login |
| **5. Add to Cart**      | `AddToCart_AfterLogin` — add book to cart after login         | `AddToCart_WithoutLogin` — attempt to add to cart without login           |
| **6. Checkout**         | `Checkout_AfterLogin` — successful checkout after login       | `Checkout_Without_Login` — attempt to checkout while not logged in        |

---

## ⚙️ Project Setup

- **Global Variables** and **Profiles** are used for dynamic data handling (e.g., random username/email).
- Test Cases are grouped using **Test Case Blocks** and **Scenarios** based on modular design principles.
- The project is executable end-to-end and supports reusability for future testing needs.

---

## 📌 Notes

- When running the test cases, make sure to switch the execution profile from **default** to **staging**.

---
