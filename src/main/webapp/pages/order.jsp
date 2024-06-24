<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Curator" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Order</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #AA98A9;
            color: #AA98A9;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            font-size: 3em;
            margin-bottom: 20px;
            color: #9B29BB;
        }

        label {
            display: block;
            margin-bottom: 0px;
            color: #9B29BB;
        }

        input[type=text] {
            width: 100%;
            padding: 12px 20px;
            margin-bottom: 2px;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
            transition: all 0.3s ease;
            border-radius: 12px;
        }

        label {
            display: block;
            margin-bottom: 0px;
            color: #9B29BB;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin-bottom: 8px;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
            transition: all 0.3s ease;
            border-radius: 12px;
        }

        input[type=submit] {
            background-color: white;
            color: #9B29BB;
            border: 2px solid #AA98A9;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-bottom: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
            border-radius: 12px;
        }

        input[type=submit]:hover {
            background-color: #9B29BB;
            color: #AA98A9;
        }
    </style>
</head>
<body>
<h1>Create Order</h1>
<form action="${pageContext.request.contextPath}/order-servlet" method="post">
    <label for="curator">Choose a curator:</label>
    <select id="curator" name="curator">
        <option value="1">Isaac Newton</option>
        <option value="2">Marie Curie</option>
        <option value="3">Albert Camus</option>
        <option value="4">Daniil Anishchanka</option>
    </select>
    <input type="submit" value="Create Order">
</form>
</body>
</html>
