package DAO;

import java.security.AlgorithmParametersSpi;
import java.sql.ResultSet;
import java.util.ArrayList;import org.apache.poi.hwpf.model.CHPBinTable;

//import com.mysql.cj.xdevapi.Statement;

import DTO.NhanVien;
import DTO.TaiKhoan;

public class NhanVienDAO {
	public static ArrayList<NhanVien> getDanhSachNhanVien(boolean nonAccount, int status) {
		connectDB.getConnection();
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		try {
			String sql = "SELECT staff_id, fullname, email, phone_number, s.status, s.account_id, username, password, account_status, position  "
					+ "FROM staffs s "
					+ "LEFT JOIN accounts a ON a.account_id = s.account_id";
			
			if (status != -1 || nonAccount) {
				sql += " WHERE ";
			}
			
			if (status != -1) {
				sql += " s.status = " + status;
			}
			
			if (nonAccount) {
				if (status != -1) {
					sql += " AND ";
				}
				sql += " s.account_id IS NULL";
			}
			
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
			    nv.setStaffId(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("fullname"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setStaffStatus(rs.getInt("status"));
			    nv.getTaiKhoan().setAccountId(rs.getInt("account_id"));
			    nv.getTaiKhoan().setUsername(rs.getString("username"));
			    nv.getTaiKhoan().setAccountStatus(rs.getInt("account_status"));
			    nv.getTaiKhoan().setPosition(rs.getString("position"));
			    dsnv.add(nv);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dsnv;
	}
	
	public static int generateIdNhanVien(boolean closeDatabase) {
		connectDB.getConnection();
		int idNhanVien = 0;
		
		try {
			String sql = "SELECT staff_id "
					+ "FROM staffs "
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
		
		if (closeDatabase) {
			connectDB.closeConnection();			
		}
		return idNhanVien;
	}
	
	public static boolean isExistNhanVien(int id) {
		connectDB.getConnection();
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM staffs WHERE staff_id = " + id;
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
	
	public static boolean updateNhanVien(int id, String fullname, String email, String phoneNumber, int status) {
	    connectDB.getConnection();
	    boolean success = false;
	    
	    try {
	    	String sql = "UPDATE staffs "
	                + "SET fullname = '" + fullname + "', email = '" + email + "', phone_number = '" + phoneNumber + "', status = '" + status + "'"
	                + " WHERE staff_id = " + id;
	        
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

	
	public static boolean insertNhanVien(String fullname, String email, String phoneNumber, int status) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "INSERT INTO staffs (staff_id, fullname, email, phone_number, status, account_id) "
				     + "VALUES (" + generateIdNhanVien(false) + ", '" + fullname + "', '" + email + "', '" + phoneNumber + "', " + 1 + ", NULL)";
					
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
	
	public static boolean insertDanhSachNhanVien(ArrayList<NhanVien> dsnv) {
		boolean success = true;
		for (NhanVien nv : dsnv) {
			String fullname = nv.getFull_name();
			String email = nv.getEmail();
			String phoneNumber = nv.getPhone_number();
			int status = 1;
			
			success = insertNhanVien(fullname, email, phoneNumber, status);
			if (!success) {
				return success;
			}
		}
		return success;
	}
	
	public static ArrayList<NhanVien> searchNhanVien(String keyword, int searchStatus) {
		connectDB.getConnection();
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		
		try {
			String sql = "SELECT * "
					+ "FROM staffs "
					+ "WHERE (fullname LIKE '%" + keyword + "%' OR email LIKE '%" + keyword + "%' OR phone_number LIKE '%" + keyword + "%')";
			
			if (searchStatus != -1) {
				sql += " AND status = " + searchStatus;
			}
			
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setStaffId(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("fullname"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setStaffStatus(rs.getInt("status"));
			    nv.getTaiKhoan().setAccountId(rs.getInt("account_id"));
			    dsnv.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		connectDB.closeConnection();
		return dsnv;
	}
	
	public static boolean isUsedAccountId(int accountId) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "SELECT * "
					+ "FROM staffs "
					+ "WHERE account_id = " + accountId;
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				connectDB.closeConnection();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean deleteNhanVienById(int staffId) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "UPDATE staffs "
					+ "SET status = 0 "
					+ "WHERE staff_id = " + staffId;
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
	
	public static boolean deleteNhanVienByAccountId(int accountId) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sqlExistAccountId = "SELECT * "
					+ "FROM staffs "
					+ "WHERE account_id = " + accountId;
			ResultSet rs = connectDB.runQuery(sqlExistAccountId);
			if (rs.next()) {
				String sql = "UPDATE staffs "
						+ "SET status = 0 "
						+ "WHERE account_id = " + accountId;
				int i = connectDB.runUpdate(sql);
				if (i > 0) {
					success = true;
				}
			} else {
				success = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean updateAccountIdForStaff(int staffId, int accountId, boolean noAccount) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "UPDATE staffs"
					+ " SET account_id = " + accountId + 
					" WHERE staff_id = " + staffId;
			if (noAccount) {
				sql += " AND account_id IS NULL AND status = 1";
			}
			
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static int getAccountIdByStaffId(int staffId) {
		connectDB.getConnection();
		
		try {
			String sql = "SELECT * FROM `staffs` WHERE staff_id = " + staffId;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				int accountId = rs.getInt("account_id");
				connectDB.closeConnection();
				return accountId;
			}
			connectDB.closeConnection();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	public static NhanVien getNhanVienByID(int id) {
		connectDB.getConnection();
		NhanVien nv = new NhanVien();
		try {
			String sql = " SELECT * FROM staffs WHERE staff_id="+id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
			    nv.setStaffId(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("fullname"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setStaffStatus(rs.getInt("status"));
			    nv.setTaiKhoan(null);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return nv;
	}

	public static NhanVien getNhanVienByAccountID(int id) {
		connectDB.getConnection();
		NhanVien nv = new NhanVien();
		try {
			String sql = " SELECT * FROM staffs WHERE account_id="+id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
			    nv.setStaffId(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("fullname"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setStaffStatus(rs.getInt("status"));
			    nv.setTaiKhoan(null);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return nv;
	}
}
