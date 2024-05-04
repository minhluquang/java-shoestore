package BUS;

import java.util.ArrayList;

import DAO.WarrantyDAO;
import DTO.Warranty;

public class WarrantyBUS {
	private static ArrayList<Warranty> dswt;
	public static ArrayList<Warranty> getDanhSachWarranty() {
		dswt = WarrantyDAO.getDanhSachWarranty();
		return dswt;
	}
	public static ArrayList<Warranty> searchWarranty(String keyword, int status) {
		dswt = WarrantyDAO.searchWarranty(keyword, status);
		return dswt;
	}
	public static int generateIdWar(boolean closeDatabase) {
		int idWar = WarrantyDAO.generateIdWar(closeDatabase);
		return idWar;
	}
	public static boolean isExitWar(int id) {
		boolean isExist = WarrantyDAO.isExitWar(id);
		return isExist;
	}
	public static boolean insertWar(int warranty_detail_id, int product_serial_id,String warranty_date,String reason,String active,int status) {
		boolean success = WarrantyDAO.insertWar(warranty_detail_id,product_serial_id,warranty_date,reason,active,status);
		return success;
	}
	public static boolean updateWar(int warranty_detail_id, int product_serial_id,String warranty_date,String reason,String active,int status) {
		boolean success = WarrantyDAO.updateWar(warranty_detail_id,product_serial_id,warranty_date,reason,active,status);
		return success;
	}
	public static boolean deleteWar(int warranty_detail_id) {
		boolean success = WarrantyDAO.deleteWar(warranty_detail_id);
		return success;
	}
	public static Warranty getWarById(int warranty_detail_id) {
		return WarrantyDAO.getWarById(warranty_detail_id);
	}
}
