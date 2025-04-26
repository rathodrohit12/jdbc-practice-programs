import java.sql.*;
import java.io.*;
class callfunc
{
public static void main(String a[])
{
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

CallableStatement cs=c.prepareCall("{?=call sumdisp(?,?)}");
cs.setInt(2,10);
cs.setInt(3,29);
cs.registerOutParameter(1,Types.INTEGER);
cs.execute();
int b=cs.getInt(1);
System.out.println(b);
}
catch(Exception e1)
{
System.out.println(e1);
}
}}