package DAO;

import java.security.AlgorithmParametersSpi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale.IsoCountryCode;

import com.mysql.cj.xdevapi.PreparableStatement;

import DTO.NhanVien;
import DTO.TaiKhoan;

public class TaiKhoanDAO {
	public static ArrayList<TaiKhoan> getDanhSachTaiKhoan() {
		connectDB.getConnection();
		ArrayList<TaiKhoan> dstk = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM accounts ";
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
		
		connectDB.closeConnection();
		return dstk;
	}
	
	public static boolean isExistUsername(String username, int accountId) {
		connectDB.getConnection();
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM accounts WHERE username = '" + username + "'";
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				if (rs.getInt("account_id") != accountId) {
					isExist = true;
				}
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isExist;
	}
	
	public static boolean isExistIdTaiKhoan(int accountId) {
		connectDB.getConnection();
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM accounts WHERE account_id = " + accountId;
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				isExist = true;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isExist;
	}
	
	public static boolean isUsedAccountId(int accountId) {
		connectDB.getConnection();
		boolean isUsed = false;
		
		try {
			String sql = "select * from staffs "
					+ "where account_id = " + accountId;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				isUsed = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isUsed;
	}
	
	public static boolean isUsedUsername(String username) {
		connectDB.getConnection();
		boolean isUsed = false;
		
		try {
			String sql = "select * from accounts where username = '" + username + "'";
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				isUsed = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isUsed;
	}
	
	public static int generateIdTaiKhoan() {
		connectDB.getConnection();
		int idTaiKhoan = 0;
		
		try {
			String sql = "SELECT account_id "
					+ "FROM accounts "
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
		
		connectDB.closeConnection();
		return idTaiKhoan;
	}
	
	
	public static boolean updateTaiKhoan(int accountId, String username, int accountStatus, String position) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "UPDATE accounts "
					+ "SET username = '" + username + "', account_status = " + accountStatus + ", position = '" + position + "' "
					+ "WHERE account_id = " + accountId;
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean insertTaiKhoan(String username, String password, int accountStatus, String position) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "INSERT INTO accounts (username, password, account_status, position) "
					    + "VALUES ('" + username + "', '" + password + "', " + accountStatus + ", '" + position + "')";
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean insertDanhSachTaiKhoan(ArrayList<TaiKhoan> dstk) {
		boolean success = true;
		for (TaiKhoan tk : dstk) {
			String username = tk.getUsername();
			String password = tk.getPassword();
			int accountStatus = tk.getAccountStatus();
			String position = tk.getPosition();
			int staffId = tk.getStaffId();
			
			if (isUsedUsername(username)) {
				continue;
			}
			
			if (!NhanVienDAO.isExistNhanVien(staffId)) {
				continue;
			}
			
			success = insertTaiKhoan(username, password, accountStatus, position);
			if (!success) {
				return success;
			} else {
				TaiKhoan tKhoan = getDetailTaiKhoanByUsername(username, true);
				success = NhanVienDAO.updateAccountIdForStaff(staffId, tKhoan.getAccountId(), true);
				if (!success) {
					continue;
				}
			}
		}
		return success;
	}
	
	public static ArrayList<TaiKhoan> searchTaiKhoan(String keyword, int searchStatus, String searchRole) {
		connectDB.getConnection();
		ArrayList<TaiKhoan> dstk = new ArrayList<>();

		try {
			String sql = "SELECT * "
					+ "FROM accounts "
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
		
		connectDB.closeConnection();
		return dstk;
	}
	
	public static TaiKhoan getDetailTaiKhoanByUsername(String username, boolean closeDatabase) {
		connectDB.getConnection();
		TaiKhoan tk = null;
		
		try {
			String sql = "SELECT * FROM accounts WHERE username = '" + username + "'";
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				tk = new TaiKhoan();
				tk.setAccountId(rs.getInt("account_id"));
				tk.setUsername(rs.getString("username"));
				tk.setPassword(rs.getString("password"));
				tk.setAccountStatus(rs.getInt("account_status"));
				tk.setPosition(rs.getString("position"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (closeDatabase) {
			connectDB.closeConnection();			
		}
		return tk;
	}
	
	public static TaiKhoan getDetailTaiKhoanByAccountId(int accountId) {
		connectDB.getConnection();
		TaiKhoan tk = null;
		
		try {
			String sql = "SELECT * FROM accounts WHERE account_id = '" + accountId + "'";
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				tk = new TaiKhoan();
				tk.setAccountId(rs.getInt("account_id"));
				tk.setUsername(rs.getString("username"));
				tk.setPassword(rs.getString("password"));
				tk.setAccountStatus(rs.getInt("account_status"));
				tk.setPosition(rs.getString("position"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();	
		return tk;
	}
	
	public static boolean deleteTaiKhoanById(int accountId) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "UPDATE accounts "
					+ "SET account_status = 0 "
					+ "WHERE account_id = " + accountId;
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean unlockTaiKhoanByAccountId(int accountId) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "UPDATE accounts "
					+ "SET account_status = 1 "
					+ "WHERE account_id = " + accountId;
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean isTruePassword(int accountId, String password) {
		connectDB.getConnection();
		boolean isTrue = false;
		
		try {
			String sql = "SELECT * FROM accounts WHERE account_id = ? AND password = ?";
			PreparedStatement prest = connectDB.prepareStatement(sql);
			prest.setInt(1, accountId);
			prest.setString(2, password);
			
			ResultSet rs = prest.executeQuery();
			if (rs.next()) {
				isTrue = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isTrue;
	}
	
	public static boolean updatePasswordByAccountId(int accountId, String password) {
		connectDB.getConnection();
		boolean isTrue = false;
		
		try {
			String sql = "UPDATE  accounts "
					+ "SET password = ? "
					+ "WHERE account_id = ?";
			PreparedStatement prest = connectDB.prepareStatement(sql);
			prest.setString(1, password);
			prest.setInt(2, accountId);
			
			int i = prest.executeUpdate();
			if (i > 0) {
				isTrue = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isTrue;
	}
}
