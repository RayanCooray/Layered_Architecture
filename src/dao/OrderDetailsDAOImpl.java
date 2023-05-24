package dao;

import db.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAOImpl {
    public static boolean saveOrderDetails(String orderId, String itemCode, BigDecimal unitPrice, int qty) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        stm.setString(1, orderId);
        stm.setString(2, itemCode);
        stm.setBigDecimal(3, unitPrice);
        stm.setInt(4, qty);
        return stm.executeUpdate() > 0;
    }
}
