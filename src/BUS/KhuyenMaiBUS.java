package BUS;

import java.util.ArrayList;
import DAO.KhuyenmaiDAO;
import DAO.RoleDAO;
import DTO.KhuyenMai;


public class KhuyenMaiBUS {
	// load ds
	private static ArrayList<KhuyenMai> dskm;
	public static ArrayList<KhuyenMai> getDanhSachKhuyenMai() {		
		dskm = KhuyenmaiDAO.getDanhSachKhuyenMai();			
	return dskm;
	}
	// search
	public static  ArrayList<KhuyenMai> searchKhuyenMai(String keyword,int status) {
		dskm = KhuyenmaiDAO.searchKhuyenMai(keyword,status);
	    return dskm;
	}
	//  check
	public static boolean isExistKM(String discount_code) {
		boolean isExist = KhuyenmaiDAO.isExistKM(discount_code);
	    return isExist;
	}
	// insert
	public static boolean insertKhuyenMai(String discount_code, int condition_value, String discount, String start_date, String end_date, String active) {
	    boolean success = KhuyenmaiDAO.insertKhuyenMai(discount_code, condition_value, discount, start_date, end_date, active);
	    return success;
	}
	// update
	public static boolean updateKhuyenMai(String discount_code, int condition_value, String discount, String start_date, String end_date, String active) {
	    boolean success = KhuyenmaiDAO.updateKhuyenMai(discount_code, condition_value, discount, start_date, end_date, active);
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
