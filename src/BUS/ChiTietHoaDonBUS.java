package BUS;

import java.util.ArrayList;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;

public class ChiTietHoaDonBUS {
    public static ArrayList<ChiTietHoaDonDTO> getAllChiTietHoaDon(){
        return ChiTietHoaDonDAO.getDanhSachChiTietHoaDon();
    }
    public static ArrayList<ChiTietHoaDonDTO> getChiTietHoaDonByID(int bill_id){
        return ChiTietHoaDonDAO.getBillsDetailsByID(bill_id);
    }
    public static boolean themChiTietHoaDon(ChiTietHoaDonDTO chiTietHoaDon){
        return ChiTietHoaDonDAO.themBillsDetails(chiTietHoaDon);
    }
    public static boolean suaChiTIetHoaDon(ChiTietHoaDonDTO chiTietHoaDon){
        return ChiTietHoaDonDAO.suaBillsDetails(chiTietHoaDon);
    }
    public static boolean xoaChiTietHoaDon(int product_serial, int bill_id){
        return ChiTietHoaDonDAO.xoaBillsDetails(product_serial, bill_id);
    }
    public static boolean themDSChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> billsDetails){
        return ChiTietHoaDonDAO.themDSBillsDetails(billsDetails);
    }
    
}
