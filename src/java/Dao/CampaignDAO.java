package Dao;

import JavaBeans.Campaign;
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
public class CampaignDAO extends Dao {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    public void create(Campaign a) throws SQLException {
        try {
            connect();
            sql = "INSERT INTO `campaign` VALUES (?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.setString(2, a.getAbout());
            pstmt.setDate(3, new java.sql.Date(a.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(a.getEndDate().getTime()));
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void update(Campaign a) throws SQLException {
        try {
            connect();
            sql = "UPDATE `campaign` SET about=?, startDate=?, endDate=? WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getAbout());
            pstmt.setDate(2, new java.sql.Date(a.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(a.getEndDate().getTime()));
            pstmt.setString(4, a.getName());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void delete(Campaign a) throws SQLException {
        try {
            connect();
            sql = "DELETE FROM `campaign` WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public List<Campaign> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `campaign`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Campaign> list = new ArrayList<>();
            while (rs.next()) {
                Campaign a = new Campaign();
                a.setName(rs.getString("name"));
                a.setAbout(rs.getString("about"));
                a.setStartDate(rs.getDate("startDate"));
                a.setEndDate(rs.getDate("endDate"));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public Campaign getByName(String name) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `campaign` WHERE name = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();
            Campaign a = new Campaign();
            a.setName(rs.getString("name"));
            a.setAbout(rs.getString("about"));
            a.setStartDate(rs.getDate("startDate"));
            a.setEndDate(rs.getDate("endDate"));
            return a;
        } finally {
            disconnect();
        }
    }
}
