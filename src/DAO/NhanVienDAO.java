package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

import DTO.NhanVien;

public class NhanVienDAO {
	public static ArrayList<NhanVien> getDanhSachNhanVien() {
		ArrayList<NhanVien> dsnv = new ArrayList<>();
		
		try {
			String sql = "SELECT account_id as staff_id, full_name, phone_number, email, account_status as status "
                    + "FROM account "
                    + "WHERE position = 'staff'";
			ResultSet rs = connectDB.runQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien();
			    nv.setAccount_id(rs.getInt("staff_id"));
			    nv.setFull_name(rs.getString("full_name"));
			    nv.setPhone_number(rs.getString("phone_number"));
			    nv.setEmail(rs.getString("email"));
			    nv.setAccountStatus(rs.getInt("status"));
			    dsnv.add(nv);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return dsnv;
	}
}
