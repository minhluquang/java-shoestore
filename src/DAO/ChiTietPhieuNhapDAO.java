package DAO;

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
}
