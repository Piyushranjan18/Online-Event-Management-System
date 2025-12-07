package com.oems.servlet;

import com.oems.dao.UserDAO;
import com.oems.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        // server-side validation
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().length() < 6 ||
            role == null || role.trim().isEmpty()) {
            req.setAttribute("error", "Please fill all fields correctly. Password must be >= 6 characters.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        User u = new User();
        u.setName(name.trim());
        u.setEmail(email.trim());
        u.setRole(role.trim());

        boolean ok = userDAO.register(u, password);
        if (ok) {
            req.setAttribute("message", "Registration successful. Please login.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Registration failed (email might already be used).");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
