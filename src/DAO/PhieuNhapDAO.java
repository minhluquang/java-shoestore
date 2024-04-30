package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static int generrate_Id() {
		connectDB.getConnection();
		int res = -1;
		try {
			String sql = "SELECT receipt_id FROM goodsreceipts ORDER BY receipt_id DESC LIMIT 1";
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				res = rs.getInt("receipt_id");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return res + 1;
	}

	public static boolean create_GoodsReceipts(int receipt_id, String date, int supplier_id, int staff_id) {
		connectDB.getConnection();
		boolean success = false;
		String sql = "INSERT INTO `goodsreceipts`(`receipt_id`, `date`, `total_price`, `supplier_id`, `staff_id`, `status`) VALUES"
				+ " (?,?,?,?,?,?)";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, receipt_id);
			mystm.setString(2, date);
			mystm.setInt(3, 0);
			mystm.setInt(4, supplier_id);
			mystm.setInt(5, staff_id);
			mystm.setInt(6, 1);

			int i = mystm.executeUpdate();
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

	public static int tinhTongTien(int receipt_id) {
		int sum = 0;
		connectDB.getConnection();
		String sql = "SELECT SUM(input_price * quantity) AS total_price FROM `goodsreceipt_details` WHERE receipt_id = ?";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, receipt_id);
			ResultSet rs = mystm.executeQuery();
			if (rs.next()) {
				sum = rs.getInt("total_price");
			}
			rs.close();
			mystm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	public static boolean update_Total_Price(int receipt_id) {
		connectDB.getConnection();
		boolean success = false;
		int total = tinhTongTien(receipt_id);
		String sql = "UPDATE `goodsreceipts` SET `total_price`= ? WHERE receipt_id = ?";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, total);
			mystm.setInt(2, receipt_id);

			int i = mystm.executeUpdate();
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
}
