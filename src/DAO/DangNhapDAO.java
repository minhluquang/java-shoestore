package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import DAO.NhanVienDAO;

public class DangNhapDAO {
	
	private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final Preferences preferences = Preferences.userNodeForPackage(DangNhapDAO.class);
    
    public static String login(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB.getConnection();
            String sql = "SELECT * FROM account WHERE username = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            if (!rs.next()) {
                return "invalid_username"; // Tên đăng nhập không tồn tại
            }
            
            int account_id = rs.getInt("account_id");
            int accountStatus = rs.getInt("account_status");
            
            if (!NhanVienDAO.isUsedAccountId(account_id)) {
                return "Unused_account";
            }
            
            if (accountStatus != 1) {
                return "inactive_account"; // Tài khoản không hoạt động
            }
            
            String dbPassword = rs.getString("password");
            if (!password.equals(dbPassword)) {
                return "invalid_password"; // Sai mật khẩu
            }
            
            return "success"; // Đăng nhập thành công
            
        } catch (SQLException e) {
            e.printStackTrace();
            return "sql_error";
        } finally {
            // Đóng các tài nguyên
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    


	public static void saveLoginInfo(String username, String password) {
        preferences.put(PREF_USERNAME, username);
        preferences.put(PREF_PASSWORD, password);
    }

    public static String[] getSavedLoginInfo() {
        String username = preferences.get(PREF_USERNAME, "");
        String password = preferences.get(PREF_PASSWORD, "");
        return new String[] { username, password };
    }
    
    public static void removeLoginInfo() {
        preferences.remove(PREF_USERNAME);
        preferences.remove(PREF_PASSWORD);
    }
    }

