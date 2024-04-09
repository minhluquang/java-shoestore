package BUS;

import java.util.ArrayList;

import DAO.NhanVienDAO;
import DTO.NhanVien;

public class NhanVienBUS {
	private static ArrayList<NhanVien> dssv = null;
	
	public static ArrayList<NhanVien> getDanhSachNhanVien() {
		if (dssv == null) {
			dssv = NhanVienDAO.getDanhSachNhanVien();
		} 
		return dssv;
	}
}
