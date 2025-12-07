<%@ page session="true" %>
<%@ page import="com.oems.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oems.model.Event" %>
<%
 User user = (User) session.getAttribute("user");
 if (user == null || !"admin".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
 List<Event> pending = (List<Event>) request.getAttribute("pendingEvents");
 if (pending == null) pending = new java.util.ArrayList<>();
%>
<html><head><link rel="stylesheet" href="css/styles.css"><title>Admin Dashboard</title></head>
<body>
<div class="container">
  <h2>Admin Dashboard</h2>
  <p>Welcome, <%= user.getName() %></p>
  <h3>Pending Events</h3>
  <table>
    <tr><th>Title</th><th>Date</th><th>Organizer</th><th>Action</th></tr>
    <% for (Event e: pending) { %>
      <tr>
        <td><%= e.getTitle() %></td>
        <td><%= e.getEventDate() %></td>
        <td><%= e.getOrganizerId() %></td>
        <td>
           <form method="post" action="admin">
             <input type="hidden" name="event_id" value="<%= e.getEventId() %>"/>
             <button name="action" value="approve">Approve</button>
             <button name="action" value="reject">Reject</button>
           </form>
        </td>
      </tr>
    <% } %>
  </table>
  <p><a href="logout">Logout</a></p>
</div>
</body></html>
