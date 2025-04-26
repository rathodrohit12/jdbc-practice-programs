import java.sql.*;
import java.io.*;
class RsTypeTest
{
public static void main(String g[])
{
Connection con=null;
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","baba1","baba1");

/// it is for getting database metadeata
DatabaseMetaData dbmd=con.getMetaData();
System.out.println(dbmd.getURL());
System.out.println(dbmd.getDriverName());




Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs=stmt.executeQuery("select * from emp");
System.out.println("Name \t Id");
System.out.println("");
//rs.absolute(2);
int i=0;
while(rs.next())
{
i++;
if(i==5)
{
try{
Thread.sleep(10000);
}catch(Exception e){
}
}
String n=rs.getString("name");
int id=rs.getInt("id");
System.out.println(n+"\t"+id); 
//rs.updateString("name","aaa");
//rs.updateRow();
}
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