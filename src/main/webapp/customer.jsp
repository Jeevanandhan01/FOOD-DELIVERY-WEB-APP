
<%@ page import="com.jeeva.dao.FoodDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jeeva.models.Food" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
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
            border-style: solid;
            border-color: black;
            margin-left: 400px;
            margin-right: 400px;
            border-radius: 20px;
            margin-bottom: 20px;
        }
        #qty
        {
            width: 30px;
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

<%
    String username = (String) session.getAttribute("uname");
%>
<nav>
    <a class="a" href="login.jsp">Login</a>
    <a class="a" href="signup.jsp">Signup</a>
    <a class="a" href="customer.jsp">Dashboard</a>
    <a class="a" href="cart.jsp">Cart</a>
    <a class="a" href="order.jsp">Order</a>
</nav>
<h1>Welcome customer <%=username%>!</h1>
<div><h2>Check your cart <a href="cart.jsp">here</a></h2></div>
<div><h2>Check your orders <a href="order.jsp">here</a> </h2></div>
<h2>Food Menus:</h2>

<%
    List<Food> fd = FoodDAO.getFoods();
    if(fd != null) {
        for (Food f : fd )
        {
            if(f.isAvail())
            {
 %>
    <div>
        <p>Food name : <%= f.getName() %></p>
        <p>Price: ₹<%= f.getPrice() %></p>
        <p>Restaurant name : <%=f.getRname()%></p>
        <p>Restaurant location : <%=f.getLocation()%></p>
        <form method="post" action="cart">
            <label for="qty">Enter quantity : </label>
            <input type="number" name="qty" id="qty" value="1">
            <input type="hidden" name="rid" value="<%=f.getRid()%>">
            <input type="hidden" name="foodId" value="<%=f.getFid() %>">
            <input type="hidden" name="username" value=<%=username%>>
            <input type="hidden" name="price" value="<%=f.getPrice()%>">
            <br><br>
            <button type="submit" >Add to Cart</button>
        </form>
    </div>
    <br>
<%
        }
    }
        }
    else
    {

%>
<h2>No foods available</h2>
<%
    }

%>
<div>
    <h2>Add your address:</h2>
    <form method="POST" action="customer">
        <input type="hidden" name="username" value="<%= username %>">
        <label for="address">Address:</label><br>
        <input type="text"  name="address" required><br><br>
        <input type="submit" value="Submit">
    </form>
</div>

<div>
    <h2>Remove your address:</h2>
    <form method="POST" action="customer">
        <input type="hidden" name="username" value="<%= username %>">
        <input type="hidden" name="del_check" value="true">
        <label for="address">Address:</label><br>
        <input type="text" id="address" name="address" required><br><br>
        <input type="submit" value="Submit">
    </form>
</div>

</body>
</html>
