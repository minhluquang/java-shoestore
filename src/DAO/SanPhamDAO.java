package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.SanPhamDTO;

public class SanPhamDAO {

    public static ArrayList<SanPhamDTO> getDanhSachSanPham() {
        ArrayList<SanPhamDTO> danhSachSanPham = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM products";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                int brand_id = rs.getInt("brand_id");
                String product_name = rs.getString("product_name");
                int output_price = rs.getInt("output_price");
                String country = rs.getString("country");
                int year_of_product = rs.getInt("year_of_product");
                String image_path = rs.getString("image_path");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");

                SanPhamDTO product = new SanPhamDTO(product_id, category_id, brand_id, product_name, output_price,
                        country, year_of_product, image_path, quantity, status);
                danhSachSanPham.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return danhSachSanPham;
    }

    public static SanPhamDTO getSanPhamByID(int product_id) {
        SanPhamDTO product = new SanPhamDTO();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM products WHERE product_id = " + product_id;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                int category_id = rs.getInt("category_id");
                int brand_id = rs.getInt("brand_id");
                String product_name = rs.getString("product_name");
                int output_price = rs.getInt("output_price");
                String country = rs.getString("country");
                int year_of_product = rs.getInt("year_of_product");                
                String image_path = rs.getString("image_path");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                product.setProduct_id(product_id);
                product.setCategory_id(category_id);
                product.setBrand_id(brand_id);
                product.setProduct_name(product_name);
                product.setOutput_price(output_price);
                product.setCountry(country);
                product.setYear_of_product(year_of_product);
                product.setImage_path(image_path);
                product.setQuantity(quantity);
                product.setStatus(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return product;
    }

    public static String getTenSanPhamById(int product_id) {
        String tenSanPham = "";
        try {
            connectDB.getConnection();
            String sql = "select product_name from products where product_id=" + product_id;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                tenSanPham = rs.getString("product_name");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return tenSanPham;
    }

    public static boolean themSanPham(SanPhamDTO sanPham) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO products (category_id, brand_id, product_name, output_price, country, year_of_product, image_path, quantity, status) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, sanPham.getCategory_id());
            pstmt.setInt(2, sanPham.getBrand_id());
            pstmt.setString(3, sanPham.getProduct_name());
            pstmt.setInt(4, sanPham.getOutput_price());
            pstmt.setString(5, sanPham.getCountry());
            pstmt.setInt(6, sanPham.getYear_of_product());
            pstmt.setString(7, sanPham.getImage_path());
            pstmt.setInt(8, sanPham.getQuantity());
            pstmt.setBoolean(9, sanPham.isStatus());

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

    public static boolean xoaSanPham(int product_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE products SET status = false WHERE product_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            // Set the product_id parameter in the PreparedStatement
            pstmt.setInt(1, product_id);

            // Execute the update operation
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                flag = false; // No rows affected means deletion failed
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return flag;
    }

    public static boolean suaSanPham(SanPhamDTO sanPham) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE products SET category_id = ?, brand_id = ?, product_name = ?, output_price = ?, country = ?, "
                    +
                    "year_of_product = ?, image_path = ?, quantity = ?, status = ? " +
                    "WHERE product_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, sanPham.getCategory_id());
            pstmt.setInt(2, sanPham.getBrand_id());
            pstmt.setString(3, sanPham.getProduct_name());
            pstmt.setInt(4, sanPham.getOutput_price());
            pstmt.setString(5, sanPham.getCountry());
            pstmt.setInt(6, sanPham.getYear_of_product());
            pstmt.setString(7, sanPham.getImage_path());
            pstmt.setInt(8, sanPham.getQuantity());
            pstmt.setBoolean(9, sanPham.isStatus());
            pstmt.setInt(10, sanPham.getProduct_id());

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

    public static int getSoluongSanPham(){
        int count=0;
        try {
            connectDB.getConnection();
            String sql = "SELECT COUNT(*) AS count FROM products";
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

    public static ArrayList<SanPhamDTO> searchDanhSachSanPham(int hang, int loai, String name) {
        ArrayList<SanPhamDTO> danhSachSanPham = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM products WHERE product_name LIKE '%"+name+"%'";
            if (hang!=-1) {
                sql+=" AND brand_id="+hang;
            }
            if (loai!=1) {
                sql+=" AND category_id="+loai;
            }
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                int brand_id = rs.getInt("brand_id");
                String product_name = rs.getString("product_name");
                int output_price = rs.getInt("output_price");
                String country = rs.getString("country");
                int year_of_product = rs.getInt("year_of_product");
                String image_path = rs.getString("image_path");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");

                SanPhamDTO product = new SanPhamDTO(product_id, category_id, brand_id, product_name, output_price,
                        country, year_of_product, image_path, quantity, status);
                danhSachSanPham.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return danhSachSanPham;
    }

}
