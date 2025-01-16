import java.sql.Connection;
import java.sql.DriverManager;


class JdbcConn {

    public static void main(String ss[]) {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            // jdbc:protocol://hostname:port/database

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb", "root", "root");
            System.out.println(c);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}