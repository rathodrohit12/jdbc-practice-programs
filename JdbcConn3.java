import java.sql.*;

// witout using DriverManager
class JdbcConn {

    public static void main(String ss[]) {

        try {

            java.sql.Driver driver = new com.mysql.cj.jdbc.Driver();

            java.util.Properties prop = new java.util.Properties();
            prop.put("user", "root");
            prop.put("password", "root");

            Connection c = driver.connect("jdbc:mysql://localhost:3306/tkhts", prop);

            System.out.println(c);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}