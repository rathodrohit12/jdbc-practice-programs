import java.sql.*;

class JdbcConn1 {

    public static void main(String ss[]) {

        try {
            // new com.mysql.cj.jdbc.Driver();
            /*
             * above can run automatically
             * b/c vendor already made it in static block
             * static{ DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver())}
             * 
             * there is no need to load Driver manually because it static block in Driver
             * class
             */

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb", "root", "root");
            System.out.println(c);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}