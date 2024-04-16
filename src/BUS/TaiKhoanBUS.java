package BUS;

import java.util.ArrayList;

import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;

public class TaiKhoanBUS {
	private static ArrayList<TaiKhoan> dstk;
	
	public static ArrayList<TaiKhoan> getDanhSachTaiKhoan() {
		dstk = TaiKhoanDAO.getDanhSachTaiKhoan();
		return dstk;
	}
	
	public static int generateIdTaiKhoan() {
		int id = TaiKhoanDAO.generateIdTaiKhoan();
		return id;
	}
	
	public static boolean isExistUsername(String username, int accountId) {
		boolean success = TaiKhoanDAO.isExistUsername(username, accountId);
		return success;
	}
	
	public static boolean isExistIdTaiKhoan(int accountId) {
		boolean success = TaiKhoanDAO.isExistIdTaiKhoan(accountId);
		return success;
	}
	
	public static boolean updateTaiKhoan(int accountId, String username, int accountStatus, String position) {
		boolean success = TaiKhoanDAO.updateTaiKhoan(accountId, username, accountStatus, position);
		return success;
	}
	
	public static boolean insertTaiKhoan(int accountId, String username, int accountStatus, String position) {
		boolean success = TaiKhoanDAO.insertTaiKhoan(accountId, username, accountStatus, position);
		return success;
	}
	
	public static ArrayList<TaiKhoan> searchTaiKhoan(String keyword, int searchStatus, String searchRole) {
		dstk = TaiKhoanDAO.searchTaiKhoan(keyword, searchStatus, searchRole);
		return dstk;
	}
	
	public static TaiKhoan getDetailTaiKhoanByUsername(String username) {
		return 	TaiKhoanDAO.getDetailTaiKhoanByUsername(username);
	}
}
