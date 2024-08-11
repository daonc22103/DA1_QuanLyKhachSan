/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.dao;

import com.QLKhachSan.entity.DatPhong;
import com.QLKhachSan.entity.Khachhang;
import com.QLKhachSan.utils.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class QLHoaDon {
    public static void findByMaDP(String maDP){
        try {
            String sql = "SELECT * FROM DATPHONG WHERE MaDP = ?";
            Connection conn = XJdbc.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maDP);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String maKH = rs.getString("maKH");
            }
        } catch (Exception e) {
        }
    }
}
