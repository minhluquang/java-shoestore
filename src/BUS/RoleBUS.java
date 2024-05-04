
package BUS;
import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.NhanVienDAO;
import DAO.RoleDAO;
import DAO.connectDB;
import DTO.NhanVien;
import DTO.Role;
public class RoleBUS {
	private static ArrayList<Role> dsrl ;
		
		public static ArrayList<Role> getDanhSachRole() {		
			dsrl = RoleDAO.getDanhSachRole();			
			return dsrl;
		}
		public static ArrayList<Role> searchRole(String keyword) {
		     dsrl = RoleDAO.searchRole(keyword);
		    return dsrl;
		}
		// write id
		public static int generateIdRole(boolean closeDatabase) {
			int idrole = RoleDAO.generateIdRole(closeDatabase);
		    return idrole;
		}
		// check
		public static boolean isExistRole(int id) {
			boolean isExist = RoleDAO.isExistRole(id);
		    return isExist;
		}

		// insert
		public static boolean insertRole(int role_id,String role_name,String role_tab_name, int status) {
			boolean success = RoleDAO.insertRole(role_id,role_name,role_tab_name,status);
			return success;
		}
		// update
		public static boolean updateRole(int role_id, String role_name,String role_tab_name, int status) {
			boolean success = RoleDAO.updateRole(role_id, role_name,role_tab_name,status);
		    return success;
		}	
		// delete
		public static boolean deleteRole(int role_id) {
		    boolean success = RoleDAO.deleteRole(role_id);
		    return success;
		}
		
		// edit
		public static Role getRoleById(int role_id) {
		    return RoleDAO.getRoleById(role_id);
		}

}

