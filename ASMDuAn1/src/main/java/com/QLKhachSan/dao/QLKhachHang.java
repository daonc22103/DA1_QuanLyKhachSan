package com.QLKhachSan.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.QLKhachSan.dao;
import com.QLKhachSan.entity.Khachhang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.QLKhachSan.utils.XJdbc;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class QLKhachHang {

    public static ArrayList<Khachhang> getAllKhachHang() {
        String sql = "SELECT * FROM KHACHHANG";
        ArrayList<Khachhang> khachHangArr = new ArrayList<Khachhang>();
        try {
            Connection conn = XJdbc.getConnection();
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Khachhang khachhang = new Khachhang();
                khachhang.setMaKH(res.getString("maKH"));
                khachhang.setTenKH(res.getString("tenKH"));
                khachhang.setGioiTinh(res.getInt("gioiTinh"));
                khachhang.setNgaySinh(res.getString("ngaySinh"));
                khachhang.setCCCD(res.getString("CCCD"));
                khachhang.setDiaChi(res.getString("diaChi"));
                khachhang.setSDT(res.getString("SDT"));
                khachhang.setEmail(res.getString("Email"));
                khachHangArr.add(khachhang);
            }
            return khachHangArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int addKkhachhang(Khachhang khachhang) {
        String sql = "INSERT INTO KHACHHANG(MaKH, TenKH, GioiTinh, NgaySinh, CCCD, DiaChi, SDT, Email) VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachhang.getMaKH());
            preparedStatement.setString(2, khachhang.getTenKH());
            preparedStatement.setInt(3, khachhang.getGioiTinh());
            preparedStatement.setString(4, khachhang.getNgaySinh());
            preparedStatement.setString(5, khachhang.getCCCD());
            preparedStatement.setString(6, khachhang.getDiaChi());
            preparedStatement.setString(7, khachhang.getSDT());
            preparedStatement.setString(8, khachhang.getEmail());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int DeleteKhachHang(Khachhang khachhang) {
        String sql = "DELETE FROM KHACHHANG WHERE MaKH = ?";
        try {
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachhang.getMaKH());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public static int updateKhachhang(Khachhang khachhang) {
        String sql = "UPDATE KHACHHANG SET TenKH = ?, GioiTinh = ?, NgaySinh = ?, CCCD = ?,DiaChi = ?, SDT = ?, Email = ? WHERE MaKH = ?";
        try (
                Connection conn = XJdbc.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, khachhang.getTenKH());
            preparedStatement.setInt(2, khachhang.getGioiTinh());
            preparedStatement.setString(3, khachhang.getNgaySinh());
            preparedStatement.setString(4, khachhang.getCCCD());
            preparedStatement.setString(5, khachhang.getDiaChi());
            preparedStatement.setString(6, khachhang.getSDT());
            preparedStatement.setString(7, khachhang.getEmail());
            preparedStatement.setString(8, khachhang.getMaKH());

            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Khachhang getKhachHangByMaKH(String maKH) {
        // Giả sử bạn có một phương thức tìm khách hàng trong cơ sở dữ liệu
        // Ví dụ:
        String sql = "SELECT * FROM KHACHHANG WHERE MaKH = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maKH);
            if (rs.next()) {
                Khachhang khachhang = new Khachhang();
                khachhang.setMaKH(rs.getString("maKH"));
                khachhang.setTenKH(rs.getString("tenKH"));
                khachhang.setGioiTinh(rs.getInt("gioiTinh"));
                khachhang.setNgaySinh(rs.getString("ngaySinh"));
                khachhang.setCCCD(rs.getString("CCCD"));
                khachhang.setDiaChi(rs.getString("diaChi"));
                khachhang.setSDT(rs.getString("SDT"));
                khachhang.setEmail(rs.getString("Email"));
                return khachhang;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Khachhang getKhachHangByTenKH(String tenKhachHang) {
        String sql = "SELECT * FROM KHACHHANG WHERE TenKH = ?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, tenKhachHang);
            if (rs.next()) {
                Khachhang khachhang = new Khachhang();
                khachhang.setMaKH(rs.getString("MaKH"));
                khachhang.setTenKH(rs.getString("TenKH"));
                khachhang.setGioiTinh(rs.getInt("gioiTinh"));
                khachhang.setNgaySinh(rs.getString("ngaySinh"));
                khachhang.setCCCD(rs.getString("CCCD"));
                khachhang.setDiaChi(rs.getString("DiaChi"));
                khachhang.setSDT(rs.getString("SDT"));
                khachhang.setEmail(rs.getString("Email"));
                return khachhang;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
