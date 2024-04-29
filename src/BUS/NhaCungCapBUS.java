package BUS;

import java.util.ArrayList;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCap;

public class NhaCungCapBUS {

	private static ArrayList<NhaCungCap> dsncc;

	public static ArrayList<NhaCungCap> getDanhSachNhaCungCap() {
		dsncc = NhaCungCapDAO.getDanhSachNhaCungCap();
		return dsncc;
	}

	public static String getTenNhaCungCapById(int id) {
		return NhaCungCapDAO.getTenNhaCungCapById(id);
	}

	public static int generrate_Id() {
		return NhaCungCapDAO.generrate_Id();
	}

	public static boolean insertPublisher(String tenNcc, String diachi) {
		return NhaCungCapDAO.insertPublisher(tenNcc, diachi);
	}

	public static boolean updatePublisher(int id, String tenNcc, String diachi) {
		return NhaCungCapDAO.updatePublisher(id, tenNcc, diachi);
	}

	public static boolean deletePublisher(int id) {
		return NhaCungCapDAO.deletePublisher(id);
	}
}
