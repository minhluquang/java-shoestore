package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Warranty;

public class WarrantyDAO {
	public static ArrayList<Warranty> getDanhSachWarranty() {
		connectDB.getConnection();
		ArrayList<Warranty> dswt = new ArrayList<>();
		PreparedStatement statement = null;
        ResultSet resultSet = null;
		try {
			String sql = "SELECT * FROM `warrantydetail` ";
			statement = connectDB.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Warranty wt = new Warranty();
				wt.setWarrantyid(resultSet.getInt("warranty_detail_id"));
				wt.setProductid(resultSet.getInt("product_id"));
				wt.setStartDate(resultSet.getString("start_date"));
				wt.setEndDate(resultSet.getString("end_date"));
				wt.setWarrantyDate(resultSet.getString("warranty_date"));
				wt.setReason(resultSet.getString("reason"));
				wt.setWarrantyStatus(resultSet.getString("warranty_status"));
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
	        String sql = "SELECT * FROM `warrantydetail` WHERE (warranty_detail_id LIKE '%" + keyword + "%' OR product_id LIKE '%" + keyword + "%' OR warranty_date LIKE '%" + keyword + "%' OR start_date LIKE '%" + keyword + "%' OR end_date LIKE '%" + keyword + "%' OR reason LIKE '%" + keyword + "%')";
	        if (status != -1) {
	            // Thêm khoảng trắng sau phần điều kiện trước khi thêm phần điều kiện về trạng thái
	            sql += " AND warranty_status = '" + (status == 1 ? "Complete" : "Non Complete") + "'";
	        }
	        ResultSet rs = connectDB.runQuery(sql);
	        while (rs.next()) {
	            Warranty wt = new Warranty();
	            wt.setWarrantyid(rs.getInt("warranty_detail_id"));
	            wt.setProductid(rs.getInt("product_id"));
	            wt.setStartDate(rs.getString("start_date"));
	            wt.setEndDate(rs.getString("end_date"));
	            wt.setWarrantyDate(rs.getString("warranty_date"));
	            wt.setReason(rs.getString("reason"));
	            wt.setWarrantyStatus(rs.getString("warranty_status"));
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
			String sql = "SELECT warranty_detail_id FROM `warrantydetail` ORDER BY warranty_detail_id DESC LIMIT 1";
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
			String sql = "SELECT * FROM `warrantydetail` WHERE warranty_detail_id = " + id;
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
	public static boolean insertWar(int warranty_detail_id, int product_id,String start_date,String end_date,String warranty_date,String reason,String warranty_status) {
		connectDB.getConnection();
		boolean success = false;
		try {
			String sql = "INSERT INTO `warrantydetail` (warranty_detail_id,product_id,start_date,end_date,warranty_date,reason,warranty_status) VALUES (" + warranty_detail_id + ", '" + product_id + "' , '" + start_date + "', '" + end_date + "', '" + warranty_date + "', '" + reason + "', '" + warranty_status + "')";
			int i = connectDB.runUpdate(sql);
			if(i>0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connectDB.closeConnection();
		return success;
	}
	public static boolean updateWar(int warranty_detail_id, int product_id, String start_date, String end_date, String warranty_date, String reason, String warranty_status) {
	    connectDB.getConnection();
	    boolean success = false;
	    try {
	        String sql = "UPDATE `warrantydetail` SET product_id = ?, start_date = ?, end_date = ?, warranty_date = ?, reason = ?, warranty_status = ? WHERE warranty_detail_id = ?";
	        PreparedStatement statement = connectDB.prepareStatement(sql);
	        statement.setInt(1, product_id);
	        statement.setString(2, start_date);
	        statement.setString(3, end_date);
	        statement.setString(4, warranty_date);
	        statement.setString(5, reason);
	        statement.setString(6, warranty_status);
	        statement.setInt(7, warranty_detail_id);

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            success = true;
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
			String sql = "DELETE FROM `warrantydetail` WHERE warranty_detail_id = ?";
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
			String sql = "SELECT MAX(warranty_detail_id) AS max_id FROM `warrantydetail` WHERE warranty_detail_id < ?";
			PreparedStatement statement = connectDB.prepareStatement(sql);
			statement.setInt(1, deleteWarId);
			ResultSet resultSet = statement.executeQuery();
	        int nextWarId = 1;
	        if (resultSet.next()) {
	        	nextWarId = resultSet.getInt("max_id") + 1;
	        }
	        sql = "ALTER TABLE `warrantydetail` AUTO_INCREMENT = ?";
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
 		  String sql = "SELECT * FROM `warrantydetail` WHERE warranty_detail_id = ?";
 		  statement = connectDB.prepareStatement(sql);
 		  statement.setInt(1,warranty_detail_id);
 		  resultSet = statement.executeQuery();
 		  if(resultSet.next()) {
 			 wt = new Warranty();
	            wt.setWarrantyid(resultSet.getInt("warranty_detail_id"));
	            wt.setProductid(resultSet.getInt("product_id"));
	            wt.setStartDate(resultSet.getString("start_date"));
	            wt.setEndDate(resultSet.getString("end_date"));
	            wt.setWarrantyDate(resultSet.getString("warranty_date"));
	            wt.setReason(resultSet.getString("reason"));
	            wt.setWarrantyStatus(resultSet.getString("warranty_status"));
 		  }
 	   } catch (SQLException e) {
	        e.printStackTrace();
	    }
 	   connectDB.closeConnection();
 	   return wt;
	}
}
