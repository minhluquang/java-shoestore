package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.NhaCungCap;

public class NhaCungCapDAO {

	public static ArrayList<NhaCungCap> getDanhSachNhaCungCap() {
		ArrayList<NhaCungCap> dsncc = new ArrayList<>();
		connectDB.getConnection();
		try {
			String sql = "Select * from suppliers";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setSupplier_id(rs.getInt("supplier_id"));
				ncc.setSupplier_name(rs.getString("supplier_name"));
				ncc.setSupplier_addresss(rs.getString("supplier_address"));
				ncc.setStatus(rs.getInt("status"));
				dsncc.add(ncc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return dsncc;
	}

	public static String getTenNhaCungCapById(int id) {
		String tenNhaCungCap = "";
		connectDB.getConnection();
		try {
			String sql = "SELECT supplier_name FROM suppliers WHERE supplier_id = " + id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				tenNhaCungCap = rs.getString("supplier_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return tenNhaCungCap;
	}

	public static int generrate_Id() {
		connectDB.getConnection();
		int res = -1;
		try {
			String sql = "SELECT supplier_id FROM suppliers ORDER BY supplier_id DESC LIMIT 1";
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				res = rs.getInt("supplier_id");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return res + 1;
	}

	public static boolean insertPublisher(String tenNcc, String diachi) {
		connectDB.getConnection();
		boolean success = false;
		String sql = "insert into suppliers (`supplier_name`,`supplier_address`,`status`) values (?,?,?)";
		try {
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setString(1, tenNcc);
			mystm.setString(2, diachi);
			mystm.setInt(3, 1);

			int i = mystm.executeUpdate();
			if (i > 0) {
				success = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return success;
	}

	public static boolean insertPublisher(ArrayList<NhaCungCap> dsncc) {
		connectDB.getConnection();
		boolean success = true;
		for (NhaCungCap nv : dsncc) {
			String name = nv.getSupplier_name();
			String address = nv.getSupplier_addresss();
			success = insertPublisher(name, address);
			if (!success) {
				return success;
			}
		}

		return success;
	}

	public static boolean updatePublisher(int id, String tenNcc, String diachi) {
		connectDB.getConnection();
		boolean success = false;
		try {
			String sql = "UPDATE  suppliers SET supplier_name = ?,supplier_address =? WHERE supplier_id =?";
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setString(1, tenNcc);
			mystm.setString(2, diachi);
			mystm.setInt(3, id);
			int i = mystm.executeUpdate();
			if (i > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		connectDB.closeConnection();
		return success;
	}

	public static boolean deletePublisher(int id) {
		connectDB.getConnection();
		boolean success = false;
		try {
			String sql = "UPDATE suppliers 	SET status = ? WHERE supplier_id= ?";
			PreparedStatement mystm = connectDB.prepareStatement(sql);
			mystm.setInt(1, 0);
			mystm.setInt(2, id);
			int rowsDeleted = mystm.executeUpdate();
			if (rowsDeleted > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return success;
	}

	public static ArrayList<NhaCungCap> getListNCCByQuery(String enteredText) {
		connectDB.getConnection();
		ArrayList<NhaCungCap> ds = new ArrayList<>();
		String sql = "SELECT * FROM suppliers WHERE supplier_name LIKE '%" + enteredText
				+ "%' or supplier_address LIKE '%" + enteredText + "%'";
		try {
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setSupplier_id(rs.getInt("supplier_id"));
				ncc.setSupplier_name(rs.getString("supplier_name"));
				ncc.setSupplier_addresss(rs.getString("supplier_address"));
				ncc.setStatus(rs.getInt("status"));
				ds.add(ncc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return ds;
	}
}
