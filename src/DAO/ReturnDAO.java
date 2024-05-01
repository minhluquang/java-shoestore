package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DTO.Return;



public class ReturnDAO {
    public static ArrayList<Return> getDanhSachReturn() {
    	connectDB.getConnection();
        ArrayList<Return> dsReturn = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT return_id, product_id, date_return, reason FROM `return`";
            statement = connectDB.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Return rt = new Return();
                rt.setReturn_id(resultSet.getInt("return_id"));
                rt.setProduct_id(resultSet.getInt("product_id"));
                rt.setDate_return(resultSet.getString("date_return"));
                rt.setReason(resultSet.getString("reason"));
                dsReturn.add(rt);
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
        connectDB.closeConnection();
        return dsReturn;
    }
    public static int generateIdReturn(boolean closeDatabase) {
    	connectDB.getConnection();
        int idReturn = 0;
        try {
        	String sql = "SELECT return_id FROM `return` ORDER BY return_id DESC LIMIT 1";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int lastId = rs.getInt("return_id");
                idReturn = lastId + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (closeDatabase) {
			connectDB.closeConnection();			
		}
        return idReturn;
    }
    public static boolean isExistReturn(int id) {
    	connectDB.getConnection();
        boolean isExist = false;        
        try {
            String sql = "SELECT * FROM `return` WHERE return_id = " + id;
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
  
    
    public static ArrayList<Return> searchReturn(String keyword) {
    	connectDB.getConnection();
        ArrayList<Return> dsReturn = new ArrayList<>();
        try {
        	 String sql = "SELECT * FROM `return` WHERE return_id LIKE '%" + keyword + "%' OR reason LIKE '%" + keyword + "%' OR date_return LIKE '%" + keyword + "%' OR product_id LIKE '%" + keyword + "%'";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                Return rt = new Return();
                rt.setReturn_id(rs.getInt("return_id"));
                rt.setProduct_id(rs.getInt("product_id"));
                rt.setDate_return(rs.getString("date_return"));
                rt.setReason(rs.getString("reason"));
                dsReturn.add(rt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return dsReturn;
    }
    
    // insert
    public static boolean insertReturn(int return_id, int product_id, String date_return,String reason) {
    	connectDB.getConnection();
    	boolean success = false;
    	try {
    		String sql = "INSERT INTO `return` (return_id,product_id,date_return,reason) VALUES (" + return_id + ",'" + product_id + "','" + date_return + "' ,'" + reason +"')";
    		int i = connectDB.runUpdate(sql);
    		if(i>0) {
    			success = true;
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	connectDB.closeConnection();
    	return success;
    }
    
	// tìm id sau khi click
	public static Return getReturnById(int return_id) {
		connectDB.getConnection();
	    Return rt = null;
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;   	    
	    try {
	        String sql = "SELECT * FROM `return` WHERE return_id = ?";
	        statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, return_id);
	        resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	        	rt = new Return();
	        	 rt.setReturn_id(resultSet.getInt("return_id"));
	             rt.setProduct_id(resultSet.getInt("product_id"));
	             rt.setDate_return(resultSet.getString("date_return"));
	             rt.setReason(resultSet.getString("reason"));   	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }    	    
	    connectDB.closeConnection();
	    return rt;
	}
	// delete
	public static boolean deleteReturn(int return_id) {
		connectDB.getConnection();
	    boolean success = false;
	    try {	       
	        String sql = "DELETE FROM `return` WHERE return_id = ?";   	        
	        connectDB.getConnection();
	        PreparedStatement statement = connectDB.prepareStatement(sql);   	        	       
	        statement.setInt(1, return_id);    	        
	        // Thực thi câu lệnh SQL
	        int rowsDeleted = statement.executeUpdate();
	        // Kiểm tra xem có bản ghi nào bị xóa không
	        if (rowsDeleted > 0) {
	            success = true;
	            // Cập nhật lại giá trị role_id cho bản ghi tiếp theo
	            updateNextReturnId(return_id);
	        }
	        // Đóng kết nối và tài nguyên
	        statement.close();
	        connectDB.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    connectDB.closeConnection();
	    return success;
	}
	// update role_id sau khi xóa data đi
	public static void updateNextReturnId(int deletedReturnId) {
		connectDB.getConnection();
	    try {
	        // Tìm role_id lớn nhất mà nhỏ hơn deletedRoleId
	        String sql = "SELECT MAX(return_id) AS max_id FROM `return` WHERE return_id < ?";
	        PreparedStatement statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, deletedReturnId);
	        ResultSet resultSet = statement.executeQuery();
	        int nextRoleId = 1; // Giá trị mặc định nếu không tìm thấy role_id nào nhỏ hơn
	        if (resultSet.next()) {
	            nextRoleId = resultSet.getInt("max_id") + 1;
	        }
	        // Thiết lập role_id tiếp theo
	        sql = "ALTER TABLE `return` AUTO_INCREMENT = ?";
	        statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, nextRoleId);
	        statement.executeUpdate();
	        // Đóng kết nối và tài nguyên
	        resultSet.close();
	        statement.close();
	        connectDB.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static boolean updateReturn(int return_id, int product_id, String date_return, String reason) {
		connectDB.getConnection();
		boolean success = false;
	    try {
	        String sql = "UPDATE `return` SET product_id = ?, date_return = ?, reason = ? WHERE return_id = ?";
	        PreparedStatement statement = connectDB.prepareStatement(sql);
	        // Set the parameters for the PreparedStatement
	        statement.setInt(1, product_id);
	        statement.setString(2, date_return);
	        statement.setString(3, reason);
	        statement.setInt(4, return_id);	        
	        // Execute the update query
	        int rowsUpdated = statement.executeUpdate();	        
	        // Check if the update was successful
	        if (rowsUpdated > 0) {
	            success = true;
	        }	        
	        // Close the PreparedStatement and connection
	        statement.close();
	        connectDB.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    connectDB.closeConnection();
	    return success;
	}

}
