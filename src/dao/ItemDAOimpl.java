package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOimpl implements ItemDAO {
    @Override
    public  ArrayList<ItemDTO> getAllitems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO>items =new ArrayList<>();
        while (rst.next()){
            items.add(new ItemDTO(rst.getString("code"),rst.getString("description"),rst.getBigDecimal("unitPrice"),rst.getInt("qtyOnHand")));
        }return items;
    }
    @Override
    public  boolean saveitems(String code, String description, BigDecimal unitPrice,Integer qtyOnHand) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, code);
        pstm.setString(2, description);
        pstm.setBigDecimal(3, unitPrice);
        pstm.setInt(4, qtyOnHand);
        pstm.executeUpdate();
        return pstm.executeUpdate()>0;

    }
    @Override
    public  boolean updateitem(String code, BigDecimal unitPrice, String qtyOnHand, String description) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
                pstm.setString(1, description);
                pstm.setBigDecimal(2,unitPrice);
                pstm.setString(3, qtyOnHand);
                pstm.setString(4, code);
                pstm.executeUpdate();
        return pstm.executeUpdate()>0;
    }
    @Override
    public boolean existItems(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }
    @Override
    public String generateItemId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public boolean deleteitem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();
        return pstm.executeUpdate()>0;
    }
}
