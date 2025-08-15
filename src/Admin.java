import java.sql.*;
import java.util.Scanner;

public class Admin {
    private Connection con;

    public Admin(Connection con) {
        this.con = con;
    }

    public void addFoodItem() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter food name: ");
            String name = sc.nextLine();
            System.out.print("Enter food price: ");
            double price = sc.nextDouble();

            String query = "INSERT INTO food_items (name, price) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.executeUpdate();

            System.out.println("Food item added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding food item: " + e.getMessage());
        }
    }

    public void viewFoodItems() {
        try {
            String query = "SELECT * FROM food_items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Food Items:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " - ₹" + rs.getDouble("price"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching food items: " + e.getMessage());
        }
    }
}
