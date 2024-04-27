package BUS;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

public class SanPhamBUS {

	public static String getTenSanPhamById(int id) {
		return SanPhamDAO.getTenSanPhamById(id);
	}
	public static ArrayList<SanPhamDTO> getDanhSachSanPham(){
		return SanPhamDAO.getDanhSachSanPham();
	}
	public static SanPhamDTO getSanPhamByID(int product_id){
		return SanPhamDAO.getSanPhamByID(product_id);
	}
}
