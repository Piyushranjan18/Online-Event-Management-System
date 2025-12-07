package com.oems.servlet;

import com.oems.dao.BookingDAO;
import com.oems.dao.EventDAO;
import com.oems.model.Event;
import com.oems.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO = new BookingDAO();
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String eventIdS = req.getParameter("event_id");
        String ticketsS = req.getParameter("tickets");

        int eventId;
        int tickets;
        try {
            eventId = Integer.parseInt(eventIdS);
            tickets = Integer.parseInt(ticketsS);
            if (tickets <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            req.setAttribute("error", "Invalid input for event or tickets.");
            req.getRequestDispatcher("events.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

        boolean ok = bookingDAO.createBooking(eventId, user.getUserId(), tickets);
        if (!ok) {
            req.setAttribute("error", "Booking failed: Not enough tickets or server error.");
            req.getRequestDispatcher("events.jsp").forward(req, resp);
            return;
        }

        // Generate QR code url (Google Chart API) - innovation feature
        Event ev = eventDAO.getEventById(eventId);
        String qrData = "event:" + eventId + "|user:" + user.getUserId() + "|tickets:" + tickets;
        String qrUrl = "https://chart.googleapis.com/chart?chs=300x300&cht=qr&chl=" + URLEncoder.encode(qrData, "UTF-8");

        req.setAttribute("message", "Booking successful!");
        req.setAttribute("qrUrl", qrUrl);
        req.setAttribute("event", ev);
        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher("book_event.jsp").forward(req, resp);
    }
}
