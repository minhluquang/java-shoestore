package DAO;

import java.security.AlgorithmParametersSpi;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale.IsoCountryCode;

import DTO.TaiKhoan;

public class TaiKhoanDAO {
	public static ArrayList<TaiKhoan> getDanhSachTaiKhoan() {
		ArrayList<TaiKhoan> dstk = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM account";
			ResultSet rs = connectDB.runQuery(sql);
			while(rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setAccountId(rs.getInt("account_id"));
				tk.setUsername(rs.getString("username"));
				tk.setPassword(rs.getString("password"));
				tk.setAccountStatus(rs.getInt("account_status"));
				tk.setPosition(rs.getString("position"));
				dstk.add(tk);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return dstk;
	}
	
	public static boolean isExistUsername(String username, int accountId) {
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM account WHERE username = '" + username + "'";
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				if (rs.getInt("account_id") != accountId) {
					isExist = true;
				}
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isExist;
	}
	
	public static boolean isExistIdTaiKhoan(int accountId) {
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM account WHERE account_id = " + accountId;
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				isExist = true;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isExist;
	}
	
	public static int generateIdTaiKhoan() {
		int idTaiKhoan = 0;
		
		try {
			String sql = "SELECT account_id "
					+ "FROM account "
					+ "ORDER BY account_id DESC "
					+ "LIMIT 1";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				int lastId = rs.getInt("account_id");
				idTaiKhoan = lastId + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return idTaiKhoan;
	}
	
	
	public static boolean updateTaiKhoan(int accountId, String username, int accountStatus, String position) {
		boolean success = false;
		
		try {
			String sql = "UPDATE account "
					+ "SET username = '" + username + "', account_status = " + accountStatus + ", position = '" + position + "' "
					+ "WHERE account_id = " + accountId;
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static boolean insertTaiKhoan(int accountId, String username, int accountStatus, String position) {
		boolean success = false;
		
		try {
			String sql = "INSERT INTO account (account_id, username, password, account_status, position) "
					+ "VALUES ("+ accountId +", '" + username + "', 'shopgiay88', " + accountStatus + ", '" + position + "')";
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static ArrayList<TaiKhoan> searchTaiKhoan(String keyword, int searchStatus, String searchRole) {
		ArrayList<TaiKhoan> dstk = new ArrayList<>();
		try {
			String sql = "SELECT * "
					+ "FROM account "
					+ "WHERE (account_id LIKE '%" + keyword + "%' OR  username LIKE '%" + keyword + "%')";
			
			if (searchStatus != -1) {
				sql += " AND account_status = " + searchStatus;
			}
			
			if (!searchRole.isEmpty()) {
				sql += " AND position = '" + searchRole + "'";
			}
			
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setAccountId(rs.getInt("account_id"));
				tk.setUsername(rs.getString("username"));
				tk.setPosition(rs.getString("position"));
				tk.setAccountStatus(rs.getInt("account_status"));
				dstk.add(tk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dstk;
	}
}
