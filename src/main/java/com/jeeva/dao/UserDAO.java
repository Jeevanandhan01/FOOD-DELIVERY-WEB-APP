package com.jeeva.dao;

import com.jeeva.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static int insertDetails(String username,String role,String password,String name,String email,String phone ) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        PreparedStatement ps = con.prepareStatement("insert into Users(uname,role,pwd,name,email,phone) values (?,?,?,?,?,?)");
        ps.setString(1,username);
        ps.setString(2,role);
        ps.setString(3,password);
        ps.setString(4,name);
        ps.setString(5,email);
        ps.setString(6,phone);

        return ps.executeUpdate();
    }
    public static boolean isUserValid(String username, String password) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "select * from Users where uname=? and pwd=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return true;
        return false;
    }

    public static String getRole(String username) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "SELECT role FROM Users WHERE uname = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getString("role");
        return null;
    }


}
