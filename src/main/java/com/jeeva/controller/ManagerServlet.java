package com.jeeva.controller;

import com.jeeva.dao.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "/com.jeeva.controller.ManagerServlet",urlPatterns = {"/manager"})
public class ManagerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String username = request.getParameter("username");
            String rname = request.getParameter("rname");
            String location = request.getParameter("location");
            String contact = request.getParameter("contact");

            try {
                int i=ManagerDAO.insert(username,rname,location,contact);
                if(i>0){
                    out.println("Restaurant details added successfully<br>");
                    out.println("Please click :<a href=manager.jsp>here</a>");
                }
                else
                {
                    out.println("Error while creating restaurant details<br>");
                    out.println("Please click :<a href=manager.jsp>here</a>");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
