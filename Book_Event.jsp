<%@ page import="com.oems.model.Event" %>
<%
  String qr = (String) request.getAttribute("qrUrl");
  Event ev = (Event) request.getAttribute("event");
  Integer tickets = (Integer) request.getAttribute("tickets");
%>
<html><head><link rel="stylesheet" href="css/styles.css"><title>Booking Confirmation</title></head>
<body>
<div class="container">
  <h2>Booking Confirmation</h2>
  <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
  <p>Event: <b><%= ev != null ? ev.getTitle() : "" %></b></p>
  <p>Tickets: <b><%= tickets != null ? tickets : 0 %></b></p>
  <p>Show this QR at the event:</p>
  <% if (qr != null) { %>
    <img src="<%= qr %>" alt="QR Code"/>
  <% } else { %>
    <p>(QR not generated)</p>
  <% } %>
  <p><a href="event">Back to Events</a></p>
</div>
</body></html>
