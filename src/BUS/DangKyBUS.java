package BUS;

import DAO.DangKyDAO;
import DTO.NhanVien;
import DTO.TaiKhoan;

public class DangKyBUS {
	public static boolean register(TaiKhoan tk) {
		boolean result = DangKyDAO.register(tk);
		return result;
	}
	
	public static boolean registerDetail(NhanVien nv) {
		boolean result = DangKyDAO.registerDetail(nv);
		return result;
	}
}
