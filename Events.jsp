<%@ page import="java.util.List" %>
<%@ page import="com.oems.model.Event" %>
<%@ page session="true" %>
<%
  List<Event> events = (List<Event>) request.getAttribute("events");
  if (events == null) events = new java.util.ArrayList<>();
%>
<html><head><link rel="stylesheet" href="css/styles.css"><title>Events</title></head>
<body>
<div class="container">
  <h2>Approved Events</h2>
  <c:if test="${not empty error}"><div class="error">${error}</div></c:if>
  <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
  <table class="events">
    <tr><th>Title</th><th>Date</th><th>Venue</th><th>Price</th><th>Action</th></tr>
    <% for (Event e: events) { %>
      <tr>
        <td><%= e.getTitle() %></td>
        <td><%= e.getEventDate() %></td>
        <td><%= e.getVenue() %></td>
        <td><%= e.getTicketPrice() %></td>
        <td>
          <form method="post" action="book">
            <input type="hidden" name="event_id" value="<%= e.getEventId() %>"/>
            <input type="number" name="tickets" value="1" min="1" style="width:60px"/>
            <button type="submit">Book</button>
          </form>
        </td>
      </tr>
    <% } %>
  </table>
</div>
</body></html>
