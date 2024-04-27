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
}
