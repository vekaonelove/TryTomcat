<%@ page import="model.ExperienceLevel" %>
<%@ page import="model.Subject" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add Curator</title>
</head>
<body>
<h1>Welcome, admin!</h1>
<h2>Add curator</h2>
<a href="curatorsList.jsp" class="view-button">View the list of curators</a>
<form action="${pageContext.request.contextPath}/account-servlet" method="post" enctype="multipart/form-data">
  <label for="name">Name:</label><br>
  <input type="text" id="name" name="name"><br>
  <label for="surname">Surname:</label><br>
  <input type="text" id="surname" name="surname"><br>
  <label for="subjects">Subjects:</label><br>
  <select id="subjects" name="subjects">`
    <% for (Subject subject : Subject.values()) { %>
    <option value="<%= subject.name() %>"><%= subject.name() %></option>
    <% } %>
  </select><br>
  <label for="experience">Experience Level:</label><br>
  <select id="experience" name="experience">
    <% for (ExperienceLevel level : ExperienceLevel.values()) { %>
    <option value="<%= level.name() %>"><%= level.name() %></option>
    <% } %>
  </select><br>
  <label for="email">Email:</label><br>
  <input type="email" id="email" name="email"><br>
  <label for="photo">Photo:</label><br>
  <input type="file" id="photo" name="photo"><br>
  <input type="hidden" name="action" value="Add Curator">
  <input type="submit" value="Add Curator"></form>

<style>
  body {
    position: relative;
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
    font-size: 2em;
    margin-bottom: 5px;
    color: #9B29BB;
  }

  h2 {
    font-size: 1em;
    margin-bottom: 20px;
    color: #9B29BB;
  }


  label {
    display: block;
    margin-bottom: 0px;
    color: #9B29BB;
  }

  input[type=text], input[type=email] {
    width: 100%;
    padding: 12px 20px;
    margin-bottom: 10px;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    transition: all 0.3s ease;
    border-radius: 12px;
  }

  input[type=file] {
    margin-bottom: 10px;
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

  .view-button {
    position: absolute;
    top: 20px;
    right: 40px;
    padding: 10px 20px;
    margin-top: 20px;
    border: none;
    border-radius: 4px;
    background-color: #28a745;
    color: white;
    text-decoration: none;
    transition: all 0.3s ease;
    border-radius: 12px;
  }

  .view-button:hover {
    background-color: beige;
  }
</style>
</body>
</html>