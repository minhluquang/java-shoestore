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
	
	public static ArrayList<KhachHang> searchKhachHang(String keyword, int searchStatus) {
		dskh = KhachHangDAO.searchKhachHang(keyword, searchStatus);
		return dskh;
	}
	
	public static int generateIdKhachHang() {
		return KhachHangDAO.generateIdKhachHang();
	}
	
	public static boolean updateKhachHang(int customerId, String customerName, String phoneNumber, int status) {
		return KhachHangDAO.updateKhachHang(customerId, customerName, phoneNumber, status);
	}
	
	public static boolean insertKhachHang(int customerId, String customerName, String phoneNumber, int status) {
		return KhachHangDAO.insertKhachHang(customerId, customerName, phoneNumber, status);
	}
	
	public static boolean isExistPhoneNumber(String phoneNumber, int customerId) {
		return KhachHangDAO.isExistPhoneNumber(phoneNumber, customerId);
	}
	
	public static boolean isExistKhachHang(int id) {
		return KhachHangDAO.isExistKhachHang(id);
	}
	
	public static boolean deleteKhachHangById(int customerId) {
		return KhachHangDAO.deleteKhachHangById(customerId);
	}
	
	public static boolean insertDanhSachKhachHang(ArrayList<KhachHang> dskh) {
		return KhachHangDAO.insertDanhSachKhachHang(dskh);
	}

	public static KhachHang getKhachHangByID(int id) {
		return KhachHangDAO.getKhachHangByID(id);
	}
	public static KhachHang getKhachHangByPhoneNumber(String phoneNumber){
		return KhachHangDAO.getKhachHangByPhoneNumber(phoneNumber);
	}
}
