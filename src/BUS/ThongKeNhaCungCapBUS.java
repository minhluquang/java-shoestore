package BUS;

import java.util.List;

import DAO.ThongKeNhaCungCapDAO;
import DTO.ThongKeNhaCungCapDTO;

public class ThongKeNhaCungCapBUS {
	public static List<ThongKeNhaCungCapDTO> getAllNCC() {
		return ThongKeNhaCungCapDAO.getAllNCC();
	}
	
	public static List<ThongKeNhaCungCapDTO> getNCCSearch(String tenNCC) {
		return ThongKeNhaCungCapDAO.getNCCSearch(tenNCC);
	}
	
}
