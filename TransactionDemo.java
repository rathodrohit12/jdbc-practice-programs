package sqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionDemo {

	public static void main(String[] args) {
		Connection connection=null;
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/genaral", "root", "bhush");
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement1 = connection
					.prepareStatement("update emp set e_bal=e_bal+? where e_id=?");
			PreparedStatement preparedStatement2 = connection
					.prepareStatement("update emp set e_bal=e_bal-? where e_id=?");
			System.out.println("Enter From Acount id");
			int f_id = sc.nextInt();
			System.out.println("Enter to Acount id");
			int t_id = sc.nextInt();
			System.out.println("Enter Amount");
			int amount = sc.nextInt();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select e_bal from emp where e_id= "+f_id);
			int avl_Amt = 0;
			if (resultSet.next()) {
				avl_Amt = resultSet.getInt("e_bal");
			}
			if (avl_Amt > amount) {
				preparedStatement1.setInt(1, amount);
				preparedStatement1.setInt(2, t_id);
				preparedStatement1.executeUpdate();
				preparedStatement2.setInt(1, amount);
				preparedStatement2.setInt(2, f_id);
				preparedStatement2.executeUpdate();
				connection.commit();
			} else {
				System.out.println("You do not have sufficient balance");
			}
		} catch (Exception e) {
			try {
			e.printStackTrace();
			System.out.println("rolling back");
			//connection.rollback();
			}
			catch (Exception e1) {
			
			}
			
		}
	finally {
		try {
		connection.close();
		}
		catch (Exception e) {
			
		}
	}

	}

}

