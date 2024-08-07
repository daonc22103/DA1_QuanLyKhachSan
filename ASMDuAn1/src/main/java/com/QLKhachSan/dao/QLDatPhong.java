/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.dao;

import com.QLKhachSan.entity.DatPhong;
import com.QLKhachSan.entity.Phong;
import com.QLKhachSan.utils.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class QLDatPhong {
    public static ArrayList<DatPhong> getAllDatPhong() {
        ArrayList<DatPhong> datphongArr = new ArrayList<DatPhong>();
        try {
            String sql = "SELECT * FROM DATPHONG";

            Connection conn = XJdbc.getConnection();
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                DatPhong datPhong = new DatPhong();
                datPhong.setmaDP(res.getString("maDP"));
                datPhong.setMaPhong(res.getString("maPhong"));
                datPhong.setTenKH(res.getString("tenKH"));
                datPhong.setSoNguoi(res.getInt("soNguoi"));
                datPhong.setGiaPhong(res.getFloat("giaPhong"));
                datPhong.setNgayNP(res.getString("ngayNP"));
                datPhong.setNgayTP(res.getString("ngayTP"));
                datphongArr.add(datPhong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return datphongArr;
    }
    public static int addDatPhong(DatPhong datPhong) {
        String sql = "INSERT INTO DATPHONG(MaDP, MaPhong, TenKH, SoNguoi, GiaPhong, NgayNP, NgayTP) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, datPhong.getMaDP());
            preparedStatement.setString(2, datPhong.getMaPhong());
            preparedStatement.setString(3, datPhong.getTenKH());
            preparedStatement.setInt(4,datPhong.getSoNguoi());
            preparedStatement.setDouble(5, datPhong.getGiaPhong());
            preparedStatement.setString(6, datPhong.getNgayNP());
            preparedStatement.setString(7, datPhong.getNgayTP());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
        public static int DeleteDatPhong(String a) {
        String sql = "DELETE FROM DATPHONG WHERE MaDP = ?";
        try {
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, a);
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
