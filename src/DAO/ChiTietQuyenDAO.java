package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ChiTietQuyen;

public class ChiTietQuyenDAO {
	public static ArrayList<Integer> getDanhSachQuyenCuaTaiKhoanBangId(int id) {
		ArrayList<Integer> dsqtk = new ArrayList<>();
		
		try {
			String sql = "SELECT * "
					+ "FROM role_detail "
					+ "WHERE account_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				dsqtk.add(rs.getInt("role_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsqtk;
	}
	
	public static boolean deleteTatCaQuyenCuaTaiKhoanBangId(int id) {
		boolean success = false;
		
		try {
			String sql = "DELETE FROM role_detail "
					+ "WHERE account_id = " + id;
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static boolean insertQuyenVaoTaiKhoan(int role_id, int account_id) {
		boolean success = false;
		
		try {
			String sql = "INSERT INTO role_detail (role_id, account_id) "
					+ "VALUES (" + role_id + ", " + account_id + ")";
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
}
