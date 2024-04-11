package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import DTO.Role;
public class RoleDAO {
	    public static ArrayList<Role> getDanhSachRole() {
	        ArrayList<Role> dsrl = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            // Thực hiện kết nối cơ sở dữ liệu
	            connection = connectDB.getConnection();

	            // Chuẩn bị truy vấn SQL
	            String sql = "SELECT role_id, role_name FROM `role`";
	            statement = connection.prepareStatement(sql);

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

}
