package com.oems.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int eventId;
    private int userId;
    private int tickets;
    private Timestamp bookedAt;

    public Booking() {}

    // getters and setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getTickets() { return tickets; }
    public void setTickets(int tickets) { this.tickets = tickets; }
    public Timestamp getBookedAt() { return bookedAt; }
    public void setBookedAt(Timestamp bookedAt) { this.bookedAt = bookedAt; }
}
