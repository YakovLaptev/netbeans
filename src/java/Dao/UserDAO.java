package Dao;

import JavaBeans.Advertising;
import JavaBeans.User;
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
public class UserDAO extends Dao {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    public void create(User a) throws SQLException {
        try {
            connect();
            sql = "INSERT INTO `user` VALUES (?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getLogin());
            pstmt.setString(2, a.getPassword());
            pstmt.setString(3, a.getRole());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void changePass(User a) throws SQLException {
        try {
            connect();
            sql = "UPDATE `user` SET password=? WHERE login=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getPassword());
            pstmt.setString(2, a.getLogin());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void changeRole(User a) throws SQLException {
        try {
            connect();
            sql = "UPDATE `user` SET role=? WHERE login=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getRole());
            pstmt.setString(2, a.getLogin());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void delete(User a) throws SQLException {
        try {
            connect();
            sql = "DELETE FROM `user` WHERE login=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getLogin());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public List<User> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `user`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User a = new User();
                a.setLogin(rs.getString("login"));
                a.setPassword(rs.getString("password"));
                a.setRole(rs.getString("role"));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public User getByLogin(String login) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `user` WHERE login = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            rs.next();
            User a = new User();
            a.setLogin(rs.getString("login"));
            a.setPassword(rs.getString("password"));
            a.setRole(rs.getString("role"));
            return a;
        } finally {
            disconnect();
        }
    }
}
