package com.jeeva.dao;

import com.jeeva.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public static int insert(String username, String address) {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "insert into customer values((select uid from users where uname = ?),?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, address);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int remove(String username,String address) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "delete from customer where uid = (select uid from users where uname=?) and address = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, address);
        int rs = ps.executeUpdate();
        if (rs > 0) {
            return rs;
        }
        return 0;
    }


    public static int getId(String username){
        try {

            Connection con = DatabaseConnection.provideConnection();
            String sql = "select uid from users where uname = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("uid");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<String> get_location(int uid)
    {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "select address from customer where uid = ?";
        List<String> location = new ArrayList<String>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                location.add(rs.getString("address"));
            }
            return location;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
