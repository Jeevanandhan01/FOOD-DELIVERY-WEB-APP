<%--
  Created by IntelliJ IDEA.
  User: jeeva
  Date: 07-01-2025
  Time: 10:44 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
    <style>
        form
        {
            border-style: solid;
            border-color: black;
            margin-left: 400px;
            margin-right: 400px;
            border-radius: 20px;
            padding-bottom: 20px;
        }
    </style>
</head>
<body>
<form action="signup" method="post">
    <center>
        <h1>SIGNUP</h1>


    <label for="name">Name :</label><br>
    <input type="text" id="name" name="name"><br>

    <label for="email">Email :</label><br>
    <input type="email" id="email" name="email"><br>

    <label for="phone">Phone number :</label><br>
    <input type="tel" id="phone" name="phone"><br>

    <label for="uname">Username :</label><br>
    <input type="text" id="uname" name="uname"><br>

    <label for="pwd">Password :</label><br>
    <input type="password" id="pwd" name="pwd"><br>
        <label for="role">Choose your role</label><br><br>
        <select name="role" id="role" required>
            <option value="Customer">Customer</option>
            <option value="Manager">Restaurant Manager</option>
        </select><br>
    <br>
    <br>
    <input type="submit" value="submit">
    </center>
</form>
</body>
</html>
