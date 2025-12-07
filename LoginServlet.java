package com.oems.servlet;

import com.oems.dao.UserDAO;
import com.oems.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Enter email and password.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        User user = userDAO.login(email.trim(), password.trim());
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            // redirect based on role
            String role = user.getRole();
            if ("admin".equals(role)) {
                resp.sendRedirect("admin_dashboard.jsp");
            } else if ("organizer".equals(role)) {
                resp.sendRedirect("organizer_dashboard.jsp");
            } else {
                resp.sendRedirect("attendee_dashboard.jsp");
            }
        } else {
            req.setAttribute("error", "Invalid credentials.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
