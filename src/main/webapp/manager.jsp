<%--
  Created by IntelliJ IDEA.
  User: jeeva
  Date: 09-01-2025
  Time: 09:53 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.jeeva.dao.FoodDAO" %>
<%@ page import="com.jeeva.models.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jeeva.dao.CustomerDAO" %>
<%@ page import="com.jeeva.dao.ManagerDAO" %>
<%@ page import="com.jeeva.models.Restaurant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Manager</title>
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
        div
        {
            border-style: solid;
            border-color: black;
            margin-left: 400px;
            margin-right: 400px;
            border-radius: 20px;
            margin-top: 10px;
            padding-top: 10px;
        }
        nav
        {
            background-color: white;
            color: black;
            display: flex;
            justify-content: center;
            align-items: center;

            .a {
                color: black;
                text-decoration: none;
                border: 2px solid black;
                border-radius:20px;
                padding: 5px 10px;
                margin: 0 10px;
            }
        }
    </style>
</head>
<body>
<nav>
    <a class="a" href="login.jsp">Login</a>
    <a class="a" href="signup.jsp">Signup</a>
    <a class="a" href="customer.jsp">Dashboard</a>
    <a class="a" href="order.jsp">Order</a>
</nav>
<%
    String username = (String) session.getAttribute("uname");
    int uid = CustomerDAO.getId(username);
    List<Restaurant> restaurants = ManagerDAO.getRestaurants(uid);

%>


<h1>Manager Panel</h1>
<h1>Welcome manager <%=username%></h1>

<div><h2>Check your orders <a href="order.jsp">here</a> </h2></div>
<%
    List<Food> fd = FoodDAO.getFoods();
    if(fd != null) {
        for (Food f : fd )
        {
            if(f.getUid()== CustomerDAO.getId(username))
            {
%>
<div>
    <p>Food name : <%= f.getName() %></p>
    <p>Price: ₹<%= f.getPrice() %></p>
    <p>Availability : <%=f.isAvail() ? "Yes" : "No"%></p>
    <form method="post" action="updateform.jsp">
        <input type="hidden" name="fid" value=<%=f.getFid()%>>
        <input type="hidden" name="price" value=<%=f.getPrice()%>>
        <input type="hidden" name="name" value="<%=f.getName()%>">
        <input type="hidden" name="available" value=<%=f.isAvail()%>>
        <button type="submit" >Edit</button>
    </form>
</div>
<%
    }
        }
    }
    if(fd.isEmpty())
    {
%>
<h2>No foods added</h2>
<%
        }

%>
<h2>Add restaurant details here :</h2>
<div>
    <form method="POST" action="manager">
        <input type="hidden" name="username" value="<%= username %>">
        <label for="rname">Restaurant Name :</label><br>
        <input type="text" id="rname" name="rname" required><br><br>


        <label for="location">Address :</label><br>
        <input type="text" id="location" name="location" required><br><br>

        <label for="contact">Contact :</label><br>
        <input type="tel" id="contact" name="contact" required><br><br>

        <input type="submit" value="Submit">
    </form>
</div>
<%
    if(!restaurants.isEmpty())
    {
%>
<h2>Add Dishes here :</h2>
<div>
<form method="POST" action="food">
    <label for ="rid">Choose your restaurant :</label><br><br>
    <select name="rid" id="rid" required>
        <%
            for(Restaurant r : restaurants )
            {
        %>
        <option value="<%=r.getR_id()%>"><%=r.getR_name()%></option>
        <%
            }
        %>
    </select><br><br>

    <label for="name">Food Name :</label><br>
    <input type="text" id="name" name="name" required><br><br>


    <label for="price">Price :</label><br>
    <input type="text" id="price" name="price" required><br><br>

    <label for="avail">Availability :</label><br>
    <select name="avail" id="avail" required>
        <option value="true">Yes</option>
        <option value="false">No</option>
    </select><br><br>

    <input type="submit" value="Submit">
</form>
</div>
<%
    }
%>
</body>
</html>
