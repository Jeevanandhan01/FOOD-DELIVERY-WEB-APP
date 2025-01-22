package com.jeeva.controller;

import com.jeeva.dao.CustomerDAO;
import com.jeeva.dao.ManagerDAO;
import com.jeeva.dao.UserDAO;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "/com.jeeva.controller.LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.setAttribute("uname", username);



        try {
            if (UserDAO.isUserValid(username, password)) {
                {
                    if(UserDAO.getRole(username).equalsIgnoreCase("customer"))
                    {
                        response.sendRedirect("customer.jsp");
                    }
                    else
                    {

                        response.sendRedirect("manager.jsp");
                    }
                }
            } else {
                out.println("Invalid credentials<br>");
                out.println("Please login again <a href=login.jsp>here</a>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


