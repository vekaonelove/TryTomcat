<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Client" %>
<%
    Client client = (Client) session.getAttribute("client");
%>
<html>
<head>
    <title>Your account</title>
    <link rel="stylesheet" type="text/css" href="../resources-front/css/account.css">

</head>
<body>
<h1>Welcome, <%= client.getFirstName() %> <%= client.getLastName() %>!</h1>
<p>Username: <%= client.getUsername() %></p>
<p>Email: <%= client.getEmail() %></p>

<h2>Update your information</h2>
<form action="../account-servlet" method="post">
    <input type="hidden" name="action" value="update">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username" value="<%= client.getUsername() %>"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" value="<%= client.getPassword() %>"><br>
    <label for="firstName">First Name:</label><br>
    <input type="text" id="firstName" name="firstName" value="<%= client.getFirstName() %>"><br>
    <label for="lastName">Last Name:</label><br>
    <input type="text" id="lastName" name="lastName" value="<%= client.getLastName() %>"><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" value="<%= client.getEmail() %>"><br>
    <input type="submit" value="Update">
</form>

<h2>Account Actions</h2>
<form action="../AccountServlet" method="post">
    <input type="hidden" name="action" value="logout">
    <input type="submit" value="Log Out">
</form>
<form action="../AccountServlet" method="post">
    <input type="hidden" name="action" value="delete">
    <input type="submit" value="Delete Account">
</form>

</body>
</html>