# Food Booking System

A simple, console-based food booking system built with Java and MySQL. This application provides a straightforward interface for both customers and administrators to manage and place food orders.

## Features

* **Dual User Roles:** The system supports two distinct roles:
    * **Admin:** Can add new food items to the menu and view all available items.
    * **User:** Can browse the menu and place orders.
* **Database Integration:** Connects to a MySQL database to store and retrieve food items and orders, ensuring data persistence.
* **Command-Line Interface:** A simple and intuitive command-line interface for easy interaction.

## Technologies Used

* **Java:** The core programming language used for the application logic.
* **MySQL:** The relational database used to store and manage data.

## How to Get Started

### Prerequisites

* Java Development Kit (JDK) installed
* MySQL Server installed and running

### Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/your-repository-name.git](https://github.com/your-username/your-repository-name.git)
    ```
2.  **Database Setup:**
    * Create a new database in MySQL named `food_booking`.
    * Create two tables in the `food_booking` database:
        ```sql
        CREATE TABLE food_items (
            id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            price DECIMAL(10, 2) NOT NULL
        );

        CREATE TABLE orders (
            id INT AUTO_INCREMENT PRIMARY KEY,
            food_id INT,
            quantity INT,
            FOREIGN KEY (food_id) REFERENCES food_items(id)
        );
        ```
3.  **Compile and Run:**
    * Open your terminal or command prompt.
    * Navigate to the project's source directory.
    * Compile the Java files:
        ```bash
        javac *.java
        ```
    * Run the main application:
        ```bash
        java Main
        ```

## How to Use

Once the application is running, you will be presented with a main menu:

* **Admin:**
    1.  Select `1` for the Admin menu.
    2.  Choose to either add a new food item or view the current menu.
* **User:**
    1.  Select `2` for the User menu.
    2.  Choose to view the menu or place an order by entering the food item's ID and quantity.
