package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.KhuyenMai;
public class KhuyenmaiDAO {
	public static ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
		connectDB.getConnection();
		 ArrayList<KhuyenMai> dskm = new ArrayList<>();
		 Connection connection = null;
	     PreparedStatement statement = null;
	     ResultSet resultSet = null;
		try {
            String sql = "SELECT * FROM `discounts`";
            statement = connectDB.prepareStatement(sql);
            // Thực thi truy vấn và nhận kết quả
            resultSet = statement.executeQuery();
            // Duyệt qua kết quả và thêm vào danh sách
            while (resultSet.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setDiscount_code(resultSet.getString("discount_code"));
                km.setDiscount_value(resultSet.getInt("discount_value"));
                km.setType(resultSet.getString("type"));
                km.setStart_date(resultSet.getString("start_date"));
                km.setEnd_date(resultSet.getString("end_date"));
                km.setStatus(resultSet.getInt("status"));
                dskm.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		connectDB.closeConnection();
        return dskm;
	}
	// search
	public static ArrayList<KhuyenMai> searchKhuyenMai(String keyword, int status) {
		connectDB.getConnection();
		ArrayList<KhuyenMai> dskm = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM `discounts` WHERE (discount_code LIKE '%" + keyword + "%' OR status LIKE '%" + keyword + "%' OR discount_value LIKE '%" + keyword + "%' OR start_date LIKE '%" + keyword + "%' OR end_date LIKE '%" + keyword + "%')";
	        
	        if (status != -1) {
	            sql += " AND status = '" + (status == 1 ? "1" : "0") + "'";
	        }
	        
	        ResultSet rs = connectDB.runQuery(sql);
	        while (rs.next()) {
	            KhuyenMai km = new KhuyenMai();          
	            km.setDiscount_code(rs.getString("discount_code"));
                km.setDiscount_value(rs.getInt("discount_value"));
                km.setType(rs.getString("type"));
                km.setStart_date(rs.getString("start_date"));
                km.setEnd_date(rs.getString("end_date"));
                km.setStatus(rs.getInt("status"));                      
	            dskm.add(km);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }        
	    connectDB.closeConnection();
	    return dskm;
	}

	  // ktr tồn tại
	  public static boolean isExistKM(String discount_code) {
		  connectDB.getConnection();
		    boolean isExist = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet resultSet = null;
		    try {
		        // Chuẩn bị truy vấn SQL
		        String sql = "SELECT * FROM `discounts` WHERE discount_code = ?";
		        statement = connectDB.prepareStatement(sql);
		        statement.setString(1, discount_code);
		        // Thực thi truy vấn và nhận kết quả
		        resultSet = statement.executeQuery();
		        // mã giảm giá đã tồn tại
		        if (resultSet.next()) {
		            isExist = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    connectDB.closeConnection();
		    return isExist;
		}
	  public static boolean updateKhuyenMai(String discount_code, int discount_value , String type, String start_date, String end_date, int status) {
		  connectDB.getConnection();  
		  boolean success = false;
		  Connection connection = null;
		    PreparedStatement statement = null;
		    try {
		        String sql = "UPDATE `discounts` "
		                   + "SET discount_value = ?, type = ?, start_date = ?, end_date = ?, status = ? "
		                   + "WHERE discount_code = ?";
		        statement = connectDB.prepareStatement(sql);
		        statement.setInt(1, discount_value);
		        statement.setString(2, type);
		        statement.setString(3, start_date);
		        statement.setString(4, end_date);
		        statement.setInt(5, status);
		        statement.setString(6, discount_code);
		        // Thực thi truy vấn
		        int rowsUpdated = statement.executeUpdate();
		        if (rowsUpdated > 0) {
		            success = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } 
		    connectDB.closeConnection();
		    return success;
		}

		public static boolean insertKhuyenMai(String discount_code, int status, int discount_value, String start_date, String end_date, String type) {
			connectDB.getConnection();
			boolean success = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    try {
		        String sql = "INSERT INTO `discounts` (discount_code, discount_value, type , start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";
		        statement = connectDB.prepareStatement(sql);
		        statement.setString(1, discount_code);
		        statement.setInt(2, discount_value);
		        statement.setString(3, type);
		        statement.setString(4, start_date);
		        statement.setString(5, end_date);
		        statement.setInt(6, status);
		        // Thực thi truy vấn
		        int rowsInserted = statement.executeUpdate();
		        if (rowsInserted > 0) {
		            success = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } 
		    connectDB.closeConnection();
		    return success;
		}
		// delete
		public static boolean deleteKM(String discount_code) {
			connectDB.getConnection();
		    boolean success = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    try {
		        // Chuẩn bị truy vấn SQL để xóa dữ liệu dựa trên discount_code
		        String sql = "DELETE FROM `discounts` WHERE discount_code = ?";
		        statement = connectDB.prepareStatement(sql);
		        // Thiết lập tham số cho câu lệnh SQL
		        statement.setString(1, discount_code);
		        // Thực thi câu lệnh SQL
		        int rowsDeleted = statement.executeUpdate();
		        // Nếu có hàng bị xóa thành công, đặt success thành true
		        if (rowsDeleted > 0) {
		            success = true;
		        }
		    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    connectDB.closeConnection();
		    return success;
		}
		// edit
		public static KhuyenMai getKhuyenMaiByDiscountCode(String discount_code) {
			connectDB.getConnection();
			KhuyenMai km = new KhuyenMai();
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet rs = null;
		    try {
		        String sql = "SELECT * FROM `discounts` WHERE discount_code = ?";
		        statement = connectDB.prepareStatement(sql);
		        statement.setString(1, discount_code);
		        rs = statement.executeQuery();	        
		        if (rs.next()) {
		             km = new KhuyenMai();
		             km.setDiscount_code(rs.getString("discount_code"));
		             km.setDiscount_value(rs.getInt("discount_value"));
		             km.setType(rs.getString("type"));      
	                 km.setStart_date(rs.getString("start_date"));
	                 km.setEnd_date(rs.getString("end_date"));
	                 km.setStatus(rs.getInt("status"));
		        }
		    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    connectDB.closeConnection();	    
		    return km;
		}
}
