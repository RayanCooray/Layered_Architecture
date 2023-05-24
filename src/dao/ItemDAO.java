package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> getAllitems() throws SQLException, ClassNotFoundException ;
    boolean saveitems(String code, String description, BigDecimal unitPrice, Integer qtyOnHand) throws SQLException, ClassNotFoundException;
    boolean updateitem(String code, BigDecimal unitPrice, String qtyOnHand, String description) throws SQLException, ClassNotFoundException;
    boolean existItems(String code) throws SQLException, ClassNotFoundException;
    String generateItemId() throws SQLException, ClassNotFoundException;
    boolean deleteitem(String code) throws SQLException, ClassNotFoundException;

}
