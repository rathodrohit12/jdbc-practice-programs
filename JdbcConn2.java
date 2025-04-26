import java.sql.*;

class JdbcConn2 {

    public static void main(String ss[]) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            /*
             * 
             * 
             */

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tkhts", "root", "root");
            System.out.println(c);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}