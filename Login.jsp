<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><meta charset="utf-8"><title>Login</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
  <h2>Login</h2>
  <form method="post" action="login">
    <label>Email</label><input name="email" />
    <label>Password</label><input type="password" name="password" />
    <button type="submit">Login</button>
  </form>
  <p><a href="register.jsp">Register</a></p>
  <c:if test="${not empty error}"><div class="error">${error}</div></c:if>
  <c:if test="${not empty message}"><div class="message">${message}</div></c:if>
</div>
</body>
</html>
