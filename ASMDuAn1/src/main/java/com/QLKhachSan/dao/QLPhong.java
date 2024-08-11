/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.dao;

import com.QLKhachSan.entity.Phong;
import com.QLKhachSan.utils.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class QLPhong {

    public static ArrayList<Phong> getAllPhong() {
        ArrayList<Phong> phongArr = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PHONG";

            Connection conn = XJdbc.getConnection();
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Phong phong = new Phong();
                phong.setMaPhong(res.getString("maPhong"));
                phong.setSoPhong(res.getInt("soPhong"));
                phong.setGiaPhong(res.getFloat("giaPhong"));
                phong.setLoaiPhong(res.getString("loaiPhong"));
                phong.setTrangThaiP(res.getString("trangThaiP"));
                phongArr.add(phong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phongArr;
    }

    public static int addPhong(Phong phong) {
        String sql = "INSERT INTO PHONG(MaPhong, SoPhong, GiaPhong, LoaiPhong, TrangThaiP) VALUES(?,?,?,?,?)";
        try {
            Connection connection = XJdbc.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phong.getMaPhong());
            preparedStatement.setInt(2, phong.getSoPhong());
            preparedStatement.setDouble(3, phong.getGiaPhong());
            preparedStatement.setString(4, phong.getLoaiPhong());
            preparedStatement.setString(5, phong.getTrangThaiP());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int updatePhong(Phong phong) {
        String sql = "UPDATE PHONG SET SoPhong = ?, GiaPhong = ?, LoaiPhong = ?,TrangThaiP = ? WHERE MaPhong = ?";
        try (
                Connection conn = XJdbc.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, phong.getSoPhong());
            preparedStatement.setDouble(2, phong.getGiaPhong());
            preparedStatement.setString(3, phong.getLoaiPhong());
            preparedStatement.setString(4, phong.getTrangThaiP());
            preparedStatement.setString(5, phong.getMaPhong());

            int i = preparedStatement.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void updateRoomStatus(String maPhong, String status) {
        String sql = "UPDATE PHONG SET TrangThaiP = ? WHERE MaPhong = ?";
        try (Connection conn = XJdbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, maPhong);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật trạng thái phòng: " + e.getMessage());
        }
    }

    public String getRoomStatus(String maPhong) {
        String status = null;
        String sql = "SELECT TrangThaiP FROM PHONG WHERE MaPhong = ?";
        try (Connection conn = XJdbc.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maPhong);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("TrangThaiP");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy trạng thái phòng: " + e.getMessage());
        }
        return status;
    }

}
