package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ChiTietSanPhamDTO;

public class ChiTietSanPhamDAO {
    public static ArrayList<ChiTietSanPhamDTO> getDanhSachChiTietSanPham() {
        ArrayList<ChiTietSanPhamDTO> productDetails = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM product_details";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int productSerialId = rs.getInt("product_serial_id");
                int productId = rs.getInt("product_id");
                boolean sold = rs.getBoolean("sold");

                ChiTietSanPhamDTO productDetail = new ChiTietSanPhamDTO(productSerialId, productId, sold);
                productDetails.add(productDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return productDetails;
    }

    public static ChiTietSanPhamDTO getProductDetailsBySerial(int product_serial_id) {
        ChiTietSanPhamDTO productDetails = new ChiTietSanPhamDTO();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM product_details WHERE product_serial_id = " + product_serial_id;
            ResultSet rs = connectDB.runQuery(sql);
            if (rs.next()) {
                int product_id = rs.getInt("product_id");
                boolean sold = rs.getBoolean("sold");

                productDetails.setProductSerialId(product_serial_id);
                productDetails.setProductId(product_id);
                productDetails.setSold(sold);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return productDetails;
    }

    public static ArrayList<ChiTietSanPhamDTO> getProductDetailsByID(int product_id) {
        ArrayList<ChiTietSanPhamDTO> dsproductDetails = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM product_details WHERE product_id = " + product_id;
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int product_serial_id = rs.getInt("product_id");
                boolean sold = rs.getBoolean("sold");

                ChiTietSanPhamDTO productDetails = new ChiTietSanPhamDTO(product_serial_id, product_id, sold);

                dsproductDetails.add(productDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return dsproductDetails;
    }

    public static boolean xoaProductDetails(int product_serial_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "DELETE FROM product_details WHERE product_serial_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, product_serial_id);

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

    public static boolean themProductDetails(ChiTietSanPhamDTO productDetails) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO product_details (product_serial_id, product_id, sold) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, productDetails.getProductSerialId());
            pstmt.setInt(2, productDetails.getProductId());
            pstmt.setBoolean(3, false);

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

    public static boolean suaProductDetails(ChiTietSanPhamDTO productDetails) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE product_details SET product_id = ? sold = ? WHERE product_serial_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setInt(1, productDetails.getProductId());
            pstmt.setBoolean(2, productDetails.isSold());
            pstmt.setInt(3, productDetails.getProductSerialId());

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

    public static boolean danhDauDaBan(int product_details_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE product_details SET sold = ? WHERE product_serial_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);

            pstmt.setBoolean(1, true);
            pstmt.setInt(2, product_details_id);

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

    public static boolean danhDauDanhSachDaBan(ArrayList<ChiTietSanPhamDTO> chiTietSanPhamDTOs) {
        boolean flag = true;
        connectDB.con = null;
        try {
            connectDB.getConnection();
            connectDB.con.setAutoCommit(false);

            for (ChiTietSanPhamDTO i : chiTietSanPhamDTOs) {
                flag = danhDauDaBan(i.getProductSerialId());
                if (!flag) {
                    connectDB.con.rollback();
                    return false;
                }
            }

            connectDB.con.commit();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
            try {
                if (connectDB.con != null) {
                    connectDB.con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connectDB.con != null) {
                    connectDB.con.setAutoCommit(true);
                    connectDB.closeConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}
