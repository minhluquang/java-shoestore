package BUS;

import java.util.ArrayList;
import DAO.ReturnDAO;
import DTO.Return;

public class ReturnBUS {
private static ArrayList<Return> dsbh = null;
	
	public static ArrayList<Return> getDanhSachReturn() {
		if (dsbh == null) {
			dsbh = ReturnDAO.getDanhSachReturn();
		} 
		return dsbh;
	}
}
