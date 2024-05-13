<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register Page</title>
  <link rel="stylesheet" type="text/css" href="../resources-front/css/register.css">

</head>
<body>
<h1>Register</h1>
<form action="../register-servlet" method="post">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br>
  <label for="firstName">First Name:</label><br>
  <input type="text" id="firstName" name="firstName"><br>
  <label for="lastName">Last Name:</label><br>
  <input type="text" id="lastName" name="lastName"><br>
  <label for="email">Email:</label><br>
  <input type="text" id="email" name="email"><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>