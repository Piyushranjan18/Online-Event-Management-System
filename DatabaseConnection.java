import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/eventdb";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(Exception e) {
            System.out.println("Database Error: " + e.getMessage());
            return null;
        }
    }
}
