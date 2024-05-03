package BUS;

import java.util.ArrayList;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhap;

public class ChiTietPhieuNhapBUS {

	private static ArrayList<ChiTietPhieuNhap> dsct;

	public static ArrayList<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap(int id) {
		dsct = ChiTietPhieuNhapDAO.getDanhSachChiTietPhieuNhap(id);
		return dsct;
	}

	public static boolean create_GoodsReceipts_Details(int product_id, int quantity, int receipt_id, int input_price) {
		return ChiTietPhieuNhapDAO.create_GoodsReceipts_Details(product_id, quantity, receipt_id, input_price);
	}

	public static boolean insertProductDetail(int product_id) {
		return ChiTietPhieuNhapDAO.insertProductDetail(product_id);
	}
}
