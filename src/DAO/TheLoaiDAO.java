package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.TheLoaiDTO;

public class TheLoaiDAO {
    // Phương thức để lấy danh sách tất cả các danh mục từ cơ sở dữ liệu
    public static ArrayList<TheLoaiDTO> getDanhSachTheLoai() {
        ArrayList<TheLoaiDTO> danhSachCategory = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM category";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                String category_name = rs.getString("category_name");
                boolean status = rs.getBoolean("status");

                TheLoaiDTO category = new TheLoaiDTO(category_id, category_name, status);
                danhSachCategory.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return danhSachCategory;
    }

    // Phương thức để lấy thông tin về một danh mục dựa trên ID
    public static TheLoaiDTO getTheLoaiByID(int category_id) {
        TheLoaiDTO category = new TheLoaiDTO();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM category WHERE category_id = " + category_id;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                String category_name = rs.getString("category_name");
                boolean status = rs.getBoolean("status");

                category.setCategory_id(category_id);
                category.setCategory_name(category_name);
                category.setStatus(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return category;
    }
}
