package com.jeeva.dao;

import com.jeeva.databaseconnection.DatabaseConnection;
import com.jeeva.models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static int add(int uid)  {


        try {

            Connection con = DatabaseConnection.provideConnection();
            String sql_cid = "select cart_id,total from cart where uid=?";
            PreparedStatement ps = con.prepareStatement(sql_cid);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            int cid = -1;
            int total = 0;
            if (rs.next()) {
                cid = rs.getInt(1);
                total = rs.getInt(2);
            }
            if (cid == -1) {
                return cid;
            }



            String sql = "insert into orders(cart_id,total_price) values (?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, total);
            int i = ps.executeUpdate();

            return i;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Order> getOrders(int uid) {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "select u.uid, f.name, r.rest_name, o.total_price, o.status, r.location , u.phone ,r.contact, u.name from orders o join cart c on o.cart_id = c.cart_id join cart_items ci on c.cart_id = ci.cart_id join food f on ci.fid = f.fid join restaurant r on f.rid = r.rid join users u on c.uid = u.uid where u.uid = ?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();

            List<Order> orders = new ArrayList<Order>();
            while (rs.next()) {
                String foodName = rs.getString("name");
                String restName = rs.getString("rest_name");
                int totalPrice = rs.getInt("total_price");
                String status = rs.getString("status");
                String location = rs.getString("location");
                String phone = rs.getString("phone");
                String contact = rs.getString("contact");
                String customerName = rs.getString("name");

                Order order = new Order(uid,foodName, restName, totalPrice, status, location, phone, contact,customerName);
                orders.add(order);
            }
            return orders;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> getManagerOrders(int uid) {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "select u.uid, f.name, r.rest_name, o.total_price, o.status, r.location, u.phone, r.contact, cu.name as customer_name from orders o join cart c on o.cart_id = c.cart_id join cart_items ci on c.cart_id = ci.cart_id join food f on ci.fid = f.fid join restaurant r on f.rid = r.rid join manager m on r.rid = m.rid join users u on m.uid = u.uid join users cu on c.uid = cu.uid where u.uid = ?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();

            List<Order> orders = new ArrayList<Order>();
            while (rs.next()) {
                String foodName = rs.getString("name");
                String restName = rs.getString("rest_name");
                int totalPrice = rs.getInt("total_price");
                String status = rs.getString("status");
                String location = rs.getString("location");
                String phone = rs.getString("phone");
                String contact = rs.getString("contact");
                String customerName = rs.getString("customer_name");

                Order order = new Order(uid,foodName, restName, totalPrice, status, location, phone, contact,customerName);
                orders.add(order);
            }
            return orders;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void assign_status(int uid)
    {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "update orders o join cart ca on o.cart_id = ca.cart_id join customer c on ca.uid = c.uid join users u on c.uid = u.uid join cart_items ci on ca.cart_id = ci.cart_id join food f on ci.fid = f.fid join restaurant r on f.rid = r.rid join manager m on r.rid = m.rid set o.status = 'successful' where m.uid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int remove_orders(String username) throws SQLException {
        int uid = CustomerDAO.getId(username);
        Connection con = DatabaseConnection.provideConnection();
        String sql = "delete from orders where cart_id = (select cart_id from cart where uid = ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, uid);
        return ps.executeUpdate();
    }




}
