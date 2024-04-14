package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.KhuyenMai;
public class KhuyenmaiDAO {
	public static ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
		 ArrayList<KhuyenMai> dskm = new ArrayList<>();
		 Connection connection = null;
	     PreparedStatement statement = null;
	     ResultSet resultSet = null;
		try {
            // Thực hiện kết nối cơ sở dữ liệu
            connection = connectDB.getConnection();
            // Chuẩn bị truy vấn SQL
            String sql = "SELECT * FROM `discount`";
            statement = connection.prepareStatement(sql);
            // Thực thi truy vấn và nhận kết quả
            resultSet = statement.executeQuery();
            // Duyệt qua kết quả và thêm vào danh sách
            while (resultSet.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setDiscount_code(resultSet.getString("discount_code"));
                km.setConditionValue(resultSet.getInt("condition_value"));
                km.setDiscount(resultSet.getString("discount"));
                km.setStart_date(resultSet.getString("start_date"));
                km.setEnd_date(resultSet.getString("end_date"));
                km.setActive(resultSet.getString("active"));
                dskm.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dskm;
	}
	// search
	public static ArrayList<KhuyenMai> searchKhuyenMai(String keyword, int status) {
	    ArrayList<KhuyenMai> dskm = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM discount WHERE (discount_code LIKE '%" + keyword + "%' OR condition_value LIKE '%" + keyword + "%' OR discount LIKE '%" + keyword + "%' OR start_date LIKE '%" + keyword + "%' OR end_date LIKE '%" + keyword + "%')";
	        
	        if (status != -1) {
	            sql += " AND active = '" + (status == 1 ? "Active" : "Non Active") + "'";
	        }
	        
	        ResultSet rs = connectDB.runQuery(sql);
	        while (rs.next()) {
	            KhuyenMai km = new KhuyenMai();          
	            km.setDiscount_code(rs.getString("discount_code"));
	            km.setConditionValue(rs.getInt("condition_value"));
	            km.setDiscount(rs.getString("discount"));
	            km.setStart_date(rs.getString("start_date"));
	            km.setEnd_date(rs.getString("end_date"));
	            km.setActive(rs.getString("active"));                          
	            dskm.add(km);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }        
	    return dskm;
	}

	  // ktr tồn tại
	  public static boolean isExistKM(String discount_code) {
		    boolean isExist = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet resultSet = null;
		    try {
		        // Thực hiện kết nối cơ sở dữ liệu
		        connection = connectDB.getConnection();
		        // Chuẩn bị truy vấn SQL
		        String sql = "SELECT * FROM discount WHERE discount_code = ?";
		        statement = connection.prepareStatement(sql);
		        statement.setString(1, discount_code);
		        // Thực thi truy vấn và nhận kết quả
		        resultSet = statement.executeQuery();
		        // mã giảm giá đã tồn tại
		        if (resultSet.next()) {
		            isExist = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Đóng các đối tượng ResultSet, PreparedStatement và Connection
		        try {
		            if (resultSet != null) {
		                resultSet.close();
		            }
		            if (statement != null) {
		                statement.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return isExist;
		}
	  public static boolean updateKhuyenMai(String discount_code, int condition_value, String discount, String start_date, String end_date, String active) {
		    boolean success = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    try {
		        // Thực hiện kết nối cơ sở dữ liệu
		        connection = connectDB.getConnection();
		        // Chuẩn bị truy vấn SQL
		        String sql = "UPDATE discount "
		                   + "SET condition_value = ?, discount = ?, start_date = ?, end_date = ?, active = ? "
		                   + "WHERE discount_code = ?";
		        statement = connection.prepareStatement(sql);
		        statement.setInt(1, condition_value);
		        statement.setString(2, discount);
		        statement.setString(3, start_date);
		        statement.setString(4, end_date);
		        statement.setString(5, active);
		        statement.setString(6, discount_code);
		        // Thực thi truy vấn
		        int rowsUpdated = statement.executeUpdate();
		        if (rowsUpdated > 0) {
		            success = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Đóng các đối tượng PreparedStatement và Connection
		        try {
		            if (statement != null) {
		                statement.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return success;
		}

		public static boolean insertKhuyenMai(String discount_code, int condition_value, String discount, String start_date, String end_date, String active) {
		    boolean success = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    try {
		        // Thực hiện kết nối cơ sở dữ liệu
		        connection = connectDB.getConnection();
		        // Chuẩn bị truy vấn SQL
		        String sql = "INSERT INTO discount (discount_code, condition_value, discount, start_date, end_date, active) VALUES (?, ?, ?, ?, ?, ?)";
		        statement = connection.prepareStatement(sql);
		        statement.setString(1, discount_code);
		        statement.setInt(2, condition_value);
		        statement.setString(3, discount);
		        statement.setString(4, start_date);
		        statement.setString(5, end_date);
		        statement.setString(6, active);
		        // Thực thi truy vấn
		        int rowsInserted = statement.executeUpdate();
		        if (rowsInserted > 0) {
		            success = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Đóng các đối tượng PreparedStatement và Connection
		        try {
		            if (statement != null) {
		                statement.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return success;
		}
		// delete
		public static boolean deleteKM(String discount_code) {
		    boolean success = false;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    try {
		        // Thực hiện kết nối cơ sở dữ liệu
		        connection = connectDB.getConnection();
		        // Chuẩn bị truy vấn SQL để xóa dữ liệu dựa trên discount_code
		        String sql = "DELETE FROM discount WHERE discount_code = ?";
		        statement = connection.prepareStatement(sql);
		        // Thiết lập tham số cho câu lệnh SQL
		        statement.setString(1, discount_code);
		        // Thực thi câu lệnh SQL
		        int rowsDeleted = statement.executeUpdate();
		        // Nếu có hàng bị xóa thành công, đặt success thành true
		        if (rowsDeleted > 0) {
		            success = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Đóng các đối tượng PreparedStatement và Connection
		        try {
		            if (statement != null) {
		                statement.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return success;
		}
		// edit
		public static KhuyenMai getKhuyenMaiByDiscountCode(String discount_code) {
			KhuyenMai km = new KhuyenMai();
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet rs = null;
		    try {
		        connection = connectDB.getConnection();
		        String sql = "SELECT * FROM discount WHERE discount_code = ?";
		        statement = connection.prepareStatement(sql);
		        statement.setString(1, discount_code);
		        rs = statement.executeQuery();	        
		        if (rs.next()) {
		             km = new KhuyenMai();
		             km.setDiscount_code(rs.getString("discount_code"));
	                 km.setConditionValue(rs.getInt("condition_value"));
	                 km.setDiscount(rs.getString("discount"));
	                 km.setStart_date(rs.getString("start_date"));
	                 km.setEnd_date(rs.getString("end_date"));
	                 km.setActive(rs.getString("active"));      
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) {
		            	rs.close();
		            }
		            if (statement != null) {
		                statement.close();
		            }
		            if (connection != null) {
		                connection.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }		    
		    return km;
		}
}
