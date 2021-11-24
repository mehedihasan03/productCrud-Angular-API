/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Controler;

import com.example.ApiResponse.ApiResponce;
import com.example.DBConnector.DataConnector;
import com.example.Model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.beans.Introspector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 88016
 */
@WebServlet(name = "ShowControler", urlPatterns = {"/showProduct"})
public class ShowProductControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ApiResponce resp = new ApiResponce();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            List<Product> p = getProducts();
            String strProduct = gson.toJson(p);
            PrintWriter out = response.getWriter();
            out.write(strProduct);
            out.flush();
            out.close();
        } catch (ClassNotFoundException ex) {
            resp.setMassage(ex.getMessage());
        } catch (SQLException ex) {
             resp.setMassage(ex.getMessage());
        }

    }

    public List<Product> getProducts() throws ClassNotFoundException, SQLException {
        List<Product> plist = new ArrayList<>();

        Connection conn = DataConnector.getConnection();
        String sql = "select * from products";

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            plist.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("remarks")
                )
            );
        }
        return plist;

    }
}
