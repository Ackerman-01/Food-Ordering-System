import java.sql.*;
import java.util.Scanner;

public class User {
    private Connection con;

    public User(Connection con) {
        this.con = con;
    }

    public void viewMenu() {
        try {
            String query = "SELECT * FROM food_items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Menu:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " - ₹" + rs.getDouble("price"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching menu: " + e.getMessage());
        }
    }

    public void placeOrder() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter food ID to order: ");
            int foodId = sc.nextInt();
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            String query = "INSERT INTO orders (food_id, quantity) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, foodId);
            stmt.setInt(2, quantity);
            stmt.executeUpdate();

            System.out.println("Order placed successfully!");
        } catch (Exception e) {
            System.out.println("Error placing order: " + e.getMessage());
        }
    }
}
