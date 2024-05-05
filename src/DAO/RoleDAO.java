package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Role;

public class RoleDAO {
    public static ArrayList<Role> getDanhSachRole() {
    	connectDB.getConnection();
        ArrayList<Role> dsrl = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {   
            String sql = "SELECT role_id, role_name, role_tab_name FROM `roles`";           
            statement = connectDB.prepareStatement(sql);            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role rl = new Role();
                rl.setRole_id(resultSet.getInt("role_id"));
                rl.setRole_name(resultSet.getString("role_name"));
                rl.setRole_tab_name(resultSet.getString("role_tab_name"));
                dsrl.add(rl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.closeConnection();
        return dsrl;
    }

    public static ArrayList<Role> searchRole(String keyword) {
    	connectDB.getConnection();
        ArrayList<Role> dsrl = new ArrayList<>();        
        try {
            String sql = "SELECT * FROM `roles` WHERE role_id LIKE '%" + keyword + "%' OR role_name LIKE '%" + keyword + "%'";         
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                Role rl = new Role();            
                rl.setRole_id(rs.getInt("role_id"));
                rl.setRole_name(rs.getString("role_name"));
                dsrl.add(rl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
        connectDB.closeConnection();
        return dsrl;
    }

    public static int generateIdRole(boolean closeDatabase) {
    	connectDB.getConnection();
        int idRole = 0;
        try {
            String sql = "SELECT role_id FROM `roles` ORDER BY role_id DESC LIMIT 1";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int lastId = rs.getInt("role_id");
                idRole = lastId + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (closeDatabase) {
			connectDB.closeConnection();			
		}
        return idRole;
    }

    public static boolean isExistRole(int id) {
    	connectDB.getConnection();
        boolean isExist = false;        
        try {
            String sql = "SELECT * FROM `roles` WHERE role_id = " + id;
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

    public static boolean updateRole(int role_id, String role_name, String role_tab_name) {
    		connectDB.getConnection();
    		boolean success = false;
    		try {
    			String sql = "UPDATE `roles` "
    	                   + "SET role_name = '" + role_name + "', "
    	                   + "role_tab_name = '" + role_tab_name + "' "
    	                   + "WHERE role_id = " + role_id;
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

    	// insert
    public static boolean insertRole(int role_id, String role_name, String role_tab_name) {
        connectDB.getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO `roles` (role_id, role_name, role_tab_name) VALUES (" + role_id + ", '" + role_name + "', '" + role_tab_name + "')";
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
    	// delete
    public static boolean deleteRole(int role_id) {
    		connectDB.getConnection();
    	    boolean success = false;
    	    try {

    	        String sql = "DELETE FROM `roles` WHERE role_id = ?";   	        
    	        PreparedStatement statement = connectDB.prepareStatement(sql);   	        
    	        statement.setInt(1, role_id);    	        

    	        int rowsDeleted = statement.executeUpdate();
    	        if (rowsDeleted > 0) {
    	            success = true;
    	            updateNextRoleId(role_id);
    	        }
    	        statement.close();
    	        connectDB.closeConnection();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    connectDB.closeConnection();
    	    return success;
    	}
    	// update role_id sau khi xóa data đi
    	public static void updateNextRoleId(int deletedRoleId) {
    		connectDB.getConnection();
    	    try {
    	        // Tìm role_id lớn nhất mà nhỏ hơn deletedRoleId
    	        String sql = "SELECT MAX(role_id) AS max_id FROM `roles` WHERE role_id < ?";
    	        PreparedStatement statement = connectDB.prepareStatement(sql);
    	        statement.setInt(1, deletedRoleId);
    	        ResultSet resultSet = statement.executeQuery();
    	        int nextRoleId = 1; // Giá trị mặc định nếu không tìm thấy role_id nào nhỏ hơn
    	        if (resultSet.next()) {
    	            nextRoleId = resultSet.getInt("max_id") + 1;
    	        }
    	        // Thiết lập role_id tiếp theo
    	        sql = "ALTER TABLE `roles` AUTO_INCREMENT = ?";
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
    	
    	// tìm id sau khi click
    	public static Role getRoleById(int role_id) {
    		connectDB.getConnection();
    	    Role role = null;
    	    Connection connection = null;
    	    PreparedStatement statement = null;
    	    ResultSet resultSet = null;   	    
    	    try {
    	        String sql = "SELECT * FROM `roles` WHERE role_id = ?";
    	        statement = connectDB.prepareStatement(sql);
    	        statement.setInt(1, role_id);
    	        resultSet = statement.executeQuery();
    	        
    	        if (resultSet.next()) {
    	            role = new Role();
    	            role.setRole_id(resultSet.getInt("role_id"));
    	            role.setRole_name(resultSet.getString("role_name"));
    	            role.setRole_tab_name(resultSet.getString("role_tab_name"));
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
    	    return role;
    	}

}
