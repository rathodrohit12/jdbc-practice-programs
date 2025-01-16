package com.dbconn;


import com.utils.PropertyReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JdbcConnectionProvider {

    private static final Logger logger = Logger.getLogger(JdbcConnectionProvider.class.getName());
    private static Connection con = null;

    private JdbcConnectionProvider() {
        // Private constructor to prevent instantiation
    }

    public static synchronized Connection getConn() {
        if (con != null) {
            return con;
        }

        try {
            // Load database driver class
            String driver = PropertyReader.getDataProperty("driver");
            String url = PropertyReader.getDataProperty("url");
            String user = PropertyReader.getDataProperty("user");
            String password = PropertyReader.getDataProperty("password");

            // Register the driver and establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            return con;
        } catch (ClassNotFoundException | SQLException e) {
            // Log exception with proper logging framework
            logger.log(Level.SEVERE, "Connection initialization failed", e);
        }

        return null;
    }
}
