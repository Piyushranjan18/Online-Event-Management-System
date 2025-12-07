package com.oems.servlet;

import com.oems.dao.EventDAO;
import com.oems.model.Event;
import com.oems.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/event")
public class EventServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // create event
        String title = req.getParameter("title");
        String desc = req.getParameter("description");
        String date = req.getParameter("event_date");
        String venue = req.getParameter("venue");
        String priceS = req.getParameter("ticket_price");
        String capacityS = req.getParameter("capacity");

        if (title == null || title.trim().isEmpty() || date == null || date.trim().isEmpty()) {
            req.setAttribute("error", "Title and Date required.");
            req.getRequestDispatcher("create_event.jsp").forward(req, resp);
            return;
        }

        double price = 0;
        int capacity = 0;
        try {
            price = Double.parseDouble(priceS);
            capacity = Integer.parseInt(capacityS);
        } catch (NumberFormatException ex) {
            req.setAttribute("error", "Invalid price or capacity.");
            req.getRequestDispatcher("create_event.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (!"organizer".equals(user.getRole())) {
            req.setAttribute("error", "Only organizers can create events.");
            req.getRequestDispatcher("create_event.jsp").forward(req, resp);
            return;
        }

        Event ev = new Event();
        ev.setTitle(title.trim());
        ev.setDescription(desc);
        ev.setEventDate(Date.valueOf(date));
        ev.setVenue(venue);
        ev.setTicketPrice(price);
        ev.setCapacity(capacity);
        ev.setOrganizerId(user.getUserId());

        boolean ok = eventDAO.createEvent(ev);
        if (ok) {
            req.setAttribute("message", "Event created and sent for admin approval.");
            req.getRequestDispatcher("organizer_dashboard.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Failed to create event.");
            req.getRequestDispatcher("create_event.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        // show approved events
        List<Event> events = eventDAO.getApprovedEvents();
        req.setAttribute("events", events);
        req.getRequestDispatcher("events.jsp").forward(req, resp);
    }
}
