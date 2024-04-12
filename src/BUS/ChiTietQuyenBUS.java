package BUS;

import java.util.ArrayList;

import DAO.ChiTietQuyenDAO;

public class ChiTietQuyenBUS {
	private static ArrayList<Integer> dsqtk;
	
	public static ArrayList<Integer> getDanhSachQuyenCuaTaiKhoanBangId(int id) {
		dsqtk = ChiTietQuyenDAO.getDanhSachQuyenCuaTaiKhoanBangId(id);
		return dsqtk;
	}
	
	public static boolean deleteTatCaQuyenCuaTaiKhoanBangId(int id) {
		boolean success = ChiTietQuyenDAO.deleteTatCaQuyenCuaTaiKhoanBangId(id);
		return success;
	}
	
	public static boolean insertQuyenVaoTaiKhoan(int roleId, int accountId) {
		boolean success = ChiTietQuyenDAO.insertQuyenVaoTaiKhoan(roleId, accountId);
		return success;
	}
}
