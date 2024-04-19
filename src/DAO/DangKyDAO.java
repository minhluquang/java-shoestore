package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.TaiKhoan;

public class DangKyDAO {
	public static boolean register(TaiKhoan tk) {
		 Connection conn = null;
	     PreparedStatement ps = null;	        
	     try {
			conn = connectDB.getConnection();
			String sql = "INSERT INTO account (account_id, username, password, account_status, position) VALUE (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tk.getAccountId());
			ps.setString(2, tk.getUsername());
			ps.setString(3, tk.getPassword());
			ps.setInt(4, tk.getAccountStatus());
			ps.setString(5, tk.getPosition());
			
			int rs = ps.executeUpdate();
			if (rs !=0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		}
	     
	     return false;
	}
	
	
}
