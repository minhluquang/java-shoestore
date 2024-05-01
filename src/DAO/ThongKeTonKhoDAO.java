package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ThongKeTonKhoDTO;

public class ThongKeTonKhoDAO {
//	List<ThongKeTonKhoDTO>
	public static List<ThongKeTonKhoDTO>  getAllSanPham() {
		List<ThongKeTonKhoDTO> list = new ArrayList<>();
		
		try {
			connectDB.getConnection();
			String sqlProduct = "select * from products";
			ResultSet rsProduct = connectDB.runQuery(sqlProduct);
			String sqlNhapHang = "select quantity from goodsreceipt_details where product_id = ?";
			PreparedStatement psNHapHang = connectDB.prepareStatement(sqlNhapHang);
			String sqlBanHang = "select count(sold)as SL_Ban from product_details where product_id = ? and sold =1 group by sold";
			PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
			
			int count = 0;
			while (rsProduct.next()) {
				count++;
				int maSP = rsProduct.getInt("product_id");
				String tenSP = rsProduct.getString("product_name");
				int TonKho = rsProduct.getInt("quantity");
				psNHapHang.setInt(1, maSP);
		
				ResultSet rsNhapHang = psNHapHang.executeQuery();
				psBanHang.setInt(1, maSP);
				ResultSet rsBanHang = psBanHang.executeQuery();
				int SL_Nhap = 0;
				if (rsNhapHang.next()) {
					SL_Nhap = rsNhapHang.getInt("quantity");
				}
				
				int SL_Ban =0;
				if (rsBanHang.next()) {
					SL_Ban = rsBanHang.getInt("SL_Ban");
				}				
				ThongKeTonKhoDTO dto = new ThongKeTonKhoDTO(count,maSP,tenSP,SL_Nhap,SL_Ban,TonKho);
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}
//	List<ThongKeTonKhoDTO>
	public static List<ThongKeTonKhoDTO> getSanPhamSearch(String search) {
		List<ThongKeTonKhoDTO> list = new ArrayList<>();
		
		try {
			connectDB.getConnection();
			String sqlProduct = "select * from products where product_name LIKE ?;";
			PreparedStatement psProduct = connectDB.prepareStatement(sqlProduct);
			psProduct.setString(1, "%" + search + "%");

			ResultSet rsProduct = psProduct.executeQuery();
			String sqlNhapHang = "select quantity from goodsreceipt_details where product_id = ?";
			PreparedStatement psNHapHang = connectDB.prepareStatement(sqlNhapHang);
			String sqlBanHang = "select count(sold)as SL_Ban from product_details where product_id = ? and sold =1 group by sold";
			PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
			
			int count = 0;
			while (rsProduct.next()) {
				count++;
				int maSP = rsProduct.getInt("product_id");
//				System.out.println(maSP);
				String tenSP = rsProduct.getString("product_name");
				int TonKho = rsProduct.getInt("quantity");
				psNHapHang.setInt(1, maSP);
		
				ResultSet rsNhapHang = psNHapHang.executeQuery();
				psBanHang.setInt(1, maSP);
				ResultSet rsBanHang = psBanHang.executeQuery();
				int SL_Nhap = 0;
				if (rsNhapHang.next()) {
					SL_Nhap = rsNhapHang.getInt("quantity");
				}
				
				int SL_Ban =0;
				if (rsBanHang.next()) {
					SL_Ban = rsBanHang.getInt("SL_Ban");
				}				
				ThongKeTonKhoDTO dto = new ThongKeTonKhoDTO(count,maSP,tenSP,SL_Nhap,SL_Ban,TonKho);
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}
	
	
	public static void main(String[] args) {
//		ThongKeTonKhoDAO.getAllSanPham();
		ThongKeTonKhoDAO.getSanPhamSearch("Air");
	}
}


