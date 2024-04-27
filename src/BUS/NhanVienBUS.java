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
	
	public static boolean updateNhanVien(int id, String fullname, String email, String phoneNumber) {
		boolean success = NhanVienDAO.updateNhanVien(id, fullname, email, phoneNumber);
		return success;
	}
	
	public static boolean insertNhanVien(String fullname, String email, String phoneNumber, int status) {
		boolean success = NhanVienDAO.insertNhanVien(fullname, email, phoneNumber, status);
		return success;
	}
	
	public static boolean insertDanhSachNhanVIen(ArrayList<NhanVien> dsnv) {
		boolean success = NhanVienDAO.insertDanhSachNhanVien(dsnv);
		return success;
	}
	
	public static ArrayList<NhanVien> searchNhanVien(String keyword) {
		dsnv = NhanVienDAO.searchNhanVien(keyword);
		return dsnv;
	}
	
	public static boolean isUsedAccountId(int accountId) {
		return NhanVienDAO.isUsedAccountId(accountId);
	}
	
	public static boolean deleteNhanVienById(int staffId) {
		return NhanVienDAO.deleteNhanVienById(staffId);
	}
}
