package com.jeeva.controller;

import com.jeeva.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/com.jeeva.controller.OrderServlet",urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int uid = Integer.parseInt(request.getParameter("uid"));
        String location = request.getParameter("location");
        String username = request.getParameter("uname");
        request.setAttribute("uname", username);
        session.setAttribute("loc", location);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String order = (request.getParameter("order"));
        if (Boolean.parseBoolean(order)) {
            OrderDAO.assign_status(uid);
            out.println("Order Assigned<br>");
            out.println("Click <a href=order.jsp>here</a>");
        }

        else {

            int i = OrderDAO.add(uid);
            if (i > 0) {
                out.println("Order added successfully");
                out.println("<br>");
                out.println("Click : <a href=order.jsp>here</a>");
            } else {
                out.println("Order already added");
                out.println("<br>");
                out.println("Click : <a href=cart.jsp>here</a>");
            }
        }
    }

}
