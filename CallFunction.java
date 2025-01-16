package functions;


import java.sql.*;
import java.io.*;
    class CallFunction
    {
        public static void main(String a[])
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","root");

                CallableStatement cs=c.prepareCall("{?=call getDob(?)}");
                cs.setString(2,"Amit");

                cs.registerOutParameter(1,Types.CHAR);
                cs.execute();
                String b=cs.getString(1);
                System.out.println(b);
            }
            catch(Exception e1)
            {
                System.out.println(e1);
            }
        }
    }


