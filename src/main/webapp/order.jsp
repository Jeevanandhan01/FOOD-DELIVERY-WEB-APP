<%@ page import="com.jeeva.dao.CustomerDAO" %>
<%@ page import="com.jeeva.dao.UserDAO" %>
<%@ page import="com.jeeva.models.Order" %>
<%@ page import="com.jeeva.dao.OrderDAO" %>
<%@ page import="com.jeeva.dao.ManagerDAO" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: jeeva
  Date: 18-01-2025
  Time: 10:14 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <style>
        body {
            text-align: center;
        }
        ul {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        div
        {
            padding-bottom: 10px;
            padding-top: 10px;
            margin-bottom: 20px;
            border-style: solid;
            border-color: black;
            margin-left: 400px;
            margin-right: 400px;
            border-radius: 20px;
        }
        #status
        {
            background-color: black;
            color: white;
        }
        #status2
        {
            background-color: black;
            color: white;
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
    <a class="a" href="cart.jsp">Cart</a>
    <a class="a" href="order.jsp">Order</a>
</nav>
<%
    String username = (String) session.getAttribute("uname");
    int uid = CustomerDAO.getId(username);
    String role = UserDAO.getRole(username);
    String loc = (String) session.getAttribute("loc");
    List<Order> orders = new ArrayList<Order>(OrderDAO.getOrders(uid));
    List<Order> m_orders = new ArrayList<>(OrderDAO.getManagerOrders(uid));
    if(role.equalsIgnoreCase("Customer"))
    {
%>
<h1>Customer's Orders Page</h1>
<%

        if(orders.isEmpty())
        {

        %>
<h1>No orders received </h1>
<%
}

    else
    {
        boolean status = true;
        float total = 0;
        for(Order o : orders)
        {
            if(o.getStatus().equalsIgnoreCase("Pending")) {
                status = false;
                total += o.getTotal();
            }
%>
<div>
    <p>Food Name : <%=o.getFood_name()%></p>
    <p>Restaurant Name : <%=o.getRestaurant_name()%></p>
    <p>Total price : <%=o.getTotal()%></p>
    <p>Location : <%=o.getRest_address()%></p>
        <p id="status">Status : <%=o.getStatus()%></p>
</div>
<%
        }
        if(status)
        {


        %>
        <div>
            <form action="payment" method="post">
                <input type="hidden" name="total" value="<%=total%>">
                <input type="hidden" name="location" value="<%=loc%>">
                <input type="hidden" name="username" value="<%=username%>">
                <input type="submit" value="Pay Here">
            </form>
        </div>

<%
        }
}
    }
    else
    {

%>

<h1>Manager's Orders Page</h1>

<%

        if(m_orders.isEmpty())
        {

        %>

<h1>No orders received</h1>
<%
        }
        else
        {
            for(Order o : m_orders)
            {
        %>
            <div>
                <p>Customer name : <%=o.getCustomer_name()%></p>
                <p>Food name : <%=o.getFood_name()%></p>
                <p>Total Price : <%=o.getTotal()%></p>
                <p>User address : <%=loc%></p>
                <p>Contact : <%=o.getUser_contact()%></p>
                <p id="status2">Status : <%=o.getStatus()%></p>
                <form action ="order" method="post">
                    <input type="hidden" name="uname" value="<%=username%>">
                    <input type="hidden" name="uid" value="<%=uid%>">
                    <input type="hidden" name="order" value="true">
                    <input type="submit" value="Process order">
                </form>
            </div>
<%
        }
    }
    }
%>
</body>
</html>
