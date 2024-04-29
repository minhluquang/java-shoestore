package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.PhieuNhap;

public class PhieuNhapDAO {

	public PhieuNhapDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<PhieuNhap> getDanhSachPhieuNhap() {
		connectDB.getConnection();
		ArrayList<PhieuNhap> dspn = new ArrayList<>();
		try {
			String sql = "Select * from goodsreceipts";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();
				pn.setReceipt_id(rs.getInt("receipt_id"));
				pn.setDate(rs.getString("date"));
				pn.setTotal_price(rs.getInt("total_price"));
				pn.setSupplier_id(rs.getInt("supplier_id"));
				pn.setStaff_id(rs.getInt("staff_id"));
				dspn.add(pn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return dspn;
	}

	public static String getTenNhanVienById(int id) {
		connectDB.getConnection();
		String tenNhanVien = "";
		try {
			String sql = "SELECT fullname FROM staffs WHERE staff_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				tenNhanVien = rs.getString("fullname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return tenNhanVien;
	}

	public static PhieuNhap getPhieuNhapById(int id) {
		connectDB.getConnection();
		PhieuNhap pn = null;
		try {
			String sql = "SELECT * FROM goodsreceipts WHERE receipt_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				pn = new PhieuNhap();
				pn.setReceipt_id(rs.getInt("receipt_id"));
				pn.setDate(rs.getString("date"));
				pn.setTotal_price(rs.getInt("total_price"));
				pn.setSupplier_id(rs.getInt("supplier_id"));
				pn.setStaff_id(rs.getInt("staff_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return pn;
	}

}
