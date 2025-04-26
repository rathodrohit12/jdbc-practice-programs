package com.dbconn;

import com.utils.PropertyReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final  public class ConnectionProvider {

    static Connection con = null;

    private ConnectionProvider() {
    }

    static public synchronized Connection getConn() {

        try {
            if (con == null) {
                Class.forName(PropertyReader.getDataProperty("driver"));
                con = DriverManager.getConnection(PropertyReader.getDataProperty("url"), PropertyReader.getDataProperty("user"), PropertyReader.getDataProperty("password"));
                String pass = PropertyReader.getDataProperty("password");
                System.out.println(pass);
                return con;
            }else{
                return con;
            }
                  
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

