package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.System;

import DTO.ThongKeDoanhThuDTO;

public class ThongKeDoanhThuDAO {
	public static List<ThongKeDoanhThuDTO> getThongKeDoanhThu() {
		List<ThongKeDoanhThuDTO> list = new ArrayList<>();
		
		try {
			connectDB.getConnection();
			String sqlNgayBanHang = "SELECT distinct date FROM bills order by date desc ";
			String sqlNgayNhapHang = "SELECT distinct date FROM goodsreceipts order by date desc ";
			PreparedStatement psNgayBanHang = connectDB.prepareStatement(sqlNgayBanHang);
			PreparedStatement psNgayNhapHang = connectDB.prepareStatement(sqlNgayNhapHang);
			ResultSet rsNgayBanHang = psNgayBanHang.executeQuery();
			ResultSet rsNgayNhapHang = psNgayNhapHang.executeQuery();
			
			List<String>allDates = new ArrayList<String>();
			
			while(rsNgayBanHang.next()) {
				String date = rsNgayBanHang.getString("date");
				if (!allDates.contains(date)) {
					allDates.add(date);
				}
				
			}
			
			while(rsNgayNhapHang.next()) {
				String date = rsNgayNhapHang.getString("date");
				if (!allDates.contains(date)) {
					allDates.add(date);
				}
			}
			
			 Collections.sort(allDates);
			 Collections.reverse(allDates);
			System.out.println(allDates);
			
			String sqlBanHang = "SELECT date, COUNT(*) AS SL_HoaDon, SUM(total_price) AS doanhThu FROM bills WHERE date = ? GROUP BY date";
	        PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
	        String sqlNhapHang = "SELECT date, COUNT(*) AS SL_DonNhap, SUM(total_price) AS von FROM goodsreceipts WHERE date = ? GROUP BY date";
	        PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
	        // Thực hiện thống kê cho từng ngày trong danh sách tất cả các ngày
	        int count = 0;
	        for (String date : allDates) {
	        	if (count < 7) {
	        		psNhapHang.setString(1, date);
		            
		            ResultSet rsNhapHang = psNhapHang.executeQuery();
		            long von = 0;
		            int slDonNhap = 0;
		            if (rsNhapHang.next()) {
		                von = rsNhapHang.getLong("von");
		                slDonNhap = rsNhapHang.getInt("SL_DonNhap");
		            }
	    
		            psBanHang.setString(1, date);
		      
		            ResultSet rsBanHang = psBanHang.executeQuery();
		            long doanhThu = 0;
		            int slHoaDon = 0;
		            if (rsBanHang.next()) {
		                doanhThu = rsBanHang.getLong("doanhThu");
		                slHoaDon = rsBanHang.getInt("SL_HoaDon");
		            }

		            long loiNhuan = doanhThu - von;
		            
		            ThongKeDoanhThuDTO dto = new ThongKeDoanhThuDTO(date, von, doanhThu, loiNhuan, slHoaDon, slDonNhap);
					list.add(dto);
					count++;
				} else {
					break;
				}
	            
				
	        }	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		connectDB.closeConnection();
		return list;
	}
	
	public static List<ThongKeDoanhThuDTO> getThongKeTheoNgay(String month, String year) {
	    List<ThongKeDoanhThuDTO> list = new ArrayList<>();

	    try {
	        connectDB.getConnection();

	        // Lấy tất cả các ngày có bán hàng trong tháng
	        String sqlNgayBanHang = "SELECT date FROM bills WHERE MONTH(date) = ? AND YEAR(date) = ? GROUP BY date";
	        PreparedStatement psNgayBanHang = connectDB.prepareStatement(sqlNgayBanHang);
	        psNgayBanHang.setString(1, month);
	        psNgayBanHang.setString(2, year);
	        ResultSet rsNgayBanHang = psNgayBanHang.executeQuery();

	        // Lấy tất cả các ngày có nhập hàng trong tháng
	        String sqlNgayNhapHang = "SELECT date FROM goodsreceipts WHERE MONTH(date) = ? AND YEAR(date) = ? GROUP BY date";
	        PreparedStatement psNgayNhapHang = connectDB.prepareStatement(sqlNgayNhapHang);
	        psNgayNhapHang.setString(1, month);
	        psNgayNhapHang.setString(2, year);
	        ResultSet rsNgayNhapHang = psNgayNhapHang.executeQuery();

	        // Danh sách chứa tất cả các ngày trong tháng
	        List<String> allDates = new ArrayList<>();

	        // Thêm các ngày có bán hàng vào danh sách tất cả các ngày
	        while (rsNgayBanHang.next()) {
	            String date = rsNgayBanHang.getString("date");
	            allDates.add(date);
	        }

	        // Thêm các ngày có nhập hàng vào danh sách tất cả các ngày
	        while (rsNgayNhapHang.next()) {
	            String date = rsNgayNhapHang.getString("date");
	            if (!allDates.contains(date)) {
	                allDates.add(date);
	            }
	        }
	       
	        String sqlBanHang = "SELECT date, COUNT(*) AS SL_HoaDon, SUM(total_price) AS doanhThu FROM bills WHERE date = ? GROUP BY date";
	        PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
	        String sqlNhapHang = "SELECT date, COUNT(*) AS SL_DonNhap, SUM(total_price) AS von FROM goodsreceipts WHERE date = ? GROUP BY date";
	        PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
	        // Thực hiện thống kê cho từng ngày trong danh sách tất cả các ngày
	        
	        Collections.sort(allDates);
	        Collections.reverse(allDates);
	        for (String date : allDates) {
	            psNhapHang.setString(1, date);
	            
	            ResultSet rsNhapHang = psNhapHang.executeQuery();
	            long von = 0;
	            int slDonNhap = 0;
	            if (rsNhapHang.next()) {
	                von = rsNhapHang.getLong("von");
	                slDonNhap = rsNhapHang.getInt("SL_DonNhap");
	            }
    
	            psBanHang.setString(1, date);
	      
	            ResultSet rsBanHang = psBanHang.executeQuery();
	            long doanhThu = 0;
	            int slHoaDon = 0;
	            if (rsBanHang.next()) {
	                doanhThu = rsBanHang.getLong("doanhThu");
	                slHoaDon = rsBanHang.getInt("SL_HoaDon");
	            }

	            long loiNhuan = doanhThu - von;
	            
	            ThongKeDoanhThuDTO dto = new ThongKeDoanhThuDTO(date, von, doanhThu, loiNhuan, slHoaDon, slDonNhap);
				list.add(dto);
	            
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); // Xử lý exception theo nhu cầu của bạn
	    } finally {
	        connectDB.closeConnection();
	    }
	    return list;
	    
	}



	
	public static List<ThongKeDoanhThuDTO> getThongKeTheoThang( String year) {
	    List<ThongKeDoanhThuDTO> list = new ArrayList<>();

	    try {
	        connectDB.getConnection();
	        String sqlThangBanHang = "SELECT distinct month(date) FROM bills WHERE YEAR(date) = ?";
	        PreparedStatement psThangBanHang = connectDB.prepareStatement(sqlThangBanHang);
	        psThangBanHang.setString(1, year);  
	        ResultSet rsThangBanHang = psThangBanHang.executeQuery();
	        
	        String sqlThangNhapHang = "SELECT distinct month(date)FROM goodsreceipts WHERE YEAR(date) = ? ";
	        PreparedStatement psThangNhapHang = connectDB.prepareStatement(sqlThangNhapHang);
	        psThangNhapHang.setString(1, year);
	        ResultSet rsThangNhapHang = psThangNhapHang.executeQuery();
	        
	        ArrayList<Integer>allMonth = new ArrayList<Integer>(); 
	        
	        while (rsThangBanHang.next()) {
				String month = rsThangBanHang.getString("month(date)");
				if (!allMonth.contains(Integer.parseInt(month))) {
					allMonth.add(Integer.parseInt(month));
				}
			}
	        
	        while (rsThangNhapHang.next()) {
				String month = rsThangNhapHang.getString("month(date)");
				if (!allMonth.contains(Integer.parseInt(month))) {
					allMonth.add(Integer.parseInt(month));
				}
			}
	        
	        Collections.sort(allMonth);
	        
	        System.out.println(allMonth);
	        

	        String sqlBanHang = "SELECT month(date), COUNT(*) AS SL_HoaDon, SUM(total_price) AS doanhThu FROM bills WHERE month(date) = ? GROUP BY month(date)";
	        PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
	        String sqlNhapHang = "SELECT month(date), COUNT(*) AS SL_DonNhap, SUM(total_price) AS von FROM goodsreceipts WHERE month(date) = ? GROUP BY month(date)";
	        PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
	        
	        
	        for (Integer month : allMonth) {
				psBanHang.setString(1, String.valueOf(month));
				ResultSet rsBanHang = psBanHang.executeQuery();
				
				long doanhThu = 0;
				int slHoaDon = 0;
				if (rsBanHang.next()) {
					doanhThu = rsBanHang.getLong("doanhThu");
					slHoaDon = rsBanHang.getInt("SL_HoaDon");
				}
				
				psNhapHang.setString(1, String.valueOf(month));
				ResultSet rsNhapHang = psNhapHang.executeQuery();
				
				int slDonNhap = 0;
				long von = 0;
				if (rsNhapHang.next()) {
					von = rsNhapHang.getLong("von");
					slDonNhap = rsNhapHang.getInt("SL_DonNhap");
				}
				
				long loiNhuan = doanhThu - von;
				ThongKeDoanhThuDTO dto = new ThongKeDoanhThuDTO(String.valueOf(month), von, doanhThu, loiNhuan, slHoaDon, slDonNhap);
				list.add(dto);
			}
	       
	    } catch (Exception e) {
	        e.printStackTrace(); // Xử lý exception theo nhu cầu của bạn
	    } finally {
	        connectDB.closeConnection();
	    }
	    return list;
	}

	
	public static int getTotalStaff() {
		int total = 0;
		try {
			connectDB.getConnection();
			String sql = "select sum(staff_id) AS total_satff from staffs where status = 1";
			ResultSet rs = connectDB.runQuery(sql);
				while(rs.next()) {
					total = rs.getInt("total_satff");			
				}
		} catch (Exception e) {
		}
		connectDB.closeConnection();
		return total;
		
	}
	
	public static int getTotalCustomer() {
		int total = 0;
		try {
			connectDB.getConnection();
			String sql = "select sum(customer_id) AS total_cus from customers where status = 1";
			ResultSet rs = connectDB.runQuery(sql);
				while(rs.next()) {
					total = rs.getInt("total_cus");			
				}
		} catch (Exception e) {
		}
		connectDB.closeConnection();
		return total;
		
	}
	
	public static int getTotalProduct() {
		int total = 0;
		try {
			connectDB.getConnection();
			String sql = "select sum(quantity) AS total_product from products where status = 1";
			ResultSet rs = connectDB.runQuery(sql);
				while(rs.next()) {
					total = rs.getInt("total_product");			
				}
		} catch (Exception e) {
		}
		connectDB.closeConnection();
		return total;
		
	}

	public static List<ThongKeDoanhThuDTO> thongKeTheoNam() {
		List<ThongKeDoanhThuDTO> list = new ArrayList<>();
		try {
	        connectDB.getConnection();
	        String sqlNamBanHang = "SELECT distinct year(date) FROM bills ";
	        PreparedStatement psNamBanHang = connectDB.prepareStatement(sqlNamBanHang);
	        ResultSet rsNamBanHang = psNamBanHang.executeQuery();
//	        ResultSet rsNamBanHang = connectDB.runQuery(sqlNamBanHang);
	     
	        String sqlNamNhapHang = "SELECT distinct year(date)FROM goodsreceipts ";
	        ResultSet rsNamNhapHang = connectDB.runQuery(sqlNamNhapHang);
	        
	        ArrayList<Integer>allYear = new ArrayList<Integer>(); 
	        
	        while (rsNamBanHang.next()) {
				String year = rsNamBanHang.getString("year(date)");
				if (!allYear.contains(Integer.parseInt(year))) {
					allYear.add(Integer.parseInt(year));
				}
			}
	        
	        while (rsNamNhapHang.next()) {
				String year = rsNamNhapHang.getString("year(date)");
				if (!allYear.contains(Integer.parseInt(year))) {
					allYear.add(Integer.parseInt(year));
				}
			}
	        
	        Collections.sort(allYear);
	        Collections.reverse(allYear);
	        
	        System.out.println(allYear);
	        

	        String sqlBanHang = "SELECT year(date), COUNT(*) AS SL_HoaDon, SUM(total_price) AS doanhThu FROM bills WHERE year(date) = ? GROUP BY year(date)";
	        PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
	        String sqlNhapHang = "SELECT year(date), COUNT(*) AS SL_DonNhap, SUM(total_price) AS von FROM goodsreceipts WHERE year(date) = ? GROUP BY year(date)";
	        PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
	        
	        
	        for (Integer year : allYear) {
				psBanHang.setString(1, String.valueOf(year));
				ResultSet rsBanHang = psBanHang.executeQuery();
				
				long doanhThu = 0;
				int slHoaDon = 0;
				if (rsBanHang.next()) {
					doanhThu = rsBanHang.getLong("doanhThu");
					slHoaDon = rsBanHang.getInt("SL_HoaDon");
				}
				
				psNhapHang.setString(1, String.valueOf(year));
				ResultSet rsNhapHang = psNhapHang.executeQuery();
				
				int slDonNhap = 0;
				long von = 0;
				if (rsNhapHang.next()) {
					von = rsNhapHang.getLong("von");
					slDonNhap = rsNhapHang.getInt("SL_DonNhap");
				}
				
				long loiNhuan = doanhThu - von;
				ThongKeDoanhThuDTO dto = new ThongKeDoanhThuDTO(String.valueOf(year), von, doanhThu, loiNhuan, slHoaDon, slDonNhap);
				list.add(dto);
			}
	       
	    } catch (Exception e) {
	        e.printStackTrace(); // Xử lý exception theo nhu cầu của bạn
	    } finally {
	        connectDB.closeConnection();
	    }
		return list;
	}
//	List<ThongKeDoanhThuDTO>
	public static List<ThongKeDoanhThuDTO>  ThongKeTheoKhoangNgay(String dateStart, String dateFinish) {
	    List<ThongKeDoanhThuDTO> list = new ArrayList<>();

	    try {
	        connectDB.getConnection();

	        // Lấy tất cả các ngày có bán hàng trong tháng
	        String sqlNgayBanHang = "SELECT distinct date FROM bills WHERE  date  BETWEEN ?  AND ?";
	        PreparedStatement psNgayBanHang = connectDB.prepareStatement(sqlNgayBanHang);
	        psNgayBanHang.setString(1,dateStart);
	        psNgayBanHang.setString(2, dateFinish);
	        ResultSet rsNgayBanHang = psNgayBanHang.executeQuery();

	        // Lấy tất cả các ngày có nhập hàng trong tháng
	        String sqlNgayNhapHang = "SELECT distinct date FROM goodsreceipts WHERE  date  BETWEEN ?  AND ?";
	        PreparedStatement psNgayNhapHang = connectDB.prepareStatement(sqlNgayNhapHang);
	        psNgayNhapHang.setString(1,dateStart);
	        psNgayNhapHang.setString(2, dateFinish);
	        ResultSet rsNgayNhapHang = psNgayNhapHang.executeQuery();

	        
	        List<String> allDates = new ArrayList<>();

	        
	        while (rsNgayBanHang.next()) {
	            String date = rsNgayBanHang.getString("date");
	            System.out.println(date);
	            if (!allDates.contains(date)) {
	            	allDates.add(date);
				}
	            
	        }

	        // Thêm các ngày có nhập hàng vào danh sách tất cả các ngày
	        while (rsNgayNhapHang.next()) {
	            String date = rsNgayNhapHang.getString("date");
	            if (!allDates.contains(date)) {
	                allDates.add(date);
	            }
	        }
	       
	        String sqlBanHang = "SELECT date, COUNT(*) AS SL_HoaDon, SUM(total_price) AS doanhThu FROM bills WHERE date = ? GROUP BY date";
	        PreparedStatement psBanHang = connectDB.prepareStatement(sqlBanHang);
	        String sqlNhapHang = "SELECT date, COUNT(*) AS SL_DonNhap, SUM(total_price) AS von FROM goodsreceipts WHERE date = ? GROUP BY date";
	        PreparedStatement psNhapHang = connectDB.prepareStatement(sqlNhapHang);
	        // Thực hiện thống kê cho từng ngày trong danh sách tất cả các ngày
	        
	        Collections.sort(allDates);
	        Collections.reverse(allDates);
	        System.out.println(allDates);
	        for (String date : allDates) {
	            psNhapHang.setString(1, date);
	            
	            ResultSet rsNhapHang = psNhapHang.executeQuery();
	            long von = 0;
	            int slDonNhap = 0;
	            if (rsNhapHang.next()) {
	                von = rsNhapHang.getLong("von");
//	                System.out.println(von);
	                slDonNhap = rsNhapHang.getInt("SL_DonNhap");
	            }
    
	            psBanHang.setString(1, date);
	      
	            ResultSet rsBanHang = psBanHang.executeQuery();
	            long doanhThu = 0;
	            int slHoaDon = 0;
	            if (rsBanHang.next()) {
	                doanhThu = rsBanHang.getLong("doanhThu");
	                slHoaDon = rsBanHang.getInt("SL_HoaDon");
//	                System.out.println(doanhThu);
	            }

	            long loiNhuan = doanhThu - von;
	            
	            ThongKeDoanhThuDTO dto = new ThongKeDoanhThuDTO(date, von, doanhThu, loiNhuan, slHoaDon, slDonNhap);
				list.add(dto);
//				System.out.println(list.toString());
	            
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); // Xử lý exception theo nhu cầu của bạn
	    } finally {
	        connectDB.closeConnection();
	    }
	    
	  return list; 
	}
	
	public static void main(String[] args) {
//		ThongKeDoanhThuDAO.getThongKeDoanhThu();
//		ThongKeDoanhThuDAO.getTotalStaff();
		ThongKeTheoKhoangNgay("2024-04-01", "2024-04-07");
		
	}
}
