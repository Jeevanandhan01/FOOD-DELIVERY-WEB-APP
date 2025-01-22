package com.jeeva.controller;

import com.jeeva.dao.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "/com.jeeva.controller.CartServlet",urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        int rid = Integer.parseInt(request.getParameter("rid"));
        int quantity = Integer.parseInt(request.getParameter("qty"));
        int fid = Integer.parseInt(request.getParameter("foodId"));
        float price = Float.parseFloat(request.getParameter("price"));


        try {
            int i = CartDAO.insert(name,fid,rid,quantity,price);
            if(i>0){
                out.println("Food items added to the cart !<br>");
                out.println("Please click here : <a href=cart.jsp>cart</a>");
            }
            else
            {
                out.println("Please order the food from same restaurant and dont add the same dish again <br>");
                out.println("Please click here : <a href=cart.jsp>cart</a>");

            }
        } catch (SQLException e) {
            out.println("You cant add the same dish or different restaurant food in the cart: ");
            out.println("Click <a href=customer.jsp>here</a>");
            e.printStackTrace();
        }

    }
}
