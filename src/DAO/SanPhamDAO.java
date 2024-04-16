package DAO;

import java.sql.ResultSet;

public class SanPhamDAO {

	public static String getTenSanPhamById(int product_id) {
		String tenSanPham = "";
		try {
			String sql = "select product_name from product where product_id=" + product_id;
			ResultSet rs = connectDB.runQuery(sql);
			if (rs.next()) {
				tenSanPham = rs.getString("product_name");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tenSanPham;
	}
}
