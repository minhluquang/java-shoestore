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
            String sql = "SELECT * FROM categories";
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
            String sql = "SELECT * FROM categories WHERE category_id = " + category_id;
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
            String sql = "UPDATE categories SET status = false WHERE category_id = ?";
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
            String sql = "INSERT INTO categories (category_name, status) VALUES (?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setString(1, theLoai.getCategory_name());
            pstmt.setBoolean(2, theLoai.isStatus());

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
        connectDB.getConnection();
        try {
            
            String sql = "UPDATE categories SET category_name = ?, status = ? WHERE category_id = ?";
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
            String sql = "SELECT COUNT(*) AS count FROM categories";
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

    public static TheLoaiDTO getTheLoaiByName(String category_name) {
        TheLoaiDTO category = new TheLoaiDTO();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM categories WHERE category_name = " + category_name;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                int category_id = rs.getInt("category_id");
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
    
    public static boolean isExistIdTheLoai(int categoryId) {
    	boolean success = false;
    	
    	try {
            connectDB.getConnection();
            String sql = "SELECT * "
            		+ "FROM categories "
            		+ "WHERE category_id = " + categoryId;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return success;
	}
    public static boolean isExistNameTheLoai(String categoryName) {
        categoryName=categoryName.toLowerCase();
    	boolean success = false;
    	try {
            connectDB.getConnection();
            String sql = "SELECT * "
            		+ "FROM categories "
            		+ "WHERE category_name LIKE '"+categoryName+"'";
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return success;
	}

    public static ArrayList<TheLoaiDTO> searchLoai(String key, int trangThai) { 
        key=key.toLowerCase();
        ArrayList<TheLoaiDTO> theLoais = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM categories WHERE LOWER(category_name) LIKE '%"+key+"%'";
            if (trangThai!=-1) {
                sql+=" AND status="+trangThai;
            }
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                String category_name = rs.getString("category_name");
                boolean status = rs.getBoolean("status");

                TheLoaiDTO theLoai = new TheLoaiDTO(category_id, category_name, status);
             theLoais.add(theLoai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return theLoais;
    }


    public static int generateIdCate(){
        int id=0;
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM categories WHERE category_id = (SELECT MAX(category_id) FROM categories)";
            ResultSet rs= connectDB.runQuery(sql);
            if (rs.next()) {
                id = rs.getInt("category_id");
                id += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return id;
    }
}
