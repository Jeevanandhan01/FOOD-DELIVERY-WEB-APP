package com.jeeva.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.SQLException;

import com.jeeva.dao.UserDAO;

@WebServlet(name="/com.jeeva.controller.SignupServlet",urlPatterns = {"/signup"})

public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");
        String role = request.getParameter("role");


        try {

            int i = UserDAO.insertDetails(username,role,password,name,email,phone);
            if(i>0){

                out.println("Sign up successful !<br>");
                out.println("Click here to login : <a href='login.jsp'>here</a>");
            }
            else
            {
                out.println("Sign up failed !<br> ");
                out.println("Click here to signup : <a href='index.jsp'>here</a>");
            }

        } catch (SQLException e) {
            out.println(e.getMessage());
            out.println("Sign up failed !<br>");
            out.println("Click here to signup : <a href='index.jsp'>here</a>");
        }
    }




}

