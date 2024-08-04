/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.utils;

import com.QLKhachSan.entity.Khachhang;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class XJdbc {
   public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QLKHACHSAN;encrypt=true;trustServerCertificate=true";
            String USERNAME = "sa";
            String PASSWORD = "123456";

            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            return connection;

        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
        return null;
    }
   public static ResultSet executeQuery(String sql, Object... args) throws ClassNotFoundException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QLKHACHSAN;encrypt=true;trustServerCertificate=true";
            String USERNAME = "sa";
            String PASSWORD = "123456";
            
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
