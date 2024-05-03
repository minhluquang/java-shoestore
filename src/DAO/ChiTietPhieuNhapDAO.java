package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ChiTietPhieuNhap;

public class ChiTietPhieuNhapDAO {

	public static ArrayList<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap(int id) {
		connectDB.getConnection();
		ArrayList<ChiTietPhieuNhap> dsct = new ArrayList<>();
		try {
			String sql = "Select * from goodsreceipt_details where receipt_id=" + id;
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				ChiTietPhieuNhap p = new ChiTietPhieuNhap();
				p.setProduct_id(rs.getInt("product_id"));
				p.setQuantity(rs.getInt("quantity"));
				p.setReceipt_id(rs.getInt("receipt_id"));
				p.setInput_price(rs.getInt("input_price"));
				dsct.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return dsct;
	}

	public static boolean create_GoodsReceipts_Details(int product_id, int quantity, int receipt_id, int input_price) {
		connectDB.getConnection();
		boolean success = false;
		String sql = "INSERT INTO `goodsreceipt_details`(`product_id`, `quantity`, `receipt_id`, `input_price`) VALUES"
				+ " (?,?,?,?)";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, product_id);
			mystm.setInt(2, quantity);
			mystm.setInt(3, receipt_id);
			mystm.setInt(4, input_price);

			int i = mystm.executeUpdate();
			if (i > 0) {
				success = true;

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		update_Quantity(product_id, quantity);
		connectDB.closeConnection();
		return success;
	}

	public static boolean update_Quantity(int product_id, int quantity) {
		boolean success = false;
		connectDB.getConnection();
		String sql = "UPDATE `products` SET `quantity`= `quantity` + ? WHERE product_id= ?";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, quantity);
			mystm.setInt(2, product_id);

			int i = mystm.executeUpdate();
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}

	public static boolean insertProductDetail(int product_id) {
		connectDB.getConnection();
		boolean success = false;
		int sold = 0;
		String sql = "INSERT INTO `product_details`(`product_id`, `sold`) VALUES (?,?)";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, product_id);
			mystm.setInt(2, sold);

			int i = mystm.executeUpdate();
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}
}
