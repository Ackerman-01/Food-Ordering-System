

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    private static Connection con = DBConnection.connect();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Food Booking System ");
            System.out.println("1. Admin ");
            System.out.println("2. User ");
            System.out.println("3. Exit ");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminMenu(sc);
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        sc.close();
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    private static void adminMenu(Scanner sc) {
        System.out.println("Admin Menu");
        System.out.println("1. Add Food Item");
        System.out.println("2. View Food Items");
        System.out.print("Enter your choice: ");
        int adminChoice = sc.nextInt();
        sc.nextLine(); 

        switch (adminChoice) {
            case 1:
                addFoodItem(sc);
                break;
            case 2:
                viewFoodItems();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu...");
        }
    }

    private static void addFoodItem(Scanner sc) {
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        System.out.print("Enter food price: ");
        float foodPrice = sc.nextFloat();

        String query = "INSERT INTO food_items (name, price) VALUES (?, ?)";

        try (var pst = con.prepareStatement(query)) {
            pst.setString(1, foodName);
            pst.setFloat(2, foodPrice);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Food item added successfully!");
            } else {
                System.out.println("Error adding food item.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewFoodItems() {
        String query = "SELECT * FROM food_items";

        try (var pst = con.prepareStatement(query);
             var rs = pst.executeQuery()) {

            System.out.println("Food Items:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                                   ", Price: " + rs.getFloat("price"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void userMenu() {
        User user = new User(con);
        Scanner sc = new Scanner(System.in);

        System.out.println("User Menu");
        System.out.println("1. View Menu");
        System.out.println("2. Place Order");
        System.out.print("Enter your choice: ");
        int userChoice = sc.nextInt();

        switch (userChoice) {
            case 1:
                user.viewMenu();
                break;
            case 2:
                user.placeOrder();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu...");
        }
    }
}
