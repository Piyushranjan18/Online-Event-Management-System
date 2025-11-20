
import java.sql.*;

public class UserService {

    public void registerUser(String name, String email, String pass, String role) {
        try (Connection con = DatabaseConnection.connect()) {
            String q = "INSERT INTO users(name,email,password,role) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, role);
            ps.executeUpdate();
            System.out.println("User Registered Successfully!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
