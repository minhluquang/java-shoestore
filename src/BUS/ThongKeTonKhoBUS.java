package BUS;

import java.util.List;

import DAO.ThongKeTonKhoDAO;
import DTO.ThongKeTonKhoDTO;

public class ThongKeTonKhoBUS {

	public static List<ThongKeTonKhoDTO>  getAllSanPham() {
		return ThongKeTonKhoDAO.getAllSanPham();
	}
	public static List<ThongKeTonKhoDTO> getSanPhamSearch(String search) {
		return ThongKeTonKhoDAO.getSanPhamSearch(search);
	}
}
