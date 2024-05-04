package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

import DTO.HoaDonDTO;

public class HoaDonDAO {
    public static ArrayList<HoaDonDTO> getDanhSachHoaDon() {
        ArrayList<HoaDonDTO> bills = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM bills ORDER BY bill_id DESC";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int bill_id = rs.getInt("bill_id");
                int staff_id = rs.getInt("staff_id");
                Date date = rs.getDate("date");
                int total_price = rs.getInt("total_price");
                String address = rs.getString("address");
                int customer_id = rs.getInt("customer_id");
                String discount_code = rs.getString("discount_code");

                HoaDonDTO bill = new HoaDonDTO(bill_id, staff_id, date, total_price, address, customer_id,
                        discount_code);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return bills;
    }

    public static HoaDonDTO getBillByID(int bill_id) {
        HoaDonDTO bill = new HoaDonDTO();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM bills WHERE bill_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, bill_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int staff_id = rs.getInt("staff_id");
                String date = rs.getString("date");
                int total_price = rs.getInt("total_price");
                String address = rs.getString("address");
                int customer_id = rs.getInt("customer_id");
                String discount_code = rs.getString("discount_code");

                bill.setBillId(bill_id);
                bill.setStaffId(staff_id);
                bill.setDate(Date.valueOf(date));
                bill.setTotalPrice(total_price);
                bill.setAddress(address);
                bill.setCustomerId(customer_id);
                bill.setDiscountCode(discount_code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return bill;
    }

    public static boolean deleteBill(int bill_id) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "DELETE FROM bills WHERE bill_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, bill_id);
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

    public static boolean insertBill(HoaDonDTO bill) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "INSERT INTO bills (staff_id, date, total_price, address, customer_id, discount_code, bill_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, bill.getStaffId());
            pstmt.setString(2, bill.getDate().toString());
            pstmt.setInt(3, bill.getTotalPrice());
            pstmt.setString(4, bill.getAddress());
            pstmt.setInt(5, bill.getCustomerId());
            pstmt.setString(6, bill.getDiscountCode());
            pstmt.setInt(7, bill.getBillId());
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

    public static boolean updateBill(HoaDonDTO bill) {
        boolean flag = true;
        try {
            connectDB.getConnection();
            String sql = "UPDATE bills SET staff_id = ?, date = ?, total_price = ?, address = ?, customer_id = ?, discount_code = ? WHERE bill_id = ?";
            PreparedStatement pstmt = connectDB.prepareStatement(sql);
            pstmt.setInt(1, bill.getStaffId());
            pstmt.setString(2, bill.getDate().toString());
            pstmt.setInt(3, bill.getTotalPrice());
            pstmt.setString(4, bill.getAddress());
            pstmt.setInt(5, bill.getCustomerId());
            pstmt.setString(6, bill.getDiscountCode());
            pstmt.setInt(7, bill.getBillId());
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

    public static int getSoLuongBill(){
        int count=0;
        try {
            connectDB.getConnection();
            String sql = "SELECT COUNT(*) AS count FROM bills";
            ResultSet rs = connectDB.runQuery(sql);
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

    public static ArrayList<HoaDonDTO> searchDanhSachHoaDon(int totalMin, int totalMax, int MaKH, int MaNV, Date dateStart, Date dateEnd) {
        ArrayList<HoaDonDTO> bills = new ArrayList<>();
        try {
            connectDB.getConnection();
            String sql = "SELECT * FROM bills WHERE date >= '"+dateStart+"' AND date <= '"+dateEnd+"'";
            if(totalMin!=-1){
                sql+=" AND total_price >= "+totalMin;
            }
            if(totalMax!=-1){
                sql+=" AND total_price <= "+totalMax;
            }
            if(MaKH!=-1){
                sql+=" AND customer_id = "+MaKH;
            }
            if(MaNV!=-1){
                sql+=" AND staff_id = "+MaNV;
            }
            sql+=" ORDER BY bill_id DESC";
            System.out.println(sql);
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int bill_id = rs.getInt("bill_id");
                int staff_id = rs.getInt("staff_id");
                Date date = rs.getDate("date");
                int total_price = rs.getInt("total_price");
                String address = rs.getString("address");
                int customer_id = rs.getInt("customer_id");
                String discount_code = rs.getString("discount_code");

                HoaDonDTO bill = new HoaDonDTO(bill_id, staff_id, date, total_price, address, customer_id,
                        discount_code);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.closeConnection();
        }
        return bills;
    }

}
