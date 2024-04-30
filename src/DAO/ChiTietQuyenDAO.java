package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ChiTietQuyen;

public class ChiTietQuyenDAO {
	public static ArrayList<Integer> getDanhSachQuyenCuaTaiKhoanBangId(int id) {
		connectDB.getConnection();
		ArrayList<Integer> dsqtk = new ArrayList<>();
		
		try {
			String sql = "SELECT * "
					+ "FROM role_details "
					+ "WHERE account_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				dsqtk.add(rs.getInt("role_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dsqtk;
	}
	
	public static boolean deleteTatCaQuyenCuaTaiKhoanBangId(int id) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "DELETE FROM role_details "
					+ "WHERE account_id = " + id;
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
	
	public static boolean insertQuyenVaoTaiKhoan(int role_id, int account_id) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "INSERT INTO role_details (role_id, account_id) "
					+ "VALUES (" + role_id + ", " + account_id + ")";
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
	
	
}
