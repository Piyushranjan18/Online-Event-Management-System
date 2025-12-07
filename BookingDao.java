package com.oems.dao;

import com.oems.model.Booking;
import com.oems.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean createBooking(int eventId, int userId, int tickets) {
        String checkSql = "SELECT SUM(tickets) as sold FROM bookings WHERE event_id = ?";
        String capacitySql = "SELECT capacity FROM events WHERE event_id = ?";
        String insertSql = "INSERT INTO bookings(event_id, user_id, tickets) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection()) {
            // check capacity
            try (PreparedStatement psCap = con.prepareStatement(capacitySql)) {
                psCap.setInt(1, eventId);
                try (ResultSet rs = psCap.executeQuery()) {
                    if (rs.next()) {
                        int capacity = rs.getInt("capacity");
                        int sold = 0;
                        try (PreparedStatement psSold = con.prepareStatement(checkSql)) {
                            psSold.setInt(1, eventId);
                            try (ResultSet rs2 = psSold.executeQuery()) {
                                if (rs2.next()) sold = rs2.getInt("sold");
                            }
                        }
                        if (sold + tickets > capacity) {
                            return false; // not enough tickets
                        }
                    } else {
                        return false;
                    }
                }
            }

            try (PreparedStatement psInsert = con.prepareStatement(insertSql)) {
                psInsert.setInt(1, eventId);
                psInsert.setInt(2, userId);
                psInsert.setInt(3, tickets);
                psInsert.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY booked_at DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setBookingId(rs.getInt("booking_id"));
                    b.setEventId(rs.getInt("event_id"));
                    b.setUserId(rs.getInt("user_id"));
                    b.setTickets(rs.getInt("tickets"));
                    b.setBookedAt(rs.getTimestamp("booked_at"));
                    list.add(b);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
