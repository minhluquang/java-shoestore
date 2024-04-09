package DAO;

import java.sql.*;

public class connectDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/backend_java";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Đang kết nối tới cơ sở dữ liệu...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Đã kết nối thành công!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ResultSet runQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int runUpdate(String sql) {
        Connection conn = null;
        Statement stmt = null;
        int rowsAffected = 0;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rowsAffected = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
