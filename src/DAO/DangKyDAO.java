package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.NhanVien;
import DTO.TaiKhoan;

public class DangKyDAO {
	public static boolean register(TaiKhoan tk) {
		 
	     PreparedStatement ps = null;	        
	     try {
			connectDB.getConnection();
			String sql = "INSERT INTO account (account_id, username, password, account_status, position) VALUE (?,?,?,?,?)";
			ps = connectDB.prepareStatement(sql);	
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
	
	public static boolean registerDetail(NhanVien nv) {
		PreparedStatement ps = null;
		try {
			connectDB.getConnection();
			String sql = "INSERT INTO staff (staff_id, fullname, email, phone_number, status,account_id) VALUE (?,?,?,?,?,?)";
			ps = connectDB.prepareStatement(sql);
			ps.setInt(1, nv.getStaffId());
			ps.setString(2, nv.getFull_name());
			ps.setString(3, nv.getEmail());
			ps.setString(4, nv.getPhone_number());
			ps.setInt(5, nv.getStaffStatus());
			ps.setInt(6,nv.getAccount_id());
			
			int rs = ps.executeUpdate();
			if (rs!=0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
}
