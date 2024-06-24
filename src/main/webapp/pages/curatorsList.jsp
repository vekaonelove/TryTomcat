<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Curators List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources-front/css/curatorsList.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #AA98A9;
            color: #AA98A9;
            margin: 0;
        }

        h1 {
            font-size: 3em;
            margin-bottom: 20px;
            color: #9B29BB;
            text-align: center;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .curator-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .curator {
            flex: 1 1 calc(50% - 40px);
            box-sizing: border-box;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
        }

        img {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
        }

        .buttons {
            position: absolute;
            top: 10px;
            right: 10px;
            display: flex;
            gap: 10px;
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
<div class="buttons">
    <button onclick="window.location.href='order.jsp'">Book</button>
    <button onclick="window.history.back()">Go back</button>
</div>
<div class="container">
    <h1>Curators List</h1>
    <div class="curator-list">
        <div class="curator">
            <p>Name: Isaac Newton</p>
            <p>Subjects: Physics</p>
            <p>Experience: Senior</p>
            <img src="..\resources-front\images\newton.jpg" alt="Our Physics curator">
        </div>
        <div class="curator">
            <p>Name: Marie Curie</p>
            <p>Subjects: Chemistry</p>
            <img src="..\resources-front\images\curie.jpg" alt="Our chemistry curator">
        </div>
        <div class="curator">
            <p>Name: Albert Camus</p>
            <p>Subjects: French</p>
            <p>Experience: Senior</p>
            <img src="..\resources-front\images\camus.jpg" alt="Our French curator">
        </div>
        <div class="curator">
            <p>Name: Daniil Anishchanka</p>
            <p>Subjects: Biology</p>
            <p>Experience: Junior</p>
            <img src="..\resources-front\images\daniil_mur.jpg" alt="Our biology specialist">
        </div>
    </div>
</div>
</body>
</html>
