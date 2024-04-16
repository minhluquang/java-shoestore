package BUS;

import java.util.ArrayList;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhap;

public class PhieuNhapBUS {
	private static ArrayList<PhieuNhap> dspn;

	public static ArrayList<PhieuNhap> getDanhSachPhieuNhap() {
		dspn = PhieuNhapDAO.getDanhSachPhieuNhap();
		return dspn;
	}

	public static String getTenNhanVienById(int id) {
		return PhieuNhapDAO.getTenNhanVienById(id);
	}

	public static PhieuNhap getPhieuNhapById(int id) {
		PhieuNhap p = PhieuNhapDAO.getPhieuNhapById(id);
		return p;
	}
}
