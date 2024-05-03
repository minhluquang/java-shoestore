package BUS;

import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import DAO.ChiTietSanPhamDAO;
import DTO.ChiTietSanPhamDTO;

public class ChiTietSanPhamBUS {
    public static ArrayList<ChiTietSanPhamDTO> getDanhSachChiTietSanPham() {
        return ChiTietSanPhamDAO.getDanhSachChiTietSanPham();
    }

    public static ChiTietSanPhamDTO getChiTietSanPhamBySerial(int product_serial_id) {
        return ChiTietSanPhamDAO.getProductDetailsBySerial(product_serial_id);
    }

    public static ArrayList<ChiTietSanPhamDTO> getChiTietSanPhamByID(int product_id) {
        return ChiTietSanPhamDAO.getProductDetailsByID(product_id);
    }

    public static boolean xoaChiTietSanPham(int product_serial_id) {
        return ChiTietSanPhamDAO.xoaProductDetails(product_serial_id);
    }

    public static boolean themChiTietSanPham(ChiTietSanPhamDTO productDetails) {
        return ChiTietSanPhamDAO.themProductDetails(productDetails);
    }

    public static boolean suaChiTietSanPham(ChiTietSanPhamDTO productDetails) {
        
        return ChiTietSanPhamDAO.suaProductDetails(productDetails);
    }

    public static boolean danhDauDanhSachDaBan(ArrayList<ChiTietSanPhamDTO> chiTietSanPhamDTOs){
        return ChiTietSanPhamDAO.danhDauDanhSachDaBan(chiTietSanPhamDTOs);
    }
    public static boolean danhDauDaBan(int product_details_id){
        return ChiTietSanPhamDAO.danhDauDaBan(product_details_id);
    }
}
