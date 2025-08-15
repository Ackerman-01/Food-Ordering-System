import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_booking", "root", "root");
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return con;
    }
}
