package com.oems.servlet;

import com.oems.dao.EventDAO;
import com.oems.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        // show pending events
        List<Event> pending = eventDAO.getPendingEvents();
        req.setAttribute("pendingEvents", pending);
        req.getRequestDispatcher("admin_dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String eventIdS = req.getParameter("event_id");
        if (action == null || eventIdS == null) {
            resp.sendRedirect("admin_dashboard.jsp");
            return;
        }
        try {
            int eventId = Integer.parseInt(eventIdS);
            if ("approve".equals(action)) {
                eventDAO.updateStatus(eventId, "approved");
            } else if ("reject".equals(action)) {
                eventDAO.updateStatus(eventId, "rejected");
            }
        } catch (NumberFormatException ex) {
            // ignore or set message
        }
        resp.sendRedirect("admin");
    }
}
