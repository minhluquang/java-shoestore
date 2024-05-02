package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ChiTietHoaDonDTO;

public class ChiTietHoaDonDAO {
    public static ArrayList<ChiTietHoaDonDTO> getDanhSachChiTietHoaDon() {
        ArrayList<ChiTietHoaDonDTO> billDetails = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM bills_details";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int productSerialId = rs.getInt("product_serial_id");
                int billId = rs.getInt("bill_id");
                int priceSingle = rs.getInt("price_single");

                ChiTietHoaDonDTO billDetail = new ChiTietHoaDonDTO(productSerialId, billId, priceSingle);
                billDetails.add(billDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return billDetails;
    }

    public static ArrayList<ChiTietHoaDonDTO> getBillsDetailsByID(int bill_id) {
        ArrayList<ChiTietHoaDonDTO> billsDetails = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM bills_details WHERE bill_id ="+bill_id;
            ResultSet rs = connectDB.runQuery(sql);
            while(rs.next()) {
                int product_serial_id = rs.getInt("product_serial_id");
                int price_single = rs.getInt("price_single");

                ChiTietHoaDonDTO billsDetail = new ChiTietHoaDonDTO();
                billsDetail.setProductSerialId(product_serial_id);
                billsDetail.setBillId(bill_id);
                billsDetail.setPriceSingle(price_single);
                billsDetails.add(billsDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return billsDetails;
    }

    public static boolean xoaBillsDetails(int product_serial_id, int bill_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "DELETE FROM bills_details WHERE product_serial_id = ? AND bill_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, product_serial_id);
            pstmt.setInt(2, bill_id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return flag;
    }

    public static boolean themBillsDetails(ChiTietHoaDonDTO billsDetails) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO bills_details (product_serial_id, bill_id, price_single) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, billsDetails.getProductSerialId());
            pstmt.setInt(2, billsDetails.getBillId());
            pstmt.setInt(4, billsDetails.getPriceSingle());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return flag;
    }

    public static boolean themDSBillsDetails(ArrayList<ChiTietHoaDonDTO> billsDetails) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO bills_details (product_serial_id, bill_id, price_single) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            int rowsAffected = 0;
            for (ChiTietHoaDonDTO chiTietHoaDonDTO : billsDetails) {
                pstmt.setInt(1, chiTietHoaDonDTO.getProductSerialId());
                pstmt.setInt(2, chiTietHoaDonDTO.getBillId());
                pstmt.setInt(3, chiTietHoaDonDTO.getPriceSingle());
                rowsAffected += pstmt.executeUpdate();
            }
            if (rowsAffected == 0) {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return flag;
    }

    public static boolean suaBillsDetails(ChiTietHoaDonDTO billsDetails) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE bills_details SET price_single = ? WHERE product_serial_id = ? AND bill_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, billsDetails.getPriceSingle());
            pstmt.setInt(2, billsDetails.getProductSerialId());
            pstmt.setInt(3, billsDetails.getBillId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return flag;
    }
}
