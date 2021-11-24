/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Controler.Methods;

import com.example.DBConnector.DataConnector;
import com.example.Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 88016
 */
public class DataMethod {

    public static Product ProductInsert(Product product) throws ClassNotFoundException, SQLException {

        Connection conn = DataConnector.getConnection();

        PreparedStatement pst = conn.prepareStatement("insert into products (name, quantity, price, remarks)"
                + " values (?, ?, ?, ?)");
        pst.setString(1, product.getName());
        pst.setInt(2, product.getQuantity());
        pst.setDouble(3, product.getPrice());
        pst.setString(4, product.getRemarks());
        int isSaved = pst.executeUpdate();
        if (isSaved > 0) {
            return product;
        }
        return null;
    }

    public static Product UpdateProduct(Product product) throws ClassNotFoundException, SQLException {

        Connection conn = DataConnector.getConnection();
        String sql = "update products set name=?, quantity=?, price=?, remarks=? where id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, product.getName());
        pst.setInt(2, product.getQuantity());
        pst.setDouble(3, product.getPrice());
        pst.setString(4, product.getRemarks());
        pst.setInt(5, product.getId());
        int rs = pst.executeUpdate();
        if (rs > 0) {
            return product;
        }
        return null;
    }

    public static boolean deleteProduct(String id) throws ClassNotFoundException, SQLException {
        Connection conn = DataConnector.getConnection();
        String sql = "delete from products where id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(id));
        int rs = pst.executeUpdate();
        if (rs > 0) {
            return true;
        }
        return false;
    }
}
