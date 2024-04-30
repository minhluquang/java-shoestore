package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hslf.record.Sound;

import DTO.ThongKeNhaCungCapDTO;
import DTO.ThongKeTonKhoDTO;
import GUI.main;

public class ThongKeNhaCungCapDAO {
//	List<ThongKeNhaCungCapDTO>
	public static List<ThongKeNhaCungCapDTO> getAllNCC() {
		List<ThongKeNhaCungCapDTO> list = new ArrayList<>();
		
		try {
			connectDB.getConnection();
			String sqlNCC = "select * from suppliers";
			ResultSet rsNCC = connectDB.runQuery(sqlNCC);
			
			String sqlNhapHang = "select count(supplier_id) as SL_Nhap, sum(total_price) as TongTien from goodsreceipts where supplier_id = ? group by supplier_id";
			PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
			
			int count = 0;
			while (rsNCC.next()) {
				count++;
				int idNCC = rsNCC.getInt("supplier_id");
				String nameNCC = rsNCC.getString("supplier_name");
				
				psNhapHang.setInt(1, idNCC);
				ResultSet rsNhapHang = psNhapHang.executeQuery();
				
				int SL_Nhap = 0;
				long tongTien = 0;
				if (rsNhapHang.next()) {
					 SL_Nhap = rsNhapHang.getInt("SL_Nhap");
					 tongTien = rsNhapHang.getLong("TongTien");
				}
				
				ThongKeNhaCungCapDTO dto = new ThongKeNhaCungCapDTO(count,idNCC,nameNCC,SL_Nhap,tongTien);
				list.add(dto);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public static List<ThongKeNhaCungCapDTO> getNCCSearch(String search) {
		List<ThongKeNhaCungCapDTO> list = new ArrayList<>();
		
		try {
			connectDB.getConnection();
			String sqlNCC = "select * from suppliers where supplier_name LIKE ?;";
			PreparedStatement psNCC = connectDB.prepareStatement(sqlNCC);
			psNCC.setString(1, "%" + search + "%");

			ResultSet rsNCC = psNCC.executeQuery();
			String sqlNhapHang = "select count(supplier_id) as SL_Nhap, sum(total_price) as TongTien from goodsreceipts where supplier_id = ? group by supplier_id";
			PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
			
			int count = 0;
			while (rsNCC.next()) {
				count++;
				int idNCC = rsNCC.getInt("supplier_id");
				String nameNCC = rsNCC.getString("supplier_name");
				
				psNhapHang.setInt(1, idNCC);
				ResultSet rsNhapHang = psNhapHang.executeQuery();
				
				int SL_Nhap = 0;
				long tongTien = 0;
				if (rsNhapHang.next()) {
					 SL_Nhap = rsNhapHang.getInt("SL_Nhap");
					 tongTien = rsNhapHang.getLong("TongTien");
				}
				
				ThongKeNhaCungCapDTO dto = new ThongKeNhaCungCapDTO(count,idNCC,nameNCC,SL_Nhap,tongTien);
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}
	
	public static void main(String[] args) {
		ThongKeNhaCungCapDAO.getAllNCC();
	}
}
