package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ChiTietPhieuNhap;

public class ChiTietPhieuNhapDAO {

	public static ArrayList<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap(int id) {
		ArrayList<ChiTietPhieuNhap> dsct = new ArrayList<>();
		try {
			String sql = "Select * from goodsreceipt_detail where receipt_id=" + id;
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				ChiTietPhieuNhap p = new ChiTietPhieuNhap();
				p.setReceiptdetail_id(rs.getInt("receiptdetail_id"));
				p.setProduct_id(rs.getInt("product_id"));
				p.setQuantity(rs.getInt("quantity"));
				p.setReceipt_id(rs.getInt("receipt_id"));
				p.setInput_price(rs.getInt("intput_price"));
				dsct.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsct;
	}
}
