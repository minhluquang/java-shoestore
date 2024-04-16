package BUS;

import java.util.ArrayList;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhap;

public class ChiTietPhieuNhapBUS {

	private static ArrayList<ChiTietPhieuNhap> dsct;

	public static ArrayList<ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap(int id) {
		dsct = ChiTietPhieuNhapDAO.getDanhSachChiTietPhieuNhap(id);
		return dsct;
	}
}
