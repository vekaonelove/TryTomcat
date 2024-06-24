<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register Page</title>
  <link rel="stylesheet" type="text/css" href="../resources-front/css/register.css">
  <script>
    function validatePassword() {
      var password = document.getElementById('password').value;
      var passwordPattern = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,}$/;
      if (!passwordPattern.test(password)) {
        alert('Password must contain at least 4 characters, including uppercase, lowercase letters and numbers.');
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<h1>Register</h1>
<form action="../register-servlet" method="post" onsubmit="return validatePassword();">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br>
  <label for="firstName">First Name:</label><br>
  <input type="text" id="firstName" name="firstName"><br>
  <label for="lastName">Last Name:</label><br>
  <input type="text" id="lastName" name="lastName"><br>
  <label for="email">Email:</label><br>
  <input type="email" id="email" name="email"><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>