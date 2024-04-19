package BUS;

import java.util.ArrayList;

import DAO.NhanVienDAO;
import DTO.NhanVien;

public class NhanVienBUS {
	private static ArrayList<NhanVien> dsnv;
	
	public static ArrayList<NhanVien> getDanhSachNhanVien() {
		dsnv = NhanVienDAO.getDanhSachNhanVien();
		return dsnv;
	}
	
	public static int generateIdNhanVien(boolean closeDatabase) {
		int id = NhanVienDAO.generateIdNhanVien(closeDatabase);
		return id;
	}
	
	public static boolean isExistNhanVien(int id) {
		boolean isExist = NhanVienDAO.isExistNhanVien(id);
		return isExist;
	}
	
	public static boolean updateNhanVien(int id, String fullname, String email, String phoneNumber, int status, String accountId) {
		boolean success = NhanVienDAO.updateNhanVien(id, fullname, email, phoneNumber, status, accountId);
		return success;
	}
	
	public static boolean insertNhanVien(String fullname, String email, String phoneNumber, int status, String accountId) {
		boolean success = NhanVienDAO.insertNhanVien(fullname, email, phoneNumber, status, accountId);
		return success;
	}
	
	public static ArrayList<NhanVien> searchNhanVien(String keyword, int status) {
		dsnv = NhanVienDAO.searchNhanVien(keyword, status);
		return dsnv;
	}
	
	public static boolean isUsedAccountId(int accountId) {
		return NhanVienDAO.isUsedAccountId(accountId);
	}
}
