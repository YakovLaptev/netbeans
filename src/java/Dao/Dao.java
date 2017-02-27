package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Yakov
 */
public class Dao {

    DataSource ds;
    Connection connection;
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/advertisingagency?useSSL=false";
    static final String LOGIN = "root";
    static final String PASSWORD = "root";

    public Dao() {

    }

    public void initConnection() {
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa");
        } catch (NamingException ex) {

        }
    }

    public void connect() {
        initConnection();
        try {
            connection = ds.getConnection();
            //connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException ex) {

        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {

        }
    }

}
