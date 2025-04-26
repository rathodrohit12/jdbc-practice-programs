package com.dbconn;

import com.utils.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionProvider {

    private static Connection connection;

    // Private constructor to prevent instantiation
    private JdbcConnectionProvider() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(PropertyReader.getDataProperty("driver"));
                connection = DriverManager.getConnection(PropertyReader.getDataProperty("url"), PropertyReader.getDataProperty("user"), PropertyReader.getDataProperty("password"));
            }catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
