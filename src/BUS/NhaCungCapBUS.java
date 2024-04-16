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
}
