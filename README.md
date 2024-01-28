# Bank Application

The Bank Application is a Java-based console application for managing accounts, designed to provide basic banking functionalities.

## Overview

This application allows users to create accounts, manage accounts as an admin or customer, and perform various banking operations.

## Features

- **Create Account:** Users can create new accounts by providing essential details.
- **Manage Account:** Admins and customers can perform account-related operations.
- **Exit:** Exit the application.

## Project Structure

- **Package: com.suryamj.bank.bankview**
  - `BankView`: The main class handling the user interface and navigation.

- **Package: com.suryamj.bank.dto**
  - `Account`: DTO (Data Transfer Object) class representing a bank account.

- **Other Packages:**
  - `com.suryamj.bank.admin`: Contains classes related to admin functionality.
  - `com.suryamj.bank.createaccount`: Contains classes related to creating accounts.
  - `com.suryamj.bank.customer`: Contains classes related to customer functionality.

## Usage

1. Start the application.
2. Choose an option from the main menu:
   - Option 1: Create an account.
   - Option 2: Manage an account (Admin or Customer).
   - Option 3: Exit the application.

## Classes

### `BankView`

The main class responsible for starting the application, displaying the menu, and handling user input.

### `Account`

A Data Transfer Object (DTO) representing a bank account.

### `AdminView`

A class containing functionalities related to the admin role.

### `CreateAccount`

A class handling the process of creating a new account.

### `CustomerView`

A class containing functionalities related to the customer role.

## License

This Bank Application is licensed under the [MIT License](LICENSE).
