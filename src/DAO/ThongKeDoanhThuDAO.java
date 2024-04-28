package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.lang.System;

import DTO.ThongKeDoanhThuDTO;

public class ThongKeDoanhThuDAO {
	public static List<ThongKeDoanhThuDTO> getThongKeDoanhThu() {
		List<ThongKeDoanhThuDTO> list = new ArrayList<>();
		
		try {
			connectDB.getConnection();
			String sqlBanHang = "SELECT date,COUNT(*)AS SL_HoaDon, SUM(total_price) AS total_price FROM bills   group by date ORDER BY date DESC LIMIT 7";
			
			PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
			ResultSet rsBanHang = psBanHang.executeQuery();
			
			// Lấy tổng tiền nhập hàng cho từng ngày 
			
			String sqlNhapHang = "SELECT date,COUNT(*) AS SL_DonNhap, SUM(total_price) AS total_price FROM goodsreceipts WHERE date = ? GROUP BY date";
			PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
			while (rsBanHang.next()) {				
				String thoigian = rsBanHang.getString("date");
				int slHoaDon = rsBanHang.getInt("SL_HoaDon");
				psNhapHang.setString(1, thoigian);
				ResultSet rsNhapHang = psNhapHang.executeQuery();
				long von=0;
				int slDonNhap = 0;
				if(rsNhapHang.next()) {
					von = rsNhapHang.getLong("total_price");
					slDonNhap = rsNhapHang.getInt("SL_DonNhap");
				}		
							
				long doanhThu = rsBanHang.getLong("total_price");
				
				long loiNhuan = doanhThu - von;
				
				ThongKeDoanhThuDTO dto = new ThongKeDoanhThuDTO(thoigian, von, doanhThu, loiNhuan, slHoaDon, slDonNhap);
				list.add(dto);
				
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
//	public static void getThongKeDoanhThu() {
//		
//		
//		try {
//			
//			connectDB.getConnection();
//			
//			String sqlBanHang = "SELECT date,COUNT(*)AS SL_HoaDon, SUM(total_price) AS total_price FROM bills   group by date ORDER BY date DESC LIMIT 7";
//					
//			ResultSet rsBanHang = connectDB.runQuery(sqlBanHang);
//			System.out.println(rsBanHang);
//			// Lấy tổng tiền nhập hàng cho từng ngày 
//			
//			String sqlNhapHang = "SELECT date,COUNT(*) AS SL_DonNhap, SUM(total_price) AS total_price FROM goodsreceipts WHERE date = ? GROUP BY date";
//			PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
//			while (rsBanHang.next()) {				
//				String thoigian = rsBanHang.getString("date");
//				System.out.println(thoigian);
//				int slHoaDon = rsBanHang.getInt("SL_HoaDon");
//				System.out.println(slHoaDon);
//				psNhapHang.setString(1, thoigian);
//				ResultSet rsNhapHang = psNhapHang.executeQuery();
//				long von=0;
//				int slDonNhap = 0;
//				if(rsNhapHang.next()) {
//					von = rsNhapHang.getLong("total_price");
//					slDonNhap = rsNhapHang.getInt("SL_DonNhap");
//				}	
//				System.out.println(von);
//				System.out.println(slDonNhap);
//							
//				long doanhThu = rsBanHang.getLong("total_price");
//				System.out.println(doanhThu);
//				long loiNhuan = doanhThu - von;
//				System.out.println(loiNhuan);
//				
//				
//			
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	public static void main(String[] args) {
		ThongKeDoanhThuDAO.getThongKeDoanhThu();
	}
}
