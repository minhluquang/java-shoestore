package BUS;

import java.util.ArrayList;

import DAO.KhuyenmaiDAO;
import DTO.KhuyenMai;

public class KhuyenMaiBUS {
	// load ds
	private static ArrayList<KhuyenMai> dskm;

	public static ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
		dskm = KhuyenmaiDAO.getDanhSachKhuyenMai();
		return dskm;
	}

	// search
	public static ArrayList<KhuyenMai> searchKhuyenMai(String keyword, int status) {
		dskm = KhuyenmaiDAO.searchKhuyenMai(keyword, status);
		return dskm;
	}

	// check
	public static boolean isExistKM(String discount_code) {
		boolean isExist = KhuyenmaiDAO.isExistKM(discount_code);
		return isExist;
	}

	// insert
	public static boolean insertKhuyenMai(String discount_code, int discount_value, String type, String start_date,
			String end_date, int status) {
		boolean success = KhuyenmaiDAO.insertKhuyenMai(discount_code, discount_value, type, start_date, end_date,
				status);
		return success;
	}

	public static boolean insertKhuyenMai(ArrayList<KhuyenMai> dskm) {
		boolean success = KhuyenmaiDAO.insertKhuyenMai(dskm);
		return success;
	}

	// update
	public static boolean updateKhuyenMai(String discount_code, int discount_value, String type, String start_date,
			String end_date, int status) {
		boolean success = KhuyenmaiDAO.updateKhuyenMai(discount_code, discount_value, type, start_date, end_date,
				status);
		return success;
	}

	// delete
	public static boolean deleteKhuyenMai(String discount_code) {
		boolean success = KhuyenmaiDAO.deleteKM(discount_code);
		return success;
	}

	// edit
	public static KhuyenMai getKhuyenMaiByDiscountCode(String discountCode) {
		return KhuyenmaiDAO.getKhuyenMaiByDiscountCode(discountCode);
	}
}
