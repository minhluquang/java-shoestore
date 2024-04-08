package com.trungtamjava.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCConnection {
	public static Connection getJDBCConection() {
		final String url = "jdbc:mysql://localhost:3306/quanlygiay";
		final String user = "root";
		final String password = "20112004";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,user,password);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Connection connection = getJDBCConection();
		if(connection != null) {
			System.out.println(" Success ");
		} 
		else {
			System.out.println(" Defeat ");
		}
	}
}
