package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Role;

public class RoleDAO {
    public static ArrayList<Role> getDanhSachRole() {
        ArrayList<Role> dsrl = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Thực hiện kết nối cơ sở dữ liệu
//            connection = connectDB.getConnection();

            // Chuẩn bị truy vấn SQL
            String sql = "SELECT role_id, role_name FROM `role`";
            statement = connectDB.prepareStatement(sql);

            // Thực thi truy vấn và nhận kết quả
            resultSet = statement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách
            while (resultSet.next()) {
                Role rl = new Role();
                rl.setRole_id(resultSet.getInt("role_id"));
                rl.setRole_name(resultSet.getString("role_name"));

                dsrl.add(rl);
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
        return dsrl;
    }

    public static ArrayList<Role> searchRole(String keyword) {
        ArrayList<Role> dsrl = new ArrayList<>();        
        try {
            String sql = "SELECT * FROM role WHERE role_id LIKE '%" + keyword + "%' OR role_name LIKE '%" + keyword + "%'";
            
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
        return dsrl;
    }

    public static int generateIdRole() {
        int idRole = 0;
        try {
            String sql = "SELECT role_id FROM role ORDER BY role_id DESC LIMIT 1";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int lastId = rs.getInt("role_id");
                idRole = lastId + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idRole;
    }

    public static boolean isExistRole(int id) {
        boolean isExist = false;        
        try {
            String sql = "SELECT * FROM role WHERE role_id = " + id;
            ResultSet rs = connectDB.runQuery(sql);            
            if (rs.next()) {
                isExist = true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return isExist;
    }

    public static boolean updateRole(int role_id, String role_name) {
    		boolean success = false;
    		try {
    			String sql = "UPDATE role "
                    + "SET role_name = '" + role_name + "' "
                    + "WHERE role_id = " + role_id;
    			int i = connectDB.runUpdate(sql);
    			if (i > 0) {
                success = true;
    			}
    		} catch (Exception e) {
            e.printStackTrace();
    		}
    		return success;
    	}

    	// insert
    public static boolean insertRole(int role_id, String role_name) {
    	    boolean success = false;
    	    try {
    	        String sql = "INSERT INTO role (role_id, role_name) VALUES (" + role_id + ", '" + role_name + "')";
    	        int i = connectDB.runUpdate(sql);
    	        if (i > 0) {
    	            success = true;
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return success;
    	}
    	// delete
    	public static boolean deleteRole(int role_id) {
    	    boolean success = false;
    	    try {
    	        // Tạo câu lệnh SQL để xóa vai trò dựa trên role_id
    	        String sql = "DELETE FROM role WHERE role_id = ?";   	        
    	        // Kết nối cơ sở dữ liệu và chuẩn bị câu lệnh SQL
//    	        Connection connection = connectDB.getConnection();
    	        PreparedStatement statement = connectDB.prepareStatement(sql);   	        
    	        // Thiết lập tham số cho câu lệnh SQL
    	        statement.setInt(1, role_id);    	        
    	        // Thực thi câu lệnh SQL
    	        int rowsDeleted = statement.executeUpdate();
    	        // Kiểm tra xem có bản ghi nào bị xóa không
    	        if (rowsDeleted > 0) {
    	            success = true;
    	            // Cập nhật lại giá trị role_id cho bản ghi tiếp theo
    	            updateNextRoleId(role_id);
    	        }
    	        // Đóng kết nối và tài nguyên
    	        statement.close();
    	        connectDB.closeConnection();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return success;
    	}
    	// update role_id sau khi xóa data đi
    	public static void updateNextRoleId(int deletedRoleId) {
    	    try {
    	        // Tìm role_id lớn nhất mà nhỏ hơn deletedRoleId
    	        String sql = "SELECT MAX(role_id) AS max_id FROM role WHERE role_id < ?";
//    	        Connection connection = connectDB.getConnection();
    	        PreparedStatement statement = connectDB.prepareStatement(sql);
    	        statement.setInt(1, deletedRoleId);
    	        ResultSet resultSet = statement.executeQuery();
    	        int nextRoleId = 1; // Giá trị mặc định nếu không tìm thấy role_id nào nhỏ hơn
    	        if (resultSet.next()) {
    	            nextRoleId = resultSet.getInt("max_id") + 1;
    	        }
    	        // Thiết lập role_id tiếp theo
    	        sql = "ALTER TABLE role AUTO_INCREMENT = ?";
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
    	    Role role = null;
    	    Connection connection = null;
    	    PreparedStatement statement = null;
    	    ResultSet resultSet = null;   	    
    	    try {
//    	        connection = connectDB.getConnection();
    	        String sql = "SELECT * FROM role WHERE role_id = ?";
    	        statement = connectDB.prepareStatement(sql);
    	        statement.setInt(1, role_id);
    	        resultSet = statement.executeQuery();
    	        
    	        if (resultSet.next()) {
    	            role = new Role();
    	            role.setRole_id(resultSet.getInt("role_id"));
    	            role.setRole_name(resultSet.getString("role_name"));    	            
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
    	    return role;
    	}

}
