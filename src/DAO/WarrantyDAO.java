package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import DTO.Warranty;

public class WarrantyDAO {
    public static ArrayList<Warranty> getDanhSachWarranty() {
        connectDB.getConnection();
        ArrayList<Warranty> dswt = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM `warranty_details` ";
            statement = connectDB.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Warranty wt = new Warranty();
                wt.setWarrantyid(resultSet.getInt("warranty_detail_id"));
                wt.setProduct_serial_id(resultSet.getInt("product_serial_id"));
                wt.setWarrantyDate(resultSet.getString("warranty_date"));
                wt.setReason(resultSet.getString("reason"));
                wt.setActive(resultSet.getString("active"));
                wt.setStatus(resultSet.getInt("status"));
                dswt.add(wt);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return dswt;
    }
    public static ArrayList<Warranty> searchWarranty(String keyword, int status) {
        connectDB.getConnection();
        ArrayList<Warranty> dswt = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `warranty_details` WHERE (warranty_detail_id LIKE '%" + keyword + "%' OR product_serial_id LIKE '%" + keyword + "%' OR warranty_date LIKE '%" + keyword + "%' OR reason LIKE '%" + keyword + "%')";
            sql += " OR active LIKE '%" + keyword + "%'";
            if (status != -1) {
                // Thêm khoảng trắng sau phần điều kiện trước khi thêm phần điều kiện về trạng thái
                sql += " AND status = '" + (status == 1 ? "1" : "0") + "'";
            }
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                Warranty wt = new Warranty();
                wt.setWarrantyid(rs.getInt("warranty_detail_id"));
                wt.setProduct_serial_id(rs.getInt("product_serial_id"));
                wt.setWarrantyDate(rs.getString("warranty_date"));
                wt.setReason(rs.getString("reason"));
                wt.setActive(rs.getString("active"));
                wt.setStatus(rs.getInt("status"));
                dswt.add(wt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return dswt;
    }
    public static int generateIdWar(boolean closeDatabase) {
        connectDB.getConnection();
        int idWar = 0;
        try {
            String sql = "SELECT `warranty_detail_id` FROM `warranty_details` ORDER BY warranty_detail_id DESC LIMIT 1";
            ResultSet rs = connectDB.runQuery(sql);
            while(rs.next()) {
                int lastId = rs.getInt("warranty_detail_id");
                idWar = lastId + 1;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (closeDatabase) {
            connectDB.closeConnection();            
        }
        return idWar;
    }
    public static boolean isExitWar(int id) {
        connectDB.getConnection();
        boolean isExist = false;
        try {
            String sql = "SELECT * FROM `warranty_details` WHERE warranty_detail_id = " + id;
             ResultSet rs = connectDB.runQuery(sql); 
             if (rs.next()) {
                 isExist = true;
              } 
        } catch (Exception e) {
            e.printStackTrace();
        }        
        connectDB.closeConnection();
        return isExist;
    }
    public static boolean insertWar(int warranty_detail_id, int product_serial_id,String warranty_date,String reason,String active,int status) {
        connectDB.getConnection();
        boolean success = false;
        try {
        	String sqlBill_id = "select * from bills_details where product_serial_id = ?";
        	PreparedStatement ps_bill_id = connectDB.prepareStatement(sqlBill_id);
        	ps_bill_id.setInt(1, product_serial_id);
        	ResultSet rs_bill_id = ps_bill_id.executeQuery();
        	String sql_bill_date = "select * from bills where bill_id = ?";
        	PreparedStatement ps_bill_date =  connectDB.prepareStatement(sql_bill_date);
        	
   	        if (rs_bill_id.next()) {  	        	
   	        	int bill_id = rs_bill_id.getInt("bill_id");
   	        	
   	        	ps_bill_date.setInt(1, bill_id);
  	            ResultSet rs_bill_date = ps_bill_date.executeQuery();
  	            
  	          String date_created ="";
  	            if (rs_bill_date.next()) {
  	            	 date_created = rs_bill_date.getString("date");
				}
  	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  	            Date createDate = sdf.parse(date_created);
  	            Date returnDate = sdf.parse(warranty_date);
  	            System.out.println(warranty_date);
  	            System.out.println(date_created);
  	          long diffInMillies = Math.abs(returnDate.getTime() - createDate.getTime());
              long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
              
              // Kiểm tra nếu số ngày giữa date_return và date_created lớn hơn 30 ngày
              if (diffInDays > 30) {
                  JOptionPane.showMessageDialog(null, "Quá thời hạn đổi trả (quá 30 ngày kể từ ngày mua).", "Lỗi", JOptionPane.ERROR_MESSAGE);
                  return false;
              }
              // Tiến hành insert vào CSDL
              String sql = "INSERT INTO `warranty_details` (warranty_detail_id,product_serial_id,warranty_date,reason,active,status) VALUES (" + warranty_detail_id + ", '" + product_serial_id + "' , '" + warranty_date + "', '" + reason + "', '" + active + "', '" + status + "')";
              int i = connectDB.runUpdate(sql);
              if (i > 0) {
                  success = true;
                  JOptionPane.showMessageDialog(null, "Thêm đổi trả thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
              } else {
                  JOptionPane.showMessageDialog(null, "Lỗi khi thêm đổi trả.", "Lỗi", JOptionPane.ERROR_MESSAGE);
              }      
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return success;
    }  
    public static boolean updateWar(int warranty_detail_id, int product_serial_id, String warranty_date, String reason,String active, int status) {
        connectDB.getConnection();
        boolean success = false;
        try {
            // Lấy ngày bảo hành hiện tại từ CSDL
            String sqlCurrentDate = "SELECT warranty_date FROM `warranty_details` WHERE warranty_detail_id = ?";
            PreparedStatement psCurrentDate = connectDB.prepareStatement(sqlCurrentDate);
            psCurrentDate.setInt(1, warranty_detail_id);
            ResultSet rsCurrentDate = psCurrentDate.executeQuery();
            String currentWarrantyDate = "";
            if (rsCurrentDate.next()) {
                currentWarrantyDate = rsCurrentDate.getString("warranty_date");
            }
            psCurrentDate.close();

            // Chuyển đổi chuỗi thành đối tượng Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = sdf.parse(currentWarrantyDate);
            Date newDate = sdf.parse(warranty_date);

            // Kiểm tra nếu warranty_date mới bé hơn giá trị hiện tại
            if (newDate.compareTo(currentDate) < 0) {
                JOptionPane.showMessageDialog(null, "Ngày bảo hành mới không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                connectDB.closeConnection();
                return false;
            }
            
            // Kiểm tra xem ngày bảo hành mới có vượt quá thời gian bảo hành đã quy định hay không
            String sqlBill_id = "SELECT * FROM bills_details WHERE product_serial_id = ?";
            PreparedStatement ps_bill_id = connectDB.prepareStatement(sqlBill_id);
            ps_bill_id.setInt(1, product_serial_id);
            ResultSet rs_bill_id = ps_bill_id.executeQuery();
            String sql_bill_date = "SELECT * FROM bills WHERE bill_id = ?";
            PreparedStatement ps_bill_date = connectDB.prepareStatement(sql_bill_date);
          
            if (rs_bill_id.next()) {             
                int bill_id = rs_bill_id.getInt("bill_id");
                
                ps_bill_date.setInt(1, bill_id);
                ResultSet rs_bill_date = ps_bill_date.executeQuery();
                
                String date_created ="";
                if (rs_bill_date.next()) {
                     date_created = rs_bill_date.getString("date");
                }
                Date createDate = sdf.parse(date_created);
                Date returnDate = newDate; // Đây là ngày bảo hành mới

                long diffInMillies = Math.abs(returnDate.getTime() - createDate.getTime());
                long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                
                if (diffInDays > 30) {
                    JOptionPane.showMessageDialog(null, "Ngày bảo hành mới vượt quá thời gian bảo hành đã quy định.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    connectDB.closeConnection();
                    return false;
                }
            }

            // Tiến hành cập nhật dữ liệu
            String sql = "UPDATE `warranty_details` SET product_serial_id = ?, warranty_date = ?, reason = ?,active  = ?, status = ? WHERE warranty_detail_id = ?";
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setInt(1, product_serial_id);
            statement.setString(2, warranty_date);
            statement.setString(3, reason);
            statement.setString(4, active);
            statement.setInt(5, status);
            statement.setInt(6, warranty_detail_id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
                JOptionPane.showMessageDialog(null, "Cập nhật bảo hành thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật bảo hành.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return success;
    }


    public static boolean deleteWar(int warranty_detail_id) {
        connectDB.getConnection();
        boolean success = false;
        try {
        	String sql = "UPDATE warranty_details SET status = 0 WHERE warranty_detail_id = ?";
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setInt(1,warranty_detail_id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
                updateNextWarId(warranty_detail_id);
            }
            statement.close();
            connectDB.closeConnection();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return success;
    }
    
    
    
    public static void updateNextWarId(int deleteWarId) {
        connectDB.getConnection();
        try {
            String sql = "SELECT MAX(warranty_detail_id) AS max_id FROM `warranty_details` WHERE warranty_detail_id < ?";
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setInt(1, deleteWarId);
            ResultSet resultSet = statement.executeQuery();
            int nextWarId = 1;
            if (resultSet.next()) {
                nextWarId = resultSet.getInt("max_id") + 1;
            }
            sql = "ALTER TABLE `warranty_details` AUTO_INCREMENT = ?";
            statement = connectDB.prepareStatement(sql);
            statement.setInt(1, nextWarId);
            statement.executeUpdate();
            resultSet.close();
            statement.close();
            connectDB.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Warranty getWarById(int warranty_detail_id) {
        connectDB.getConnection();
        Warranty wt = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;  
       try {
          String sql = "SELECT * FROM `warranty_details` WHERE warranty_detail_id = ?";
          statement = connectDB.prepareStatement(sql);
          statement.setInt(1,warranty_detail_id);
          resultSet = statement.executeQuery();
          if(resultSet.next()) {
             wt = new Warranty();
                wt.setWarrantyid(resultSet.getInt("warranty_detail_id"));
                wt.setProduct_serial_id(resultSet.getInt("product_serial_id"));
                wt.setWarrantyDate(resultSet.getString("warranty_date"));
                wt.setReason(resultSet.getString("reason"));
                wt.setActive(resultSet.getString("active"));
                wt.setStatus(resultSet.getInt("status"));
          }
       } catch (SQLException e) {
            e.printStackTrace();
        }
       connectDB.closeConnection();
       return wt;
    }
}
