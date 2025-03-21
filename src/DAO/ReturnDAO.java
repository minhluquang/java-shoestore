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

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import BUS.ChiTietSanPhamBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietSanPhamDTO;
import DTO.Return;
import DTO.SanPhamDTO;


public class ReturnDAO {
    public static ArrayList<Return> getDanhSachReturn() {
    	connectDB.getConnection();
        ArrayList<Return> dsReturn = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT return_id, product_serial_id, date_return, reason, active , status FROM `returns`";
            statement = connectDB.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Return rt = new Return();
                rt.setReturn_id(resultSet.getInt("return_id"));
                rt.setProduct_serial_id(resultSet.getInt("product_serial_id"));
                rt.setDate_return(resultSet.getString("date_return"));
                rt.setReason(resultSet.getString("reason"));
                rt.setActive(resultSet.getString("active"));
                rt.setStatus(resultSet.getInt("status"));
                dsReturn.add(rt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectDB.closeConnection();
        return dsReturn;
    }
    public static int generateIdReturn(boolean closeDatabase) {
    	connectDB.getConnection();
        int idReturn = 0;
        try {
        	String sql = "SELECT return_id FROM `returns` ORDER BY return_id DESC LIMIT 1";
            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                int lastId = rs.getInt("return_id");
                idReturn = lastId + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (closeDatabase) {
			connectDB.closeConnection();			
		}
        return idReturn;
    }
    public static boolean isExistReturn(int id) {
    	connectDB.getConnection();
        boolean isExist = false;        
        try {
            String sql = "SELECT * FROM `returns` WHERE return_id = " + id;
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
  
    
    public static ArrayList<Return> searchReturn(String keyword, int status) {
        connectDB.getConnection();
        ArrayList<Return> dsReturn = new ArrayList<>();
        try {
        	String sql = "SELECT * FROM `returns` WHERE (return_id LIKE '%" + keyword + "%' OR reason LIKE '%" + keyword + "%' OR date_return LIKE '%" + keyword + "%' OR product_serial_id LIKE '%" + keyword + "%'" + " OR active LIKE '%" + keyword + "%')";
            if (status != -1) {
                sql += " AND status = " + status;
            }

            ResultSet rs = connectDB.runQuery(sql);
            while (rs.next()) {
                Return rt = new Return();
                rt.setReturn_id(rs.getInt("return_id"));
                rt.setProduct_serial_id(rs.getInt("product_serial_id"));
                rt.setDate_return(rs.getString("date_return"));
                rt.setReason(rs.getString("reason"));
                rt.setActive(rs.getString("active"));
                rt.setStatus(rs.getInt("status"));
                dsReturn.add(rt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        connectDB.closeConnection();
        return dsReturn;
    }

    // insert
    public static boolean insertReturn(int return_id, int product_serial_id, String date_return, String reason, String active, int status, boolean noJOption) {
        connectDB.getConnection();
        boolean success = false;
        try {     
        	String sqlBill_id = "select * from bills_details where product_serial_id = ?";
    		PreparedStatement ps_bill_id = connectDB.prepareStatement(sqlBill_id);
    		ps_bill_id.setInt(1, product_serial_id);
  	        ResultSet rs_bill_id = ps_bill_id.executeQuery();
  	        String sql_bill_date = "select * from bills where bill_id = ?";
  	        PreparedStatement ps_bill_date =  connectDB.prepareStatement(sql_bill_date);
  	        
  	       String sqlCheckReturn = "SELECT * FROM returns WHERE product_serial_id = ?";
           PreparedStatement ps_checkReturn = connectDB.prepareStatement(sqlCheckReturn);
           ps_checkReturn.setInt(1, product_serial_id);
           ResultSet rs_checkReturn = ps_checkReturn.executeQuery();
           if (rs_checkReturn.next()) {
              if (!noJOption) {
                  JOptionPane.showMessageDialog(null, "Sản phẩm này đã được đổi trả trước đó.", "Lỗi", JOptionPane.ERROR_MESSAGE);
              }
              return false; // Không cho phép thêm đổi trả mới
           }
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
  	            Date returnDate = sdf.parse(date_return);

  	          long diffInMillies = Math.abs(returnDate.getTime() - createDate.getTime());
              long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
              System.out.println("date" + date_created);
              // Kiểm tra nếu số ngày giữa date_return và date_created lớn hơn 7 ngày
              if (diffInDays > 7) {
            	  if (!noJOption) {
            		  JOptionPane.showMessageDialog(null, "Quá thời hạn đổi trả (quá 7 ngày kể từ ngày mua).", "Lỗi", JOptionPane.ERROR_MESSAGE);            		  
            	  }
                  return false;
              }
              ChiTietSanPhamDTO chiTietSanPhamDTO = ChiTietSanPhamBUS.getChiTietSanPhamBySerial(product_serial_id);
              if(!SanPhamBUS.kiemTraTonKhoByID(chiTietSanPhamDTO.getProductId())) {
            	  if (!noJOption) {            		  
            		  JOptionPane.showMessageDialog(null, "Hiện tại đã hết sản phẩm đổi trả", "Lỗi", JOptionPane.ERROR_MESSAGE);
            	  }
            	  active = "NO";
              }
              // Tiến hành insert vào CSDL
              String sql = "INSERT INTO `returns` (return_id, product_serial_id, date_return, reason, active, status) VALUES (" + return_id + ", '" + product_serial_id + "', '" + date_return + "', '" + reason + "','" + active + "', " + status + ")";
              System.out.println(sql); 
              connectDB.getConnection();
              int i = connectDB.runUpdate(sql);
              if (i > 0) {
                  success = true;
                  if (!active.equals("NO")) {
                      SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
                      sanPhamDTO.setQuantity(sanPhamDTO.getQuantity() - 1);
                      ChiTietSanPhamDTO chiTietSanPhamDTO2 = ChiTietSanPhamBUS.getChiTietSanPhamByProductIDLimit1(sanPhamDTO.getProduct_id());
                      ChiTietSanPhamBUS.danhDauDaBan(chiTietSanPhamDTO2.getProductSerialId());
                      SanPhamBUS.suaSanPham(sanPhamDTO);
                  }
                  if (!noJOption) {                	  
                	  JOptionPane.showMessageDialog(null, "Thêm đổi trả thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                  }
              } else {
            	  if (!noJOption) {
            		  JOptionPane.showMessageDialog(null, "Lỗi khi thêm đổi trả.", "Lỗi", JOptionPane.ERROR_MESSAGE);            		  
            	  }
              }   
        }
   	   } catch (Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return success;
    }
    
    public static boolean isExistProductSerialId(int productSerialId) {
    	connectDB.getConnection();
    	boolean success = false;
    	
    	try {
			String sql = "SELECT * "
					+ "FROM returns "
					+ "WHERE product_serial_id = " + productSerialId;
			ResultSet rs= connectDB.runQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	
    	connectDB.closeConnection();
    	return success;
    }
    
    
    public static boolean insertDanhSachDoiTra(ArrayList<Return> dsdt) {
    	connectDB.getConnection();
        boolean success = true;
        try {
			for (Return dt : dsdt) {
				int returnId = generateIdReturn(true);
				int productSerialId = dt.getProduct_serial_id();
				String dateReturn = dt.getDate_return();
				String reason = dt.getReason();
				
				if (isExistProductSerialId(productSerialId)) {
					continue;
				}
				
				insertReturn(returnId, productSerialId, dateReturn, reason, "OK", 1, true);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        connectDB.closeConnection();
        return success;
    }


    
	// tìm id sau khi click
	public static Return getReturnById(int return_id) {
		connectDB.getConnection();
	    Return rt = null;
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;   	    
	    try {
	        String sql = "SELECT * FROM `returns` WHERE return_id = ?";
	        statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, return_id);
	        resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	        	rt = new Return();
	        	 rt.setReturn_id(resultSet.getInt("return_id"));
	        	 rt.setProduct_serial_id(resultSet.getInt("product_serial_id"));
	             rt.setDate_return(resultSet.getString("date_return"));
	             rt.setReason(resultSet.getString("reason"));
	             rt.setActive(resultSet.getString("active"));
	             rt.setStatus(resultSet.getInt("status"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }    	    
	    connectDB.closeConnection();
	    return rt;
	}
	// delete
	public static boolean deleteReturn(int return_id) {
		connectDB.getConnection();
	    boolean success = false;
	    try {	       
	        String sql = "UPDATE returns SET status = 0 WHERE return_id = ?";   	        
	        PreparedStatement statement = connectDB.prepareStatement(sql);   	        	       
	        statement.setInt(1, return_id);    	        
	        // Thực thi câu lệnh SQL
	        int rowsDeleted = statement.executeUpdate();
	        // Kiểm tra xem có bản ghi nào bị xóa không
	        if (rowsDeleted > 0) {
	            success = true;
	            // Cập nhật lại giá trị role_id cho bản ghi tiếp theo
	            updateNextReturnId(return_id);
	        }
	        // Đóng kết nối và tài nguyên
	        statement.close();
	        connectDB.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    connectDB.closeConnection();
	    return success;
	}
	// update role_id sau khi xóa data đi
	public static void updateNextReturnId(int deletedReturnId) {
		connectDB.getConnection();
	    try {
	        // Tìm role_id lớn nhất mà nhỏ hơn deletedRoleId
	        String sql = "SELECT MAX(return_id) AS max_id FROM `returns` WHERE return_id < ?";
	        PreparedStatement statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, deletedReturnId);
	        ResultSet resultSet = statement.executeQuery();
	        int nextRoleId = 1; // Giá trị mặc định nếu không tìm thấy role_id nào nhỏ hơn
	        if (resultSet.next()) {
	            nextRoleId = resultSet.getInt("max_id") + 1;
	        }
	        // Thiết lập role_id tiếp theo
	        sql = "ALTER TABLE `returns` AUTO_INCREMENT = ?";
	        statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, nextRoleId);
	        statement.executeUpdate();
	        // Đóng kết nối và tài nguyên
	        resultSet.close();
	        statement.close();
	        connectDB.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static boolean updateReturn(int return_id, int product_serial_id, String date_return, String reason, String active, int status) {
	    connectDB.getConnection();
	    boolean success = false;
	    try {

	        // Lấy ngày bảo hành hiện tại từ CSDL
	        String sqlCurrentDate = "SELECT date_return FROM `returns` WHERE return_id = ?";
	        PreparedStatement psCurrentDate = connectDB.prepareStatement(sqlCurrentDate);
	        psCurrentDate.setInt(1, return_id);
	        ResultSet rsCurrentDate = psCurrentDate.executeQuery();
	        String currentWarrantyDate = "";
	        if (rsCurrentDate.next()) {
	            currentWarrantyDate = rsCurrentDate.getString("date_return");
	        }

	        // Chuyển đổi chuỗi thành đối tượng Date
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date currentDate = sdf.parse(currentWarrantyDate);
	        Date newDate = sdf.parse(date_return);

	        // Kiểm tra nếu warranty_date mới bé hơn giá trị hiện tại
	        if (newDate.compareTo(currentDate) < 0) {
	            JOptionPane.showMessageDialog(null, "Ngày bảo hành mới không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        // Kiểm tra xem ngày bảo hành mới có vượt quá thời gian bảo hành đã quy định hay không
	        String sqlBillDetails = "SELECT bill_id FROM bills_details WHERE product_serial_id = ?";
	        PreparedStatement psBillDetails = connectDB.prepareStatement(sqlBillDetails);
	        psBillDetails.setInt(1, product_serial_id);
	        ResultSet rsBillDetails = psBillDetails.executeQuery();
	        
	        if (rsBillDetails.next()) {             
	            int bill_id = rsBillDetails.getInt("bill_id");

	            String sqlBillDate = "SELECT date FROM bills WHERE bill_id = ?";
	            PreparedStatement psBillDate = connectDB.prepareStatement(sqlBillDate);
	            psBillDate.setInt(1, bill_id);
	            ResultSet rsBillDate = psBillDate.executeQuery();
	            
	            if (rsBillDate.next()) {
	                String date_created = rsBillDate.getString("date");
	                Date createDate = sdf.parse(date_created);
	                Date returnDate = newDate; // Đây là ngày bảo hành mới

	                long diffInMillies = Math.abs(returnDate.getTime() - createDate.getTime());
	                long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	                
	                if (diffInDays > 7) {
	                    JOptionPane.showMessageDialog(null, "Ngày đổi trả mới vượt quá thời gian bảo hành đã quy định.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return false;
	                }
	            }
	        }

	        // Cập nhật thông tin trả hàng
	        String sqlUpdateReturn = "UPDATE `returns` SET product_serial_id = ?, date_return = ?, reason = ?, active = ?, status = ? WHERE return_id = ?";
	        PreparedStatement psUpdateReturn = connectDB.prepareStatement(sqlUpdateReturn);
	        psUpdateReturn.setInt(1, product_serial_id);
	        psUpdateReturn.setString(2, date_return);
	        psUpdateReturn.setString(3, reason);
	        psUpdateReturn.setString(4, active);
	        psUpdateReturn.setInt(5, status);
	        psUpdateReturn.setInt(6, return_id);
	        
	        int rowsUpdated = psUpdateReturn.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            success = true;
	        }
	    }catch (Exception e) {
            e.printStackTrace();
        }
        connectDB.closeConnection();
        return success;
	}
}
