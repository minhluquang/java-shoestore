package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.KhachHang;

public class KhachHangDAO {
	public static ArrayList<KhachHang> getDanhSachKhachHang() {
		connectDB.getConnection();
		ArrayList<KhachHang> dskh = new ArrayList<>();

		try {
			String sql = "SELECT * FROM customers";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setCustomerId(rs.getInt("customer_id"));
				kh.setCustomerName(rs.getString("customer_name"));
				kh.setPhoneNumber(rs.getString("phone_number"));
				kh.setStatus(rs.getInt("status"));
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
			String sql = "SELECT * " + "FROM customers " + "ORDER BY customer_id DESC " + "LIMIT 1";
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
			String sql = "SELECT * FROM customers WHERE customer_id = " + id;
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

	public static boolean updateKhachHang(int customerId, String customerName, String phoneNumber, int status) {
		connectDB.getConnection();
		boolean success = false;

		try {
			String sql = "UPDATE customers " + "SET customer_name = ?, phone_number = ?, status = ? " + "WHERE customer_id = ?";
			PreparedStatement prest = connectDB.prepareStatement(sql);

			prest.setString(1, customerName);
			prest.setString(2, phoneNumber);
			prest.setInt(3, status);
			prest.setInt(4, customerId);
			
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
			String sql = "SELECT * " + "FROM customers " + "WHERE phone_number = '" + phoneNumber + "'";
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

	public static boolean insertKhachHang(int customerId, String customerName, String phoneNumber, int status) {
		connectDB.getConnection();
		boolean success = false;

		try {
			String sql;
			if (customerId == 0) {
				sql = "INSERT INTO customers (customer_name, phone_number, status) " + " VALUES (?, ?, ?)";
			} else {
				sql = "INSERT INTO customers (customer_id, customer_name, phone_number, status) "
						+ " VALUES (?, ?, ?, ?)";
			}

			PreparedStatement prest = connectDB.prepareStatement(sql);

			if (customerId == 0) {
				prest.setString(1, customerName);
				prest.setString(2, phoneNumber);
				prest.setInt(3, status);
			} else {
				prest.setInt(1, customerId);
				prest.setString(2, customerName);
				prest.setString(3, phoneNumber);
				prest.setInt(4, status);
			}

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

	public static ArrayList<KhachHang> searchKhachHang(String keyword, int searchStatus) {
		connectDB.getConnection();
		ArrayList<KhachHang> dskh = new ArrayList<>();

		try {
			String sql = "SELECT * FROM customers "
					+ "WHERE (customer_id LIKE ? OR customer_name LIKE ? OR phone_number LIKE ?)";
			
			if (searchStatus != -1) {
				sql += " AND status = " + searchStatus;
			}
			
			PreparedStatement prest = connectDB.prepareStatement(sql);

			String searchKeyword = "%" + keyword + "%";
			prest.setString(1, searchKeyword);
			prest.setString(2, searchKeyword);
			prest.setString(3, searchKeyword);

			ResultSet rs = prest.executeQuery();

			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setCustomerId(rs.getInt("customer_id"));
				kh.setCustomerName(rs.getString("customer_name"));
				kh.setPhoneNumber(rs.getString("phone_number"));
				kh.setStatus(rs.getInt("status"));
				dskh.add(kh);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		connectDB.closeConnection();
		return dskh;
	}

	public static boolean deleteKhachHangById(int customerId) {
		connectDB.getConnection();
		boolean success = false;

		try {
			String sql = "UPDATE customers " + "SET status = 0 " + "WHERE customer_id = " + customerId;
			int i = connectDB.runUpdate(sql);
			if (i > 0) {
				success = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		connectDB.closeConnection();
		return success;
	}

	public static boolean insertDanhSachKhachHang(ArrayList<KhachHang> dskh) {
		boolean success = true;
		for (KhachHang kh : dskh) {
			String fullname = kh.getCustomerName();
			String phoneNumber = kh.getPhoneNumber();
			int status = 1;

			success = insertKhachHang(0, fullname, phoneNumber, status);
			if (!success) {
				return success;
			}
		}
		return success;
	}

	public static KhachHang getKhachHangByID(int id) {
		connectDB.getConnection();
		KhachHang khachHang = new KhachHang();

		try {
			String sql = "SELECT * FROM customers WHERE status = 1 AND customer_id=" + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				khachHang.setCustomerId(rs.getInt("customer_id"));
				khachHang.setCustomerName(rs.getString("customer_name"));
				khachHang.setPhoneNumber(rs.getString("phone_number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connectDB.closeConnection();
		return khachHang;
	}

	public static KhachHang getKhachHangByPhoneNumber(String phoneNumber) {
		connectDB.getConnection();
		KhachHang khachHang = new KhachHang();

		try {
			String sql = "SELECT * FROM customers WHERE status = 1 AND phone_number = " + phoneNumber;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				khachHang.setCustomerId(rs.getInt("customer_id"));
				khachHang.setCustomerName(rs.getString("customer_name"));
				khachHang.setPhoneNumber(rs.getString("phone_number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connectDB.closeConnection();
		return khachHang;
	}

}
