package Dao;

import JavaBeans.Сustomer;
import JavaBeans.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yakov
 */
public class OrderDAO extends Dao {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    public void create(Order a) throws SQLException {
        try {
            connect();
            sql = "INSERT INTO `order` VALUES (?,?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getNumber());
            pstmt.setString(2, a.getCustomer().getEmail());
            pstmt.setString(3, a.getAdvertisingName());
            pstmt.setDate(4, new java.sql.Date(a.getCreatedDate().getTime()));
            pstmt.setInt(5, a.getStatus().getId());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void delete(Order a) throws SQLException {
        try {
            connect();
            sql = "DELETE FROM `order` WHERE number=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getNumber());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public List<Order> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `order`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Order> list = new ArrayList<>();
            CustomerDAO cd = new CustomerDAO();
            StatusDAO sd = new StatusDAO();
            while (rs.next()) {
                Order a = new Order();
                a.setNumber(rs.getInt("number"));
                a.setCustomer(cd.getByEmail(rs.getString("customerEmail")));
                a.setAdvertisingName(rs.getString("advertisingName"));
                a.setCreatedDate(rs.getDate("createdDate"));
                a.setStatus(sd.getStatusById(rs.getInt("statusid")));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public Order getByNumber(int number) throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `order` WHERE number=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();
            Order a = new Order();
            CustomerDAO cd = new CustomerDAO();
            StatusDAO sd = new StatusDAO();
            rs.next();
            a.setNumber(rs.getInt("number"));
            a.setCustomer(cd.getByEmail(rs.getString("customerEmail")));
            a.setAdvertisingName(rs.getString("advertisingName"));
            a.setCreatedDate(rs.getDate("createdDate"));
            a.setStatus(sd.getStatusById(rs.getInt("statusid")));
            return a;
        } finally {
            disconnect();
        }
    }

    public List<Order> getByCustomer(Сustomer a) throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `order` WHERE customerEmail=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getEmail());
            rs = pstmt.executeQuery();
            List<Order> list = new ArrayList<>();
            CustomerDAO cd = new CustomerDAO();
            StatusDAO sd = new StatusDAO();
            while (rs.next()) {
                Order o = new Order();
                o.setNumber(rs.getInt("number"));
                o.setCustomer(cd.getByEmail(rs.getString("customerEmail")));
                o.setAdvertisingName(rs.getString("advertisingName"));
                o.setCreatedDate(rs.getDate("createdDate"));
                o.setStatus(sd.getStatusById(rs.getInt("statusid")));
                list.add(o);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public void changeStatus(Order a) throws SQLException {
        try {
            connect();
            sql = "UPDATE `order` SET statusid=? WHERE number=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getStatus().getId());
            pstmt.setInt(2, a.getNumber());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }
}
