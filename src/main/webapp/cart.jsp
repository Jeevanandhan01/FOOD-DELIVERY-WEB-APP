<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.jeeva.dao.CartDAO" %>
<%@ page import="com.jeeva.models.Cart" %>
<%@ page import="com.jeeva.dao.CustomerDAO" %>
<%@ page import="com.jeeva.controller.LoginServlet" %><%--
  Created by IntelliJ IDEA.
  User: jeeva
  Date: 17-01-2025
  Time: 03:13 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
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
    <a class="a" href="cart.jsp">Cart</a>
    <a class="a" href="order.jsp">Order</a>
</nav>
<%
    String username = (String)session.getAttribute("uname");
    int uid = CustomerDAO.getId(username);
    List<String> locs = new ArrayList<>(CustomerDAO.get_location(uid));
    %>
<h2>Customer <%=username%>'s Cart</h2>
<%
    float total_sum = 0;
    List<Cart> carts = new ArrayList<>(CartDAO.get_customer_Cart(uid));
    if (carts.isEmpty()) {
 %>
    <h2>No Food items added into the cart</h2>
<%
}
    else
    {
        for (Cart cart : carts) {
            total_sum += cart.getTotal();
%>
    <div>
        <p>Food Name : <%=cart.getFoodname()%></p>
        <p>Quantity : <%=cart.getQuantity()%></p>
        <p>Price : <%=cart.getPrice()%></p>
        <p>Total : <%=cart.getTotal()%></p>
        <p>Restaurant Name : <%=cart.getRestaurantname()%></p>
        <form action="cartedit" method="post">
            <input type="hidden" value="<%=cart.getRestaurantname()%>" name="rest_name">
            <input type="hidden" value="<%=cart.getFoodname()%>" name="food_name">
            <input type="hidden" value="<%=uid%>" name="uid">
            <input type="hidden" value="<%=cart.getFid()%>" name="fid">
            <input type="submit" value="delete">
        </form>
    </div>
<%
    }
        %>

    <div>
        <p>Total Payable Amount : <%=total_sum%></p>
    </div>

    <div>
    <form method="post" action="order">
        <label for="location" >Choose your location :</label><br>
        <select name="location" id="location">
<%
        for (String l:locs)
        {


%>
            <option value="<%=l%>"><%=l%></option>
            <%
                }
            %>
        </select><br><br>
        <input type="hidden" name="uid" value=<%=uid%>>
        <input type="hidden" name="uname" value="<%=username%>">
        <input type="submit" value="Place order">
    </form>
    </div>

    <%
    }

%>
</body>
</html>
