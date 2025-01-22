<%--
  Created by IntelliJ IDEA.
  User: jeeva
  Date: 18-01-2025
  Time: 04:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update_Form</title>
    <style>
        body,li
        {
            text-align: center;
        }
        ul
        {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        form
        {
            border-style: solid;
            border-color: black;
            margin-left: 400px;
            margin-right: 400px;
            border-radius: 20px;
            margin-top: 10px;
            padding-top: 10px;
            padding-bottom: 10px;
        }
    </style>
</head>
<body>
<h2>Edit Form</h2>
<%
    String name = request.getParameter("name");
    String fid = request.getParameter("fid");
    String price = request.getParameter("price");
    String avail = request.getParameter("available");
%>
<form method="POST" action="edit">
    <label>Old Name : <%=name%></label><br>
    <label for="name">New Name :</label><br>
    <input type="text" id="name" name="name" value="<%=name%>"><br><br>

    <label>Old price : <%=price%></label><br>
    <label for="price">New Price :</label><br>
    <input type="text" id="price" name="price" value=<%=price%>><br><br>

    <label>Old Availability :<%=avail%></label><br>
    <label for="avail">New Availability :</label><br>
    <select name="avail" id="avail" required>
        <option value="true">Yes</option>
        <option value="false">No</option>
    </select><br><br>
    <label for="delete">Want to delete ?</label><br>
    <select name="delete" id="delete" required>
        <option value="false">No</option>
        <option value="true">Yes</option>
    </select><br><br>
    <input type="hidden" name="fid" value=<%=fid%>>
    <input type="submit" value="Submit">
</form>
</body>
</html>
