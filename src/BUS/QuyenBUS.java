package BUS;

import java.util.ArrayList;

import DTO.Quyen;
import DAO.QuyenDAO;

public class QuyenBUS {
	public static ArrayList<Quyen> getDanhSachQuyen() {
		return QuyenDAO.getDanhSachQuyen();
	}
	
	public static ArrayList<Quyen> getDanhSachQuyenByUsername(String username) {
		return QuyenDAO.getDanhSachQuyenByUsername(username);
	}
}
