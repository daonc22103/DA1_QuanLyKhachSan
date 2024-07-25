/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.dao;

import com.QLKhachSan.dao.QLKhachSanDAO;
import com.QLKhachSan.entity.Khachhang;
import com.QLKhachSan.utils.XJdbc;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class QLKhachHang extends QLKhachSanDAO<Object, Object>{

    public void insert(Khachhang model) {
                String sql="INSERT INTO KhachHang (MaKH, TenKH, SDT, DiaChi, Email, NgaySinh, GioiTinh) VALUES (?, ?, ?, ?, ?, ?, ?)";
                XJdbc.update(sql, 
                model.getMaKH(), 
                model.getTenKH(), 
                model.getSDT(), 
                model.getDiaChi(), 
                model.getEmail(),
                model.getNgaySinh(),
                model.isGioiTinh());

    }

    public void update(Khachhang model){
        String sql="UPDATE KhachHang SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? WHERE MaNH=?";
        XJdbc.update(sql, 
                model.getMaKH(), 
                model.getTenKH(), 
                model.getSDT(), 
                model.getDiaChi(), 
                model.getEmail(),
                model.getNgaySinh(),
                model.isGioiTinh());
    }

    public void delete(Integer MaKH){
        String sql="DELETE FROM KhachHang WHERE MaKH=?";
        XJdbc.update(sql, MaKH);
    }

    @Override
    public Object selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<Object> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
