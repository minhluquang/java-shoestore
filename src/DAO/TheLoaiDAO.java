package DAO;

import java.sql.PreparedStatement;
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

    public static boolean xoaTheLoai(int category_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE category SET status = false WHERE category_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, category_id);

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

    public static boolean themTheLoai(TheLoaiDTO theLoai) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO category (category_id, category_name, status) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, theLoai.getCategory_id());
            pstmt.setString(2, theLoai.getCategory_name());
            pstmt.setBoolean(3, theLoai.isStatus());

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

    public static boolean suaTheLoai(TheLoaiDTO theLoai) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE category SET category_name = ?, status = ? WHERE category_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setString(1, theLoai.getCategory_name());
            pstmt.setBoolean(2, theLoai.isStatus());
            pstmt.setInt(3, theLoai.getCategory_id());

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

    public static int getSoluongTheLoai(){
        int count=0;
        try {
            connectDB.getConnection();
            String sql = "SELECT COUNT(*) AS count FROM category";
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
