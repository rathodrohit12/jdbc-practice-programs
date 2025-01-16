import java.sql.*;
import java.io.*;
class MetaResultSet
{
public static void main(String g[])
{
Connection con=null;
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from emp");
 
ResultSetMetaData rsmd=rs.getMetaData();

System.out.println(rsmd.getColumnCount());
System.out.println(rsmd.getColumnName(1));
System.out.println(rsmd.getColumnName(2));
System.out.println(rsmd.getTableName(1));
System.out.println(rsmd.getColumnType(1));
System.out.println(rsmd.getColumnType(2));
System.out.println(rsmd.getColumnTypeName(1));
System.out.println(rsmd.getColumnTypeName(2));

}catch(Exception e)
{
System.out.println(e);
}
finally
{
try
{
con.close();
}catch(Exception e){}}
}
}