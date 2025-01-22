package com.jeeva.dao;

import com.jeeva.databaseconnection.DatabaseConnection;
import com.jeeva.models.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    public static int insert(String name,float price,int id,boolean avail) throws SQLException {
        Connection con=null;
        con = DatabaseConnection.provideConnection();
        String sql="insert into food(name,price,rid,is_available) values(?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setFloat(2,price);
        pstmt.setInt(3,id);
        pstmt.setBoolean(4,avail);

        int i = pstmt.executeUpdate();
        if(i>0){
            return i;
        }
        return -1;
    }

    public static int totalfood(){
        Connection con=null;
        con = DatabaseConnection.provideConnection();
        String sql="select count(*) from food";
        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Food> getFoods(){
        Connection con=null;
        con = DatabaseConnection.provideConnection();
        String sql="select m.uid,f.fid,f.name,f.price,m.rid,f.is_available,r.rest_name,r.location from food f join manager m on f.rid = m.rid join restaurant r on r.rid = m.rid";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Food> foods = new ArrayList<>();

            while (rs.next()) {
                Food food = new Food(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8)
                );
                foods.add(food);
            }
            return foods;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int delete(int fid) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        String sql="delete from food where fid=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,fid);
        int i = pstmt.executeUpdate();
        if(i>0){
            return i;
        }
        return -1;
    }

    public static int update(String name,float price,int fid,boolean avail) throws SQLException {
        Connection con=DatabaseConnection.provideConnection();
        String sql = "UPDATE food SET name = ?, price = ?, is_available = ? WHERE fid = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setFloat(2,price);
        pstmt.setBoolean(3,avail);
        pstmt.setInt(4,fid);
        int i = pstmt.executeUpdate();
        if(i>0){
            return i;
        }
        return -1;
    }
}
