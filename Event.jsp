<%@ page import="com.oems.model.User" %>
<%@ page session="true" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null || !"organizer".equals(user.getRole())) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<html><head><link rel="stylesheet" href="css/styles.css"><title>Create Event</title></head>
<body>
<div class="container">
  <h2>Create Event</h2>
  <form method="post" action="event" onsubmit="return validateEvent();">
    <label>Title</label><input name="title" id="title" />
    <label>Description</label><textarea name="description"></textarea>
    <label>Date (YYYY-MM-DD)</label><input name="event_date" id="event_date" />
    <label>Venue</label><input name="venue" />
    <label>Ticket Price</label><input name="ticket_price" id="ticket_price" />
    <label>Capacity</label><input name="capacity" id="capacity" />
    <button type="submit">Create</button>
  </form>
  <script>
  function validateEvent() {
    var t = document.getElementById("title").value.trim();
    var d = document.getElementById("event_date").value.trim();
    var p = document.getElementById("ticket_price").value.trim();
    var c = document.getElementById("capacity").value.trim();
    if (!t || !d || isNaN(parseFloat(p)) || isNaN(parseInt(c))) { alert("Please enter valid fields"); return false; }
    return true;
  }
  </script>
</div>
</body></html>
