package BUS;
import java.util.ArrayList;
import DAO.RoleDAO;
import DTO.Role;
public class RoleBUS {
	private static ArrayList<Role> dsrl = null;
		
		public static ArrayList<Role> getDanhSachRole() {
			if (dsrl == null) {
				dsrl = RoleDAO.getDanhSachRole();
			} 
			return dsrl;
		}
}
