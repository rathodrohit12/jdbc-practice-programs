import java.sql.*;
import java.io.*;
class CallProcedure
{
public static void main(String a[])
{
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","bhush");

Statement s=c.createStatement();
CallableStatement cs=c.prepareCall("{call myProcedure(?,?,?)}");
cs.setString(1,"ram");
cs.setInt(2,30000);
cs.setString(3,"pune");
int i=cs.executeUpdate();
if(i>0)
System.out.println("Insertedd....");
ResultSet rs=s.executeQuery("select * from Employee");
while(rs.next())
{ 
System.out.println("Name="+rs.getString(1));
System.out.println("salarry="+rs.getInt(2));
System.out.println("location="+rs.getString(3));
}
}catch(SQLException e)
{
e.printStackTrace();
}
catch(Exception e1)
{
e1.printStackTrace();
}
}
}