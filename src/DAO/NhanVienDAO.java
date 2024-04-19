package DAO;

import java.security.AlgorithmParametersSpi;
import java.sql.ResultSet;
import java.util.ArrayList;

//import com.mysql.cj.xdevapi.Statement;

import DTO.NhanVien;
import DTO.TaiKhoan;

public class NhanVienDAO {
	public static ArrayList<NhanVien> getDanhSachNhanVien() {
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		
		try {
			String sql = "SELECT staff_id, fullname, email, phone_number, s.status, username "
					+ "FROM staff s "
					+ "INNER JOIN account a ON a.account_id = s.account_id";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
			    nv.setStaffId(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("fullname"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setStaffStatus(rs.getInt("status"));
			    nv.setUsername(rs.getString("username"));
			    dsnv.add(nv);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return dsnv;
	}
	
	public static int generateIdNhanVien() {
		int idNhanVien = 0;
		
		try {
			String sql = "SELECT staff_id "
					+ "FROM staff "
					+ "ORDER BY staff_id DESC "
					+ "LIMIT 1";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				int lastId = rs.getInt("staff_id");
				idNhanVien = lastId + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idNhanVien;
	}
	
	public static boolean isExistNhanVien(int id) {
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM staff WHERE staff_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				isExist = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isExist;
	}
	
	public static boolean updateNhanVien(int id, String fullname, String email, String phoneNumber, int status, String username) {
		boolean success = false;
		
		try {
			TaiKhoan tk = new TaiKhoan();
			tk = TaiKhoanDAO.getDetailTaiKhoanByUsername(username);
			
			String sql = "UPDATE staff "
			           + "SET fullname = '" + fullname + "', email = '" + email + "', phone_number = '" + phoneNumber + "', status = '" + status + "', account_id = " + tk.getAccountId();
			sql += " WHERE staff_id = " + id;
			
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static boolean insertNhanVien(String fullname, String email, String phoneNumber, int status, String username) {
		boolean success = false;
		
		try {
			TaiKhoan tk = new TaiKhoan();
			tk = TaiKhoanDAO.getDetailTaiKhoanByUsername(username);
			
			String sql = "INSERT INTO staff (staff_id, fullname, email, phone_number, status, account_id) "
					     + "VALUES (" + generateIdNhanVien() + ", '" + fullname + "', '" + email + "', '" + phoneNumber + "', " + status + ", " + tk.getAccountId() + ")";
			
			
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static ArrayList<NhanVien> searchNhanVien(String keyword, int status) {
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		
		try {
			String sql = "SELECT * "
					+ "FROM staff "
					+ "WHERE (fullname LIKE '%" + keyword + "%' OR email LIKE '%" + keyword + "%' OR phone_number LIKE '%" + keyword + "%')";
			
			if (status != -1) {
				sql += " AND status = " + status; 
			}
			
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setStaffId(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("fullname"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setStaffStatus(rs.getInt("status"));
			    nv.setUsername(rs.getString("username"));
			    dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return dsnv;
	}
	
	public static boolean isUsedAccountId(int accountId) {
		boolean success = false;
		
		try {
			String sql = "SELECT * "
					+ "FROM staff "
					+ "WHERE account_id = " + accountId;
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
}
