package DAO;

import java.lang.invoke.TypeDescriptor.OfField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import DTO.KhachHang;

public class KhachHangDAO {
	public static ArrayList<KhachHang> getDanhSachKhachHang() {
		connectDB.getConnection();
		ArrayList<KhachHang> dskh = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM customer";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setCustomerId(rs.getInt("customer_id"));
				kh.setCustomerName(rs.getString("customer_name"));
				kh.setPhoneNumber(rs.getString("phone_number"));
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dskh;
	}
	
	public static int generateIdKhachHang() {
		connectDB.getConnection();
		int idKhachHang = 0;
		
		try {
			String sql = "SELECT * "
					+ "FROM customer "
					+ "ORDER BY customer_id DESC "
					+ "LIMIT 1";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				int lastId = rs.getInt("customer_id");
				idKhachHang = lastId + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return idKhachHang;
	}
	
	public static boolean isExistKhachHang(int id) {
		connectDB.getConnection();
		boolean isExist = false;
		
		try {
			String sql = "SELECT * FROM customer WHERE customer_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				isExist = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isExist;
	}
	
	public static boolean updateKhachHang(int customerId, String customerName, String phoneNumber) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "UPDATE customer "
					+ "SET customer_name = ?, phone_number = ?"
					+ "WHERE customer_id = ?";
			PreparedStatement prest = connectDB.prepareStatement(sql);
		
			prest.setString(1, customerName);
			prest.setString(2, phoneNumber);
			prest.setInt(3, customerId);
			
			int i = prest.executeUpdate();
			
			if (i > 0) {
				success = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static boolean isExistPhoneNumber(String phoneNumber, int customerId) {
		connectDB.getConnection();
		boolean isExist = false;
		
		try {
			String sql = "SELECT * "
					+ "FROM customer "
					+ "WHERE phone_number = '" + phoneNumber + "'";
			ResultSet rs = connectDB.runQuery(sql);
			
			if (rs.next()) {
				if (rs.getInt("customer_id") != customerId) {
					isExist = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return isExist;
	}
	
	public static boolean insertKhachHang(int customerId, String customerName, String phoneNumber) {
		connectDB.getConnection();
		boolean success = false;
		
		try {
			String sql = "INSERT INTO customer (customer_id, customer_name, phone_number) "
					+ " VALUES (?, ?, ?)";
			
			PreparedStatement prest = connectDB.prepareStatement(sql);
			
			prest.setInt(1, customerId);
			prest.setString(2, customerName);
			prest.setString(3, phoneNumber);
			
			int i = prest.executeUpdate();

			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return success;
	}
	
	public static ArrayList<KhachHang> searchKhachHang(String keyword) {
		connectDB.getConnection();
		ArrayList<KhachHang> dskh = new ArrayList<>();

		try {
			String sql = "SELECT * FROM customer "
	                + "WHERE customer_id LIKE ? OR customer_name LIKE ? OR phone_number LIKE ?";
	        
	        PreparedStatement prest = connectDB.prepareStatement(sql);

	        String searchKeyword = "%" + keyword + "%";
	        prest.setString(1, searchKeyword);
	        prest.setString(2, searchKeyword);
	        prest.setString(3, searchKeyword);

	        ResultSet rs = prest.executeQuery();
	        
	        while (rs.next()) {
	        	System.out.println(rs.getString("customer_name"));
	        	KhachHang kh = new KhachHang();
	        	kh.setCustomerId(rs.getInt("customer_id"));
	        	kh.setCustomerName(rs.getString("customer_name"));
	        	kh.setPhoneNumber(rs.getString("phone_number"));
	        	dskh.add(kh);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dskh;
	}
}
