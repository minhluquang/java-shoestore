package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import DTO.Return;

public class ReturnDAO {
    public static ArrayList<Return> getDanhSachReturn() {
        ArrayList<Return> dsrt = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Thực hiện kết nối cơ sở dữ liệu
            connection = connectDB.getConnection();

            // Chuẩn bị truy vấn SQL
            String sql = "SELECT return_id, product_id, date_return, reason FROM `return`";
            statement = connection.prepareStatement(sql);

            // Thực thi truy vấn và nhận kết quả
            resultSet = statement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách
            while (resultSet.next()) {
                Return rt = new Return();
                rt.setReturn_id(resultSet.getInt("return_id"));
                rt.setProduct_id(resultSet.getInt("product_id"));
                rt.setDate_return(resultSet.getString("date_return"));
                rt.setReason(resultSet.getString("reason"));
                dsrt.add(rt);
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

        return dsrt;
    }
}

