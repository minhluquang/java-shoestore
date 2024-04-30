package BUS;

import java.util.List;

import DAO.ThongKeDoanhThuDAO;
import DTO.ThongKeDoanhThuDTO;

public class ThongKeDoanhThuBUS {

    public static List<ThongKeDoanhThuDTO> getThongKeDoanhThu() {
        return ThongKeDoanhThuDAO.getThongKeDoanhThu();
    }
    
    public static List<ThongKeDoanhThuDTO> getThongKeTheoNgay(String month, String year) {
        return ThongKeDoanhThuDAO.getThongKeTheoNgay(month, year);
    }
    public static List<ThongKeDoanhThuDTO> getThongKeTheoThang(String year) {
        return ThongKeDoanhThuDAO.getThongKeTheoThang(year);
    }
    
    public static List<ThongKeDoanhThuDTO> thongKeTheoNam() {
        return ThongKeDoanhThuDAO.thongKeTheoNam();
    }
    
    public static List<ThongKeDoanhThuDTO> thongKeTheoKhoangNgay(String dateStart, String dateFinish) {
        return ThongKeDoanhThuDAO.ThongKeTheoKhoangNgay(dateStart, dateFinish);
    }
    
    public static int getTotalStaff() {
		return ThongKeDoanhThuDAO.getTotalStaff();
	}
    
    public static int getTotalCustomer() {
		return ThongKeDoanhThuDAO.getTotalCustomer();
	}
    
    
    
    public static int getTotalProduct() {
		return ThongKeDoanhThuDAO.getTotalProduct();
	}
    
}