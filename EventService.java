import java.sql.*;
import java.util.*;

public class EventService {

    public void createEvent(String title, String desc, String date, int organizerId) {
        try (Connection con = DatabaseConnection.connect()) {
            String q = "INSERT INTO events(title, description, date, organizer_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, date);
            ps.setInt(4, organizerId);
            ps.executeUpdate();
            System.out.println("Event Created Successfully!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void approveEvent(int eventId) {
        try (Connection con = DatabaseConnection.connect()) {
            String q = "UPDATE events SET status='approved' WHERE event_id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, eventId);
            ps.executeUpdate();
            System.out.println("Event Approved!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
