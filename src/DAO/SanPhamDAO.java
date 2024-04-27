package DAO;

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
}
