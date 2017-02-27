package Dao;

import JavaBeans.Advertising;
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
public class AdvertisignDAO extends Dao {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    public void create(Advertising a) throws SQLException {
        try {
            connect();
            sql = "INSERT INTO `advertising` VALUES (?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.setString(2, a.getCategory());
            pstmt.setInt(3, a.getPrice());
            pstmt.setString(4, a.getBriefDescription());
            pstmt.setString(5, a.getFullDescription());
            pstmt.setString(6, a.getCampaignName());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void update(Advertising a) throws SQLException {
        try {
            connect();
            sql = "UPDATE `advertising` SET category=?, price=?, briefDescription=?, fullDescription=?, campaignName=? WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getCategory());
            pstmt.setInt(2, a.getPrice());
            pstmt.setString(3, a.getBriefDescription());
            pstmt.setString(4, a.getFullDescription());
            pstmt.setString(5, a.getCampaignName());
            pstmt.setString(6, a.getName());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void delete(Advertising a) throws SQLException {
        try {
            connect();
            sql = "DELETE FROM `advertising` WHERE name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, a.getName());
            pstmt.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public List<Advertising> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `advertising`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Advertising> list = new ArrayList<>();
            while (rs.next()) {
                Advertising a = new Advertising();
                a.setName(rs.getString("name"));
                a.setCategory(rs.getString("category"));
                a.setPrice(rs.getInt("price"));
                a.setBriefDescription(rs.getString("briefDescription"));
                a.setFullDescription(rs.getString("fullDescription"));
                a.setCampaignName(rs.getString("campaignName"));
                list.add(a);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public Advertising getByName(String name) throws SQLException {
        try {
            connect();
            sql = "SELECT * FROM `advertising` WHERE name = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();
            Advertising a = new Advertising();
            a.setName(rs.getString("name"));
            a.setCategory(rs.getString("category"));
            a.setPrice(rs.getInt("price"));
            a.setBriefDescription(rs.getString("briefDescription"));
            a.setFullDescription(rs.getString("fullDescription"));
            a.setCampaignName(rs.getString("campaignName"));
            return a;
        } finally {
            disconnect();
        }
    }
}
