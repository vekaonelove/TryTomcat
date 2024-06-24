<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Client" %>
<%
    Client client = (Client) session.getAttribute("client");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Your account</title>
    <link rel="stylesheet" type="text/css" href="../resources-front/css/account.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #AA98A9;
            color: #AA98A9;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }

        h1 {
            font-size: 30px;
            margin-bottom: 20px;
            color: #9B29BB;
        }

        h2 {
            font-size: 15px;
            color: #9B29BB;
        }

        label {
            display: block;
            color: #9B29BB;
            margin-bottom: 2px;
        }

        input[type=text], input[type=password], input[type=email] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .account-actions {
            display: flex;
            justify-content: flex-end;
            width: 100%;
            margin-top: 20px;
        }

        .account-actions input[type=submit] {
            background-color: white;
            color: #9B29BB;
            border: 2px solid #AA98A9;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            transition: all 0.3s ease;
            border-radius: 12px;
        }

        .account-actions input[type=submit]:hover {
            background-color: #9B29BB;
            color: #AA98A9;
        }

        .top-buttons {
            position: absolute;
            top: 10px;
            right: 10px;
            display: flex;
            gap: 10px;
        }

        .top-buttons button {
            background-color: white;
            color: #9B29BB;
            border: 2px solid #AA98A9;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease;
            border-radius: 12px;
        }

        .top-buttons button:hover {
            background-color: #9B29BB;
            color: #AA98A9;
        }
    </style>
</head>
<body>
<div class="top-buttons">
    <button onclick="window.location.href='curatorsList.jsp'">Curators</button>
    <button onclick="window.location.href='order.jsp'">Book</button>
    <button onclick="window.history.back()">Go back</button>
</div>
<h1>Welcome, <%= client.getFirstName() %> <%= client.getLastName() %>!</h1>
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
<div class="account-actions">
    <form action="../AccountServlet" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Log Out">
    </form>
    <form action="../AccountServlet" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="submit" value="Delete Account">
    </form>
</div>

</body>
</html>
