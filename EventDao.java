package com.oems.dao;

import com.oems.model.Event;
import com.oems.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public boolean createEvent(Event ev) {
        String sql = "INSERT INTO events(title, description, event_date, venue, ticket_price, capacity, organizer_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ev.getTitle());
            ps.setString(2, ev.getDescription());
            ps.setDate(3, ev.getEventDate());
            ps.setString(4, ev.getVenue());
            ps.setDouble(5, ev.getTicketPrice());
            ps.setInt(6, ev.getCapacity());
            ps.setInt(7, ev.getOrganizerId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Event> getApprovedEvents() {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE status='approved' ORDER BY event_date";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Event e = new Event();
                e.setEventId(rs.getInt("event_id"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setEventDate(rs.getDate("event_date"));
                e.setVenue(rs.getString("venue"));
                e.setTicketPrice(rs.getDouble("ticket_price"));
                e.setCapacity(rs.getInt("capacity"));
                e.setOrganizerId(rs.getInt("organizer_id"));
                e.setStatus(rs.getString("status"));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Event> getPendingEvents() {
        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE status='pending' ORDER BY created_at";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Event e = new Event();
                e.setEventId(rs.getInt("event_id"));
                e.setTitle(rs.getString("title"));
                e.setDescription(rs.getString("description"));
                e.setEventDate(rs.getDate("event_date"));
                e.setVenue(rs.getString("venue"));
                e.setTicketPrice(rs.getDouble("ticket_price"));
                e.setCapacity(rs.getInt("capacity"));
                e.setOrganizerId(rs.getInt("organizer_id"));
                e.setStatus(rs.getString("status"));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatus(int eventId, String status) {
        String sql = "UPDATE events SET status = ? WHERE event_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, eventId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE event_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Event e = new Event();
                    e.setEventId(rs.getInt("event_id"));
                    e.setTitle(rs.getString("title"));
                    e.setDescription(rs.getString("description"));
                    e.setEventDate(rs.getDate("event_date"));
                    e.setVenue(rs.getString("venue"));
                    e.setTicketPrice(rs.getDouble("ticket_price"));
                    e.setCapacity(rs.getInt("capacity"));
                    e.setOrganizerId(rs.getInt("organizer_id"));
                    e.setStatus(rs.getString("status"));
                    return e;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
