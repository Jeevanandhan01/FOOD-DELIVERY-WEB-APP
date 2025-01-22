package com.jeeva.controller;

import com.jeeva.dao.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartEdit",urlPatterns = {"/cartedit"})
public class CartEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String rest_name = request.getParameter("rest_name");
        String food_name = request.getParameter("food_name");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int fid = Integer.parseInt(request.getParameter("fid"));

        int i=CartDAO.remove_cart(uid,fid);
        if(i>0){
            out.println("Cart has been removed");
            out.println("Click : <a href=cart.jsp>here</a>");
        }
        else
        {
            out.println("Error deleting the cart");
            out.println("Click : <a href=cart.jsp>here</a>");
        }


    }
}
