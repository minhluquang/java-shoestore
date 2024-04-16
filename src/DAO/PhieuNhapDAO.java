package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.PhieuNhap;

public class PhieuNhapDAO {

	public PhieuNhapDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<PhieuNhap> getDanhSachPhieuNhap() {
		ArrayList<PhieuNhap> dspn = new ArrayList<>();
		try {
			String sql = "Select * from goodsreceipt";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();
				pn.setReceipt_id(rs.getInt("receipt_id"));
				pn.setGoodsreceipt_name(rs.getString("goodsreceipt_name"));
				pn.setDate(rs.getString("date"));
				pn.setTotal_price(rs.getInt("total_price"));
				pn.setSupplier_id(rs.getInt("supplier_id"));
				pn.setStaff_id(rs.getInt("staff_id"));
				dspn.add(pn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dspn;
	}

	public static String getTenNhanVienById(int id) {
		String tenNhanVien = "";
		try {
			String sql = "SELECT fullname FROM staff WHERE staff_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				tenNhanVien = rs.getString("fullname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenNhanVien;
	}

	public static PhieuNhap getPhieuNhapById(int id) {
		PhieuNhap pn = null;
		try {
			String sql = "SELECT * FROM goodsreceipt WHERE receipt_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				pn = new PhieuNhap();
				pn.setReceipt_id(rs.getInt("receipt_id"));
				pn.setGoodsreceipt_name(rs.getString("goodsreceipt_name"));
				pn.setDate(rs.getString("date"));
				pn.setTotal_price(rs.getInt("total_price"));
				pn.setSupplier_id(rs.getInt("supplier_id"));
				pn.setStaff_id(rs.getInt("staff_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pn;
	}

}
