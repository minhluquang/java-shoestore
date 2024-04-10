package BUS;

import java.util.ArrayList;

import DAO.NhanVienDAO;
import DTO.NhanVien;

public class NhanVienBUS {
	private static ArrayList<NhanVien> dssv;
	
	public static ArrayList<NhanVien> getDanhSachNhanVien() {
		dssv = NhanVienDAO.getDanhSachNhanVien();
		return dssv;
	}
	
	public static int generateIdNhanVien() {
		int id = NhanVienDAO.generateIdNhanVien();
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
}
