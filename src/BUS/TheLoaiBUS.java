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
    public static int getSoluongTheLoai(){
        return TheLoaiDAO.getSoluongTheLoai();
    }
    public static boolean themTheLoai(TheLoaiDTO theLoaiDTO){
        return TheLoaiDAO.themTheLoai(theLoaiDTO);
    }
    public static boolean xoaTheLoai(int category_id){
        return TheLoaiDAO.xoaTheLoai(category_id);
    }
    public static boolean suaTheLoai(TheLoaiDTO theLoaiDTO){
        return TheLoaiDAO.suaTheLoai(theLoaiDTO);
    }
}
