package BUS;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

public class SanPhamBUS {

	public static String getTenSanPhamById(int product_id) {
		return SanPhamDAO.getTenSanPhamById(product_id);
	}
	public static ArrayList<SanPhamDTO> getDanhSachSanPham(){
		return SanPhamDAO.getDanhSachSanPham();
	}
	public static SanPhamDTO getSanPhamByID(int product_id){
		return SanPhamDAO.getSanPhamByID(product_id);
	}
	public static boolean themSanPham(SanPhamDTO sanPhamDTO){
		return SanPhamDAO.themSanPham(sanPhamDTO);
	}
	public static boolean xoaSanPham(int product_id){
		return SanPhamDAO.xoaSanPham(product_id);
	}
	public static boolean suaSanPham(SanPhamDTO sanPhamDTO){
		return SanPhamDAO.suaSanPham(sanPhamDTO);
	}
	public static int getSoluongSanPham(){
		return SanPhamDAO.getSoluongSanPham();
	}
}
