package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.PhieuNhap;
import DTO.SanPhamDTO;

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

	public static String getInPutPriceByProductId(String product_id) {
		connectDB.getConnection();
		String res = "";
		String sql = "Select output_price from products where product_id = " + product_id;
		try {
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				res = rs.getString("output_price");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}

	public static ArrayList<SanPhamDTO> searchObjectById(String enteredText) {
		connectDB.getConnection();
		ArrayList<SanPhamDTO> ds = new ArrayList<>();
		String sql = "SELECT * FROM products WHERE product_name LIKE '%" + enteredText + "%'";
		try {
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				int category_id = rs.getInt("category_id");
				int brand_id = rs.getInt("brand_id");
				String product_name = rs.getString("product_name");
				int output_price = rs.getInt("output_price");
				String country = rs.getString("country");
				int year_of_product = rs.getInt("year_of_product");
				String image_path = rs.getString("image_path");
				int quantity = rs.getInt("quantity");
				boolean status = rs.getBoolean("status");

				SanPhamDTO product = new SanPhamDTO(product_id, category_id, brand_id, product_name, output_price,
						country, year_of_product, image_path, quantity, status);
				ds.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return ds;
	}

	public static ArrayList<PhieuNhap> searchByDays(String bd, String kt, String query) {
		connectDB.getConnection();
		ArrayList<PhieuNhap> arr = new ArrayList<>();
		String sql = "SELECT * FROM `goodsreceipts` WHERE date BETWEEN " + "'" + bd + "'" + "AND" + "'" + kt + "'";
		if (!query.equals("")) {
			sql = "SELECT * FROM goodsreceipts INNER JOIN staffs ON goodsreceipts.staff_id = staffs.staff_id WHERE staffs.fullname LIKE "
					+ "'%" + query + "%' AND date BETWEEN " + "'" + bd + "'" + "AND" + "'" + kt + "'" + "LIMIT 100";
		}
		try {
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();
				pn.setReceipt_id(rs.getInt("receipt_id"));
				pn.setDate(rs.getString("date"));
				pn.setTotal_price(rs.getInt("total_price"));
				pn.setSupplier_id(rs.getInt("supplier_id"));
				pn.setStaff_id(rs.getInt("staff_id"));
				arr.add(pn);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return arr;
	}
}
