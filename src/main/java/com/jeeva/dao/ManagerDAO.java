package com.jeeva.dao;

import com.jeeva.databaseconnection.DatabaseConnection;
import com.jeeva.models.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {



    public static int insert(String username,String rname,String location, String contact) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT uid FROM users WHERE uname = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();

        int uid=-1;
        if(rs.next())
            uid= rs.getInt("uid");


        String restaurant = "INSERT INTO restaurant (rest_name,location,contact) VALUES (?,?,?)";
        ps = con.prepareStatement(restaurant,PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, rname);
        ps.setString(2, location);
        ps.setString(3, contact);


        int res = ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        int rid = -1;
        if(rs.next())
            rid = rs.getInt(1);
        else
            return -1;


        String manager = "INSERT INTO manager VALUES (?, ?);";
        ps = con.prepareStatement(manager);
        ps.setInt(1, uid);
        ps.setInt(2, rid);
        if(ps.executeUpdate()<=0)
            return -1;
        return res;
    }

    public static List<Restaurant> getRestaurants(int uid){
        Connection con = DatabaseConnection.provideConnection();
        String sql = "select r.rid,r.rest_name from restaurant r join  manager m on m.rid = r.rid where m.uid = ?";
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int rid = rs.getInt("rid");
                String restName = rs.getString("rest_name");
                restaurants.add(new Restaurant(rid,restName));
            }
            return restaurants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
