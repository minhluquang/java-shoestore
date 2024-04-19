package BUS;

import java.util.ArrayList;
import java.sql.ResultSet;
import DAO.connectDB;
import DAO.ReturnDAO;
import DTO.Return;


public class ReturnBUS {
private static ArrayList<Return> dsbh ;
	
	public static ArrayList<Return> getDanhSachReturn() {		
			dsbh = ReturnDAO.getDanhSachReturn();		
		return dsbh;
	}
	public static ArrayList<Return> searchReturn(String keyword) {
	    ArrayList<Return> dsbh = ReturnDAO.searchReturn(keyword);
	    return dsbh;
	}
	// write id
	public static int generateIdReturn() {
		int idreturn = ReturnDAO.generateIdReturn();
	    return idreturn;
	}
	// check
	public static boolean isExistReturn(int id) {
		boolean isExist = ReturnDAO.isExistReturn(id);
		return isExist;
	}
	// insert
	public static boolean insertReturn(int return_id, int product_id, String date_return,String reason) {
		boolean success = ReturnDAO.insertReturn(return_id,product_id,date_return,reason);
		return success;
	}
	// update
	public static boolean updateReturn(int return_id, int product_id, String date_return,String reason) {
		boolean success = ReturnDAO.updateReturn(return_id,product_id,date_return,reason);
	    return success;
	}
	// delete
	public static boolean deleteReturn(int return_id) {
	    boolean success = ReturnDAO.deleteReturn(return_id);
	    return success;
	}
	// edit
	public static Return getReturnById(int return_id) {
	    return ReturnDAO.getReturnById(return_id);
	}
}
