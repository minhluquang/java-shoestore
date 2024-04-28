package BUS;

import java.util.List;

import DAO.ThongKeDoanhThuDAO;
import DTO.ThongKeDoanhThuDTO;

public class ThongKeDoanhThuBUS {

    public static List<ThongKeDoanhThuDTO> getThongKeDoanhThu() {
        return ThongKeDoanhThuDAO.getThongKeDoanhThu();
    }
}