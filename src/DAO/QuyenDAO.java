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
				dsq.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		connectDB.closeConnection();
		return dsq;
	}
}
