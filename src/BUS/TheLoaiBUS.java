package BUS;

import java.util.ArrayList;

import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;

public class TheLoaiBUS {
    public static ArrayList<TheLoaiDTO> getDanhSachTheLoai(){
        return TheLoaiDAO.getDanhSachTheLoai();
    }
    public static TheLoaiDTO getTheLoaiByID(int category_id){
        return TheLoaiDAO.getTheLoaiByID(category_id);
    }
}
