package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;
     boolean savecustomer(String id, String name, String address) throws SQLException, ClassNotFoundException ;
     boolean updatecustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;
     boolean deletecustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException ;
     boolean exitcustomer(String id) throws SQLException, ClassNotFoundException;
}
