package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.HangDTO;

public class HangDAO {

    public static ArrayList<HangDTO> getDanhSachHang() {
        ArrayList<HangDTO> brands = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM brands";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int brand_id = rs.getInt("brand_id");
                String brand_name = rs.getString("brand_name");
                boolean status = rs.getBoolean("status");

                HangDTO brand = new HangDTO(brand_id, brand_name, status);
                brands.add(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return brands;
    }

    public static HangDTO getHangByID(int brand_id) {
        HangDTO brand = new HangDTO();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM brands WHERE brand_id = " + brand_id;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                String brand_name = rs.getString("brand_name");
                boolean status = rs.getBoolean("status");

                brand.setBrand_id(brand_id);
                brand.setBrand_name(brand_name);
                brand.setStatus(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return brand;
    }

    public static boolean xoaHang(int brand_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE brands SET status = false WHERE brand_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, brand_id);

            // Execute the update operation
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

    public static boolean themHang(HangDTO hang) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO brands (brand_name, status) VALUES (?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setString(1, hang.getBrand_name());
            pstmt.setBoolean(2, hang.isStatus());

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

    public static boolean suaHang(HangDTO hang) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE brands SET brand_name = ?, status = ? WHERE brand_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setString(1, hang.getBrand_name());
            pstmt.setBoolean(2, hang.isStatus());
            pstmt.setInt(3, hang.getBrand_id());

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

    public static int getSoluongHang(){
        int count=0;
        try {
            connectDB.getConnection();
            String sql = "SELECT COUNT(*) AS count FROM brands";
            ResultSet rs= connectDB.runQuery(sql);
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return count;
    }

}
