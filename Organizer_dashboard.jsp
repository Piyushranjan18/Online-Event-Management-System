<%@ page session="true" %>
<%@ page import="com.oems.model.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null || !"organizer".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
%>
<html><head><link rel="stylesheet" href="css/styles.css"><title>Organizer Dashboard</title></head>
<body>
<div class="container">
  <h2>Organizer Dashboard</h2>
  <p>Welcome, <%= user.getName() %></p>
  <p><a href="create_event.jsp">Create Event</a> | <a href="event">View Public Events</a> | <a href="logout">Logout</a></p>
</div>
</body></html>
