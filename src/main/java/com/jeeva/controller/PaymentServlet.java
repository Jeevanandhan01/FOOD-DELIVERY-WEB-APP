package com.jeeva.controller;

import com.jeeva.dao.CartDAO;
import com.jeeva.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "PaymentServlet",urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String location = request.getParameter("location");
        Float total = Float.parseFloat(request.getParameter("total"));
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try
        {
            int i=OrderDAO.remove_orders(username);
            int j=CartDAO.remove_cart_items(username);
            int k=CartDAO.remove_cart(username);
            if(i>0 && j>0 && k>0)
            {
                out.println("Payment successful<br>");
            }
            else
            {
                out.println("Payment failed<br>");
            }

        }
        catch (SQLException e)
        {
            out.println("Error occured while paying");
            e.printStackTrace();
        }


    }
}
