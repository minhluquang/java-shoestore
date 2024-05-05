package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DTO.HoaDonDTO;
import DAO.HoaDonDAO;

public class HoaDonBUS {
    public static ArrayList<HoaDonDTO> getDanhSachHoaDon() {
        return HoaDonDAO.getDanhSachHoaDon();
    }

    public static HoaDonDTO getHoaDonByID(int bill_id) {
        return HoaDonDAO.getBillByID(bill_id);
    }

    public static boolean xoaHoaDon(int bill_id) {
        return HoaDonDAO.deleteBill(bill_id);
    }

    public static boolean themHoaDon(HoaDonDTO bill) {
        return HoaDonDAO.insertBill(bill);
    }

    public static boolean suaHoaDon(HoaDonDTO bill) {
        return HoaDonDAO.updateBill(bill);
    }

    public static int getSoLuongHoaDon() {
        return HoaDonDAO.getSoLuongBill();
    }

    public static ArrayList<HoaDonDTO> searchHoaDon(int totalMin, int totalMax, int MaKH, int MaNV, Date dateStart, Date dateEnd){
        return HoaDonDAO.searchDanhSachHoaDon(totalMin, totalMax, MaKH, MaNV, dateStart, dateEnd);
    }

    public static int generateIdHoaDon(){
        return HoaDonDAO.generateIdHoaDon();
    }
}
