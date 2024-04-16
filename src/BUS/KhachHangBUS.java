package BUS;

import java.util.ArrayList;

import DAO.KhachHangDAO;
import DTO.KhachHang;

public class KhachHangBUS {
	private static ArrayList<KhachHang> dskh;
	
	public static ArrayList<KhachHang> getDanhSachKhachHang() {
		dskh = KhachHangDAO.getDanhSachKhachHang();
		return dskh;
	}
	
	public static ArrayList<KhachHang> searchKhachHang(String keyword) {
		dskh = KhachHangDAO.searchKhachHang(keyword);
		return dskh;
	}
	
	public static int generateIdKhachHang() {
		return KhachHangDAO.generateIdKhachHang();
	}
	
	public static boolean updateKhachHang(int customerId, String customerName, String phoneNumber) {
		return KhachHangDAO.updateKhachHang(customerId, customerName, phoneNumber);
	}
	
	public static boolean insertKhachHang(int customerId, String customerName, String phoneNumber) {
		return KhachHangDAO.insertKhachHang(customerId, customerName, phoneNumber);
	}
	
	public static boolean isExistPhoneNumber(String phoneNumber, int customerId) {
		return KhachHangDAO.isExistPhoneNumber(phoneNumber, customerId);
	}
	
	public static boolean isExistKhachHang(int id) {
		return KhachHangDAO.isExistKhachHang(id);
	}
	
}
