package com.jeeva.controller;

import com.jeeva.dao.FoodDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="FoodServlet",urlPatterns = {"/food"})
public class FoodServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        boolean avail = Boolean.parseBoolean(req.getParameter("avail"));
        int rid = Integer.parseInt(req.getParameter("rid"));

        try {
            int i = FoodDAO.insert(name,price,rid,avail);
            if (i > 0) {
                out.println("Food added successfully<br>");
                out.println("Click here : <a href='manager.jsp'>Manager</a>");
            }
            else
            {
                out.println("Error while adding food");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
