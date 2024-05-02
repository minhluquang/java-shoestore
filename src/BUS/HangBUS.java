package BUS;

import java.util.ArrayList;

import DAO.HangDAO;
import DTO.HangDTO;

public class HangBUS {
    public static ArrayList<HangDTO> getDanhSachHang(){
        return HangDAO.getDanhSachHang();
    }
    public static HangDTO getHangByID(int brand_id){
        return HangDAO.getHangByID(brand_id);
    }
    public static int getSoluongHang(){
        return HangDAO.getSoluongHang();
    }
    public static boolean themHang(HangDTO hangDTO){
        return HangDAO.themHang(hangDTO);
    }
    public static boolean xoaHang(int brand_id){
        return HangDAO.xoaHang(brand_id);
    }
    public static boolean suaHang(HangDTO hangDTO){
        return HangDAO.suaHang(hangDTO);
    }
    public static HangDTO getHangByName(String brand_name){
        return HangDAO.getHangByName(brand_name);
    }
}
