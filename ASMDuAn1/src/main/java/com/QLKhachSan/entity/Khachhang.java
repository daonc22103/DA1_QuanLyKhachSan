/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.entity;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Khachhang {
    private String maKH;
    private String tenKH;
    private int gioiTinh;
    private String ngaySinh;
    private String CCCD;
    private String diaChi;
    private String SDT;
    private String Email;

    public Khachhang() {
    }
    
    public Khachhang(String maKH, String tenKH, int gioiTinh, String CCCD, String diaChi, String SDT, String Email) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.Email = Email;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public Object[] toObjectQLKH(){
        return new Object[] {
        maKH, tenKH, gioiTinh == 1 ? "Nam" : "Nữ", ngaySinh, CCCD, diaChi, SDT, Email      
        };
    }
}
