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
            String sql = "SELECT * FROM product";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                int category_id = rs.getInt("category_id");
                int brand_id = rs.getInt("brand_id");
                String product_name = rs.getString("product_name");
                int output_price = rs.getInt("output_price");
                String country = rs.getString("country");
                int year_of_product = rs.getInt("year_of_product");
                int discount_percent = rs.getInt("discount_percent");
                String image_path = rs.getString("image_path");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");

                SanPhamDTO product = new SanPhamDTO(product_id, category_id, brand_id, product_name, output_price, country, year_of_product, discount_percent, image_path, quantity, status);
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
            String sql = "SELECT * FROM product WHEWE product_id = " + product_id;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                int category_id = rs.getInt("category_id");
                int brand_id = rs.getInt("brand_id");
                String product_name = rs.getString("product_name");
                int output_price = rs.getInt("output_price");
                String country = rs.getString("country");
                int year_of_product = rs.getInt("year_of_product");
                int discount_percent = rs.getInt("discount_percent");
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
				product.setDiscount_percent(discount_percent);
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
			String sql = "select product_name from product where product_id=" + product_id;
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
        boolean flag=true;
        try {
			connectDB.getConnection();
            String sql = "INSERT INTO product (product_id, category_id, brand_id, product_name, output_price, country, year_of_product, discount_percent, image_path, quantity, status) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, sanPham.getProduct_id());
            pstmt.setInt(2, sanPham.getCategory_id());
            pstmt.setInt(3, sanPham.getBrand_id());
            pstmt.setString(4, sanPham.getProduct_name());
            pstmt.setInt(5, sanPham.getOutput_price());
            pstmt.setString(6, sanPham.getCountry());
            pstmt.setInt(7, sanPham.getYear_of_product());
            pstmt.setInt(8, sanPham.getDiscount_percent());
            pstmt.setString(9, sanPham.getImage_path());
            pstmt.setInt(10, sanPham.getQuantity());
            pstmt.setBoolean(11, sanPham.isStatus());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                flag = false; 
            }
        } catch (Exception e) {
            flag=false;
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
            String sql = "UPDATE product SET status = false WHERE product_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
    
            // Set the product_id parameter in the PreparedStatement
            pstmt.setInt(1, product_id);
    
            // Execute the update operation
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                flag = false; // No rows affected means deletion failed
            }
        } catch (Exception e) {
            flag=false;
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return flag;
    }

}
