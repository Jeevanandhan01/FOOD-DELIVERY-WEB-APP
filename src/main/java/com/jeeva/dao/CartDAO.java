package com.jeeva.dao;

import com.jeeva.databaseconnection.DatabaseConnection;
import com.jeeva.models.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    public static int insert(String username, int fid, int rid, int quantity, float price) throws SQLException {
        int uid = CustomerDAO.getId(username);
        Connection con = DatabaseConnection.provideConnection();

        int cart_id = get_cartID(uid);
        if (cart_id == -1) {

            String createCartSQL = "INSERT INTO cart(uid, total) VALUES(?, ?)";
            PreparedStatement createCartStmt = con.prepareStatement(createCartSQL, Statement.RETURN_GENERATED_KEYS);
            createCartStmt.setInt(1, uid);
            createCartStmt.setFloat(2, 0); 
            createCartStmt.executeUpdate();

            ResultSet generatedKeys = createCartStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cart_id = generatedKeys.getInt(1);
            } else {
                return 0;
            }
        }


        String sql2 = "INSERT INTO cart_items(cart_id, fid, rid, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setInt(1, cart_id);
        ps2.setInt(2, fid);
        ps2.setInt(3, rid);
        ps2.setInt(4, quantity);
        ps2.setFloat(5, price);
        float total = price * quantity;
        ps2.setFloat(6, total);

        int rs2 = ps2.executeUpdate();
        if (rs2 <= 0) {
            return 0;
        }


        String updateCartSQL = "UPDATE cart SET total = (SELECT SUM(subtotal) FROM cart_items WHERE cart_id = ?) WHERE cart_id = ?";
        PreparedStatement ps3 = con.prepareStatement(updateCartSQL);
        ps3.setInt(1, cart_id);
        ps3.setInt(2, cart_id);
        ps3.executeUpdate();

        return 1;
    }


    public static int get_cartID(int uid) throws SQLException {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "SELECT cart_id FROM cart WHERE uid=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, uid);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("cart_id");
        }
        return -1;
    }


    public static List<Cart> get_customer_Cart(int uid) {
        Connection con = DatabaseConnection.provideConnection();
        String sql = "SELECT u.uid, f.fid, f.name, ci.quantity, ci.price,ci.subtotal, r.rest_name, r.location FROM Cart c JOIN Cart_Items ci ON c.cart_id = ci.cart_id JOIN Food f ON ci.fid = f.fid JOIN Restaurant r ON f.rid = r.rid JOIN Users u ON c.uid = u.uid WHERE c.uid = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            List<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        rs.getFloat(6),
                        rs.getString(7),
                        rs.getString(8)
                );
                carts.add(cart);
            }
            return carts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int remove_cart(int uid, int fid) {
        Connection con = DatabaseConnection.provideConnection();

        try {
            String sq = "select cart_id from Cart where uid = ?";
            PreparedStatement ps = con.prepareStatement(sq);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            int cart_id = -1;
            if (rs.next()) {
                cart_id = rs.getInt("cart_id");
            }
            if (cart_id == -1) {
                return 0;
            }


            String sql = "delete from Cart_Items where cart_id = ? AND fid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cart_id);
            ps.setInt(2, fid);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected <= 0) {
                return 0;
            }


            String sqle = "select count(*) from Cart_Items where cart_id = ?";
            ps = con.prepareStatement(sqle);
            ps.setInt(1, cart_id);
            ResultSet rs2 = ps.executeQuery();
            rs2.next();
            int items = rs2.getInt(1);

            if (items == 0) {
                String sqld = "delete from Cart where uid = ?";
                ps = con.prepareStatement(sqld);
                ps.setInt(1, uid);
                ps.executeUpdate();

            } else {
                String sql_sum = "select sum(subtotal) from Cart_items where cart_id = ?";
                ps = con.prepareStatement(sql_sum);
                ps.setInt(1, cart_id);
                ResultSet rs3 = ps.executeQuery();
                rs3.next();
                int total = rs3.getInt(1);

                String sql_update = "update Cart set total = ? where cart_id = ?";
                ps = con.prepareStatement(sql_update);
                ps.setInt(1, total);
                ps.setInt(2, cart_id);
                int rowsAffected2 = ps.executeUpdate();
                if (rowsAffected2 <= 0) {
                    return 0;
                }

            }

            return 1;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return -1;
    }

    public static int remove_cart_items(String username) throws SQLException {
        int uid = CustomerDAO.getId(username);
        if (uid <= 0)
            return 0;

        Connection con = DatabaseConnection.provideConnection();
        String sql = "delete from cart_items where cart_id = (select cart_id from cart where uid = ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, uid);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected <= 0) {
            return 0;
        }
        return rowsAffected;
    }

    public static int remove_cart(String username) throws SQLException {
        int uid = CustomerDAO.getId(username);

        Connection con = DatabaseConnection.provideConnection();
        String sql = "delete from cart where uid = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, uid);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected <= 0) {
            return 0;
        }
        return rowsAffected;
    }

}
