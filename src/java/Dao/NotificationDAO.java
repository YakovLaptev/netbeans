package Dao;

import JavaBeans.Notification;
import JavaBeans.Сustomer;
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
public class NotificationDAO extends Dao {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    public void create(Notification a) throws SQLException {
        try {
            connect();
            sql = "INSERT INTO `notification` VALUES (?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getNumber());
            pstmt.setString(2, a.getAbout());
            pstmt.setDate(3, new java.sql.Date(a.getCreatedDate().getTime()));
            pstmt.setString(4, a.getRecipient());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void delete(Notification a) throws SQLException {
        try {
            connect();
            sql = "DELETE FROM `notification` WHERE number=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, a.getNumber());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public List<Notification> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `notification`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Notification> list = new ArrayList<>();
            while (rs.next()) {
                Notification a = new Notification();
                a.setNumber(rs.getInt("number"));
                a.setAbout(rs.getString("about"));
                a.setCreatedDate(rs.getDate("createdDate"));
                a.setRecipient(rs.getString("customerEmail"));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public List<Notification> getByCustomerEmail(String email) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `notification` WHERE customerEmail=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            List<Notification> list = new ArrayList<>();
            while (rs.next()) {
                Notification a = new Notification();
                a.setNumber(rs.getInt("number"));
                a.setAbout(rs.getString("about"));
                a.setCreatedDate(rs.getDate("createdDate"));
                a.setRecipient(rs.getString("customerEmail"));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public Notification getByNumber(int number) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `notification` WHERE number=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();
            rs.next();
            Notification a = new Notification();
            a.setNumber(rs.getInt("number"));
            a.setAbout(rs.getString("about"));
            a.setCreatedDate(rs.getDate("createdDate"));
            a.setRecipient(rs.getString("customerEmail"));
            return a;
        } finally {
            disconnect();
        }
    }
}
