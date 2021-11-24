/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DBConnector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 88016
 */
public class DataConnector {

    public static Connection connection = null;
    public static String url = "jdbc:mysql://localhost:3306/school";
    public static String user = "root";
    public static String pass = "123456";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, pass);
        } else {
            return connection;
        }

        return connection;
    }

}
