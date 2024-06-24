<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error Order</title>
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

    p {
      color: #9B29BB;
    }

    button {
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

    button:hover {
      background-color: #9B29BB;
      color: #AA98A9;
    }
  </style>
</head>
<body>
<h1>Error</h1>
<p>There was an issue with creating your order. Please try again.</p>
<button onclick="window.history.back()">Go back</button>
</body>
</html>