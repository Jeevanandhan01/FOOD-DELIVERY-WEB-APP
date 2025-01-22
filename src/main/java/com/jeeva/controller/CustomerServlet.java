package com.jeeva.controller;

import com.jeeva.dao.CustomerDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "/com.jeeva.controller.CustomerServlet",urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        String check = req.getParameter("del_check");
        boolean delete = check != null;
        if(delete)
        {
            try {
                int i= CustomerDAO.remove(username,address);
                if(i>0) {
                    out.println("Customer address has been removed<br>");
                    out.println("Click :<a href='customer.jsp'>here</a>");
                }
                else
                {
                    out.println("Customer address has not been removed enter correct address<br>");
                    out.println("Click :<a href='customer.jsp'>here</a>");
                }
            } catch (SQLException e) {
                out.println("Error while removing customer address<br>");
                out.println("Click :<a href='customer.jsp'>here</a>");
            }
        }
        else {
            int i = CustomerDAO.insert(username, address);
            if (i > 0) {
                out.println("Profile updated successfully");
                out.println("Please click : <a href='customer.jsp'>here</a>");
            } else {
                out.println("Failed to update profile!");
                out.println("Click :<a href='customer.jsp'>here</a>");
            }
        }
    }
}
