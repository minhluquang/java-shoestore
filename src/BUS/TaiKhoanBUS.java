package BUS;

import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
	
	public static boolean insertTaiKhoan(String username, String password, int accountStatus, String position) {
		boolean success = TaiKhoanDAO.insertTaiKhoan(username, password, accountStatus, position);
		return success;
	}
	
	public static ArrayList<TaiKhoan> searchTaiKhoan(String keyword, int searchStatus, String searchRole) {
		dstk = TaiKhoanDAO.searchTaiKhoan(keyword, searchStatus, searchRole);
		return dstk;
	}
	
	public static TaiKhoan getDetailTaiKhoanByUsername(String username, boolean closeDatabase) {
		return 	TaiKhoanDAO.getDetailTaiKhoanByUsername(username, closeDatabase);
	}
	
	public static boolean deleteTaiKhoanById(int accountId) {
		return TaiKhoanDAO.deleteTaiKhoanById(accountId);
	}
	
	public static boolean unlockTaiKhoanByAccountId(int accountId) {
		return TaiKhoanDAO.unlockTaiKhoanByAccountId(accountId);
	}
	
	public static boolean isUsedUsername(String username) {
		return TaiKhoanDAO.isUsedUsername(username);
	}
	
	public static boolean insertDanhSachTaiKhoan(ArrayList<TaiKhoan> dstk) {
		return TaiKhoanDAO.insertDanhSachTaiKhoan(dstk);
	}
	
	public static TaiKhoan getDetailTaiKhoanByAccountId(int accountId) {
		return TaiKhoanDAO.getDetailTaiKhoanByAccountId(accountId);
	}
	
	public static boolean isTruePassword(int accountId, String password) {
		return TaiKhoanDAO.isTruePassword(accountId, password);
	}
	
	public static boolean updatePasswordByAccountId(int accountId, String password) {
		return TaiKhoanDAO.updatePasswordByAccountId(accountId, password);
	}
}
