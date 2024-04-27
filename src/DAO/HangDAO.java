package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.HangDTO;

public class HangDAO {

    public static ArrayList<HangDTO> getDanhSachHang() {
        ArrayList<HangDTO> brands = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM brand";
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
            String sql = "SELECT * FROM brand WHERE brand_id = " + brand_id;
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
}
