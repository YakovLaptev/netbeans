package Dao;

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
public class CustomerDAO extends Dao {
    
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;
    
    public void create(Сustomer a) throws SQLException {
        try {
            connect();
            sql = "INSERT INTO `customer` VALUES (?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getFio());
            pstmt.setDate(2, new java.sql.Date(a.getRegistrationDate().getTime()));
            pstmt.setString(3, a.getEmail());
            pstmt.setString(4, a.getUser().getLogin());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }
    
    public void update(Сustomer a) throws SQLException {
        try {
            connect();
            sql = "UPDATE `customer` SET registrationDate=?, email=?, userLogin=? WHERE fio=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, new java.sql.Date(a.getRegistrationDate().getTime()));
            pstmt.setString(2, a.getEmail());
            pstmt.setString(3, a.getUser().getLogin());            
            pstmt.setString(4, a.getFio());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }
    
    public void delete(Сustomer a) throws SQLException {
        try {
            connect();
            sql = "DELETE FROM `customer` WHERE fio=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getFio());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }
    
    public List<Сustomer> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `customer`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Сustomer> list = new ArrayList<>();
            UserDAO ud = new UserDAO();
            while (rs.next()) {
                Сustomer a = new Сustomer();
                a.setFio(rs.getString("fio"));
                a.setRegistrationDate(rs.getDate("registrationDate"));
                a.setEmail(rs.getString("email"));
                a.setUser(ud.getByLogin(rs.getString("userLogin")));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public Сustomer getByFIO(String fio) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `customer` WHERE fio=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fio);
            rs = pstmt.executeQuery();
            Сustomer a = new Сustomer();
            UserDAO ud = new UserDAO();
            rs.next();
            a.setFio(rs.getString("fio"));
            a.setRegistrationDate(rs.getDate("registrationDate"));
            a.setEmail(rs.getString("email"));
            a.setUser(ud.getByLogin(rs.getString("userLogin")));            
            return a;
        } finally {
            disconnect();
        }
    }
        
    public Сustomer getByEmail(String email) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `customer` WHERE email=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            Сustomer a = new Сustomer();
            UserDAO ud = new UserDAO();
            rs.next();
            a.setFio(rs.getString("fio"));
            a.setRegistrationDate(rs.getDate("registrationDate"));
            a.setEmail(rs.getString("email"));
            a.setUser(ud.getByLogin(rs.getString("userLogin")));            
            return a;
        } finally {
            disconnect();
        }
    }
    
    public Сustomer getByUserLogin(String userLogin) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `customer` WHERE userLogin=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userLogin);
            rs = pstmt.executeQuery();
            Сustomer a = new Сustomer();
            UserDAO ud = new UserDAO();
            rs.next();
            a.setFio(rs.getString("fio"));
            a.setRegistrationDate(rs.getDate("registrationDate"));
            a.setEmail(rs.getString("email"));
            a.setUser(ud.getByLogin(rs.getString("userLogin")));            
            return a;
        } finally {
            disconnect();
        }
    }
}
