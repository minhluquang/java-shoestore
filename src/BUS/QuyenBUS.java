package BUS;

import java.util.ArrayList;

import DTO.Quyen;
import DAO.QuyenDAO;

public class QuyenBUS {
	private static ArrayList<Quyen> dsq;
	
	public static ArrayList<Quyen> getDanhSachQuyen() {
		dsq = QuyenDAO.getDanhSachQuyen();
		return dsq;
	}
}
