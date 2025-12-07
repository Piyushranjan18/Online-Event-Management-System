<%@ page session="true" %>
<%@ page import="com.oems.model.User" %>
<%
 User user = (User) session.getAttribute("user");
 if (user == null || !"attendee".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
%>
<html><head><link rel="stylesheet" href="css/styles.css"><title>Attendee Dashboard</title></head>
<body>
<div class="container">
  <h2>Attendee Dashboard</h2>
  <p>Welcome, <%= user.getName() %></p>
  <p><a href="event">Browse Events</a> | <a href="logout">Logout</a></p>
</div>
</body></html>
