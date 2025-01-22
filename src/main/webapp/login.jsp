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
    <title>Login</title>
    <style>
        form{
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
<form action="login" method="post">
    <center>
        <h1>LOGIN</h1>
    <label for="uname" >Username : </label><br>
    <input type="text" id="uname" name="uname"><br>
    <label for="pwd">Password : </label><br>
    <input type="password" id="pwd" name="pwd"><br><br>
    <input type="submit" value="submit">
    </center>
</form>
</body>
</html>
