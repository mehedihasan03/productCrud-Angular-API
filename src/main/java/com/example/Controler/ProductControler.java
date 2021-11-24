/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Controler;

import com.example.ApiResponse.ApiResponce;
import com.example.Controler.Methods.DataMethod;
import com.example.Model.Product;
import com.example.Utill.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 88016
 */
@WebServlet(name = "ProductControler", urlPatterns = {"/saveProduct"})
public class ProductControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //==(Gson is a Library)=======get request(json data) to convert java object===================
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = Utility.requestBodyToStr(request);
        Product product = gson.fromJson(jb.toString(), Product.class);
        System.out.println("Product" + gson.toJson(product));
        //=======================database coding=======================
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("product", product);
        ApiResponce apiRes = new ApiResponce("Save Successfull'", data, 200);

        try {
            Product p = DataMethod.ProductInsert(product);
            if (p != null) {
                apiRes.setMassage("insert successfull");
                apiRes.setStatusCode(200);
            } else {
                apiRes.setMassage("unsuccessfull");
            }
        } catch (ClassNotFoundException ex) {
            apiRes.setMassage(ex.getMessage());
        } catch (SQLException ex) {
            apiRes.setMassage(ex.getMessage());
        }
        //========================response from json===================
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(apiRes));
        out.flush();
        out.close();

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringBuffer jb = Utility.requestBodyToStr(req);
        Product product = gson.fromJson(jb.toString(), Product.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("product", product);
        ApiResponce apiRes = new ApiResponce();

        try {
            Product p = DataMethod.UpdateProduct(product);
            if (p != null) {
                apiRes.setMassage("Update successfull");
                apiRes.setStatusCode(200);
            } else {
                apiRes.setMassage("Udate Unsuccessfull");
                apiRes.setStatusCode(401);
            }
        } catch (ClassNotFoundException ex) {
            apiRes.setMassage(ex.getMessage());
        } catch (SQLException ex) {
            apiRes.setMassage(ex.getMessage());
        }
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(apiRes));
        out.flush();
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Gson gson = new Gson();
        ApiResponce apires = new ApiResponce();
        try {
            boolean isDelete = DataMethod.deleteProduct(id);
            if (isDelete) {
                apires.setMassage("Product removed successfully");
                apires.setStatusCode(201);
            } else {
                apires.setMassage("deletation unsuccessfull");
                apires.setStatusCode(401);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(apires));
        out.flush();
        out.close();

    }
}
