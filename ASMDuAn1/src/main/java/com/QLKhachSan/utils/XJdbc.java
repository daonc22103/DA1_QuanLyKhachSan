/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class XJdbc {
    private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;encrypt=false;trustServerCertificate=true;databaseName=QLKHACHSAN";
    private static String username="sa";
    private static String password="123456";
    
    /*
     * Nạp driver
     */
    static{
        try {            
            Class.forName(driver);
        } 
        catch (ClassNotFoundException ex) {
            System.out.println("Register CLass ERROR: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(dburl, username, password);
        }catch(Exception e){
            System.out.println(e.getMessage() + "ERROR CONNECTION");
        }
        if(connection == null)
            System.err.print("Cannot connection to the Database Server MSSQL!");
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }
        else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    
    public static void update(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } 
            finally{
                stmt.getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
