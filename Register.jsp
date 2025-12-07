<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><meta charset="utf-8"><title>Register</title>
<link rel="stylesheet" href="css/styles.css">
<script>
function validate() {
  var name = document.getElementById("name").value.trim();
  var email = document.getElementById("email").value.trim();
  var pass = document.getElementById("password").value;
  if (!name || !email || pass.length < 6) {
    alert("Please complete the form. Password must be >=6 chars.");
    return false;
  }
  var re = /\S+@\S+\.\S+/;
  if (!re.test(email)) { alert("Invalid email."); return false; }
  return true;
}
</script>
</head>
<body>
<div class="container">
  <h2>Register</h2>
  <c:if test="${not empty error}"><div class="error">${error}</div></c:if>
  <form method="post" action="register" onsubmit="return validate();">
    <label>Name</label><input id="name" name="name" />
    <label>Email</label><input id="email" name="email" />
    <label>Password</label><input type="password" id="password" name="password" />
    <label>Role</label>
    <select name="role">
      <option value="attendee">Attendee</option>
      <option value="organizer">Organizer</option>
    </select>
    <button type="submit">Register</button>
  </form>
  <p><a href="login.jsp">Already have an account? Login</a></p>
</div>
</body>
</html>
