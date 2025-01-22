package com.jeeva.controller;

import com.jeeva.dao.FoodDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "FoodEditServlet",urlPatterns = {"/edit"})
public class FoodEditServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String name = request.getParameter("name");
        Float price = Float.parseFloat(request.getParameter("price"));
        int fid = Integer.parseInt(request.getParameter("fid"));
        boolean delete = Boolean.parseBoolean(request.getParameter("delete"));
        boolean avail = Boolean.parseBoolean(request.getParameter("avail"));

        if(delete)
        {
            try {
                int i=FoodDAO.delete(fid);
                if(i>0) {
                    out.println("Food item deleted successfully<br>");
                    out.println("Please click here : <a href=manager.jsp>manager</a>");
                }
                else {
                    out.println("Error in deleting food item");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            try {
                int i=FoodDAO.update(name,price,fid,avail);
                if(i>0) {
                    out.println("Food item edited successfully<br>");
                    out.println("Please click here : <a href=manager.jsp>manager</a>");
                }
                else
                {
                    out.println("Error in updating food item<br>");
                    out.println("Please click here : <a href=manager.jsp>manager</a>");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
