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
        ArrayList<Khachhang> khachHangArr = new ArrayList<Khachhang>();
        try {
            String sql = "SELECT * FROM KHACHHANG";

            Connection conn = XJdbc.getConnection();
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Date ngaySinh = null;
                Khachhang khachhang = new Khachhang();
                khachhang.setMaKH(res.getString("maKH"));
                khachhang.setTenKH(res.getString("tenKH"));
                khachhang.setGioiTinh(res.getBoolean("gioiTinh"));
                khachhang.setNgaySinh(res.getDate("ngaySinh"));
                khachhang.setCCCD(res.getString("CCCD"));
                khachhang.setDiaChi(res.getString("diaChi"));
                khachhang.setSDT(res.getString("SDT"));
                khachhang.setEmail(res.getString("Email"));
                khachHangArr.add(khachhang);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHangArr;
    }
    public static int addKkhachhang(Khachhang khachhang) {
        String sql = "INSERT INTO KHACHHANG(MaKH, TenKH, GioiTinh, SDT, DiaChi, Email, NgaySinh, CCCD) VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, khachhang.getMaKH());
            preparedStatement.setString(2, khachhang.getTenKH());
            preparedStatement.setBoolean(3, khachhang.getGioiTinh());
            preparedStatement.setString(3, khachhang.getSDT());
            preparedStatement.setString(4, khachhang.getDiaChi());
            preparedStatement.setString(5, khachhang.getEmail());
            preparedStatement.setDate(6, new java.sql.Date(khachhang.getNgaySinh().getTime()));

            preparedStatement.setString(8, khachhang.getCCCD());
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
        String sql = "UPDATE KHACHHANG SET TenKH = ?, SDT = ?, DiaChi = ?, Email = ?, NgaySinh = ?, GioiTinh = ?, CCCD = ? WHERE MaKH = ?";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
preparedStatement.setString(1, khachhang.getTenKH());
            preparedStatement.setString(2, khachhang.getSDT());
            preparedStatement.setString(3, khachhang.getDiaChi());
            preparedStatement.setString(4, khachhang.getEmail());
            preparedStatement.setDate(5, new java.sql.Date(khachhang.getNgaySinh().getTime()));
            preparedStatement.setBoolean(6, khachhang.getGioiTinh());
            preparedStatement.setString(7, khachhang.getMaKH());
            preparedStatement.setString(8, khachhang.getCCCD());

            int count = preparedStatement.executeUpdate();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
public static Khachhang getKhachhangByID(String id) {
    String sql = "SELECT * FROM KHACHHANG WHERE MaKH = ?";
    try (
        Connection conn = XJdbc.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, id);
        ResultSet res = pstmt.executeQuery();

        if (res.next()) {
            String maKH = res.getString("MaKH");
            String tenKH = res.getString("TenKH");
            String SDT = res.getString("SDT");
            String diaChi = res.getString("DiaChi");
            String email = res.getString("Email");
            Date ngaySinh = res.getDate("NgaySinh");
            boolean gioiTinh = res.getBoolean("GioiTinh");
            String CCCD = res.getString("CCCD");

            Khachhang khachhang = new Khachhang();
            return khachhang;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}


