package Dao;

import JavaBeans.Status;
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
public class StatusDAO extends Dao {

    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static String sql;

    public List<Status> getAll() throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `status`";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Status> list = new ArrayList<>();
            while (rs.next()) {
                Status s = new Status();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
            return list;
        } finally {
            disconnect();
        }
    }

    public Status getStatusById(int id) throws SQLException, ParseException {
        try {
            connect();
            sql = "SELECT * FROM `status` WHERE id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            Status s = new Status();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            return s;
        } finally {
            disconnect();
        }
    }
}
