package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class connectDB {
	static final String DB_URL = "jdbc:mysql://localhost:3306/backend_java";
	static final String USER = "root";
	static final String PASS = "";

	static Connection con;
	static Statement stm;
	static PreparedStatement prest;

	public static void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet runQuery(String sql) {
		try {
			return stm.executeQuery(sql);
		} catch (Exception e) {
			return null;
		}
	}

	public static int runUpdate(String sql) {
		try {
			return stm.executeUpdate(sql);
		} catch (Exception e) {
			return 0;
		}
	}

	public static PreparedStatement prepareStatement(String sql) {
		try {
			prest = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prest;
	}

	public static void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
		}
	}
}
