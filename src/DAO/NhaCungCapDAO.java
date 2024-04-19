package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.NhaCungCap;

public class NhaCungCapDAO {

	public static ArrayList<NhaCungCap> getDanhSachNhaCungCap() {
		ArrayList<NhaCungCap> dsncc = new ArrayList<>();
		try {
			String sql = "Select * from supplier";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setSupplier_id(rs.getInt("supplier_id"));
				ncc.setSupplier_name(rs.getString("supplier_name"));
				ncc.setSupplier_name(rs.getString("supplier_address"));
				dsncc.add(ncc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsncc;
	}

	public static String getTenNhaCungCapById(int id) {
		connectDB.getConnection();
		String tenNhaCungCap = "";
		
		try {
			String sql = "SELECT supplier_name FROM supplier WHERE supplier_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				tenNhaCungCap = rs.getString("supplier_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return tenNhaCungCap;
	}
}
