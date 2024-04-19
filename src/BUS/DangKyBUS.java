package BUS;

import DAO.DangKyDAO;
import DTO.TaiKhoan;

public class DangKyBUS {
	public static boolean register(TaiKhoan tk) {
		boolean result = DangKyDAO.register(tk);
		return result;
	}
}
