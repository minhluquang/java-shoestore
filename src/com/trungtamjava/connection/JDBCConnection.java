
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
/*
package com.trungtamjava.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCConnection {
    public static Connection getJDBCConection() {
        final String url = "jdbc:mysql://localhost:3306/quanlygiay";
        final String user = "root";
        final String password = "20112004";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection connection = getJDBCConection();
        if (connection != null) {
            System.out.println("Success");
            // Log ra bảng account
            logAccountTable(connection);
        } else {
            System.out.println("Defeat");
        }
    }

    public static void logAccountTable(Connection connection) {
        String query = "SELECT * FROM account";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Lấy các cột từ dòng hiện tại
                int accountId = resultSet.getInt("account_id");
                String username = resultSet.getString("full_name");
                String password = resultSet.getString("password");

                // In ra console
                System.out.println("Account ID: " + accountId + ", Username: " + username + ", Password: " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
*/