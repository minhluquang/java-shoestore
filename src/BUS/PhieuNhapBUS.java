package BUS;

import java.util.ArrayList;

import javax.swing.JTable;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhap;
import DTO.SanPhamDTO;

public class PhieuNhapBUS {
	private static ArrayList<PhieuNhap> dspn;

	public static ArrayList<PhieuNhap> getDanhSachPhieuNhap() {
		dspn = PhieuNhapDAO.getDanhSachPhieuNhap();
		return dspn;
	}

	public static String getTenNhanVienById(int id) {
		return PhieuNhapDAO.getTenNhanVienById(id);
	}

	public static PhieuNhap getPhieuNhapById(int id) {
		PhieuNhap p = PhieuNhapDAO.getPhieuNhapById(id);
		return p;
	}

	public static int generate_Id() {
		return PhieuNhapDAO.generrate_Id();
	}

	public static double getThanhTien(int soLuong, int gia) {

		return soLuong * 1.0 * gia;
	}

	public static String renderTongSoTien(JTable table) {
		double sum = 0;
		int rowCount = table.getRowCount();
		int lastColumnIndex = table.getColumnCount() - 1;

		for (int i = 0; i < rowCount; i++) {
			Object lastColumnValue = table.getValueAt(i, lastColumnIndex);
			sum += (double) lastColumnValue;
		}
		return sum + "";
	}

	public static boolean create_GoodsReceipts(int receipt_id, String date, int supplier_id, int staff_id) {
		return PhieuNhapDAO.create_GoodsReceipts(receipt_id, date, supplier_id, staff_id);
	}

	public static int tinhTongTien(int receipt_id) {
		return PhieuNhapDAO.tinhTongTien(receipt_id);
	}

	public static boolean update_Total_Price(int receipt_id) {
		return PhieuNhapDAO.update_Total_Price(receipt_id);
	}

	public static String getInPutPriceByProductId(String product_id) {
		return PhieuNhapDAO.getInPutPriceByProductId(product_id);
	}

	public static String tinhGiaNhap(int giaBan) {
		int res = (int) (giaBan * (1 - 0.2));
		System.out.println(res);
		return "" + res;
	}

	public static ArrayList<SanPhamDTO> searchObjectById(String enteredText) {
		return PhieuNhapDAO.searchObjectById(enteredText);
	}

	public static ArrayList<PhieuNhap> searchByDays(String bd, String kt, String query) {
		return PhieuNhapDAO.searchByDays(bd, kt, query);
	}
}
