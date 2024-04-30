package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Quyen;

public class QuyenDAO {
	public static ArrayList<Quyen> getDanhSachQuyen() {
		connectDB.getConnection();
		ArrayList<Quyen> dsq = new ArrayList<>();
		
		try {
			String sql = "SELECT * "
					+ "FROM roles ";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				Quyen q = new Quyen();
				q.setRoleId(rs.getInt("role_id"));
				q.setRoleName(rs.getString("role_name"));
				q.setRoleTabName(rs.getString("role_tab_name"));
				q.setStatus(rs.getInt("status"));
				dsq.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dsq;
	}
	
	public static ArrayList<Quyen> getDanhSachQuyenByUsername(String username) {
		connectDB.getConnection();
		ArrayList<Quyen> dsq = new ArrayList<>();
		
		try {
			String sql = "SELECT r.role_id, r.role_name, r.role_tab_name, r.status as role_status "
					+ "FROM accounts a "
					+ "INNER JOIN role_details rd ON rd.account_id = a.account_id  "
					+ "JOIN roles r ON r.role_id = rd.role_id "
					+ "WHERE a.username = '" + username + "'";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				Quyen q = new Quyen();
				q.setRoleId(rs.getInt("role_id"));
				q.setRoleName(rs.getString("role_name"));
				q.setStatus(rs.getInt("role_status"));
				q.setRoleTabName(rs.getString("role_tab_name"));
				dsq.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dsq;
	}
}
