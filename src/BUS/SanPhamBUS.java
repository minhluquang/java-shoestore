package BUS;

import DAO.SanPhamDAO;

public class SanPhamBUS {

	public static String getTenSanPhamById(int id) {
		return SanPhamDAO.getTenSanPhamById(id);
	}
}
