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
public class NhanVien {

    public String maNV;
    public String tenNV;
    public String chucVu;
    public String congViec;
    public String ngaySinh;
    public boolean gioiTinh;
    private double luongNgayCong;
    private double luongCoBan;
    private double phuCap;
    private double khauTru;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String chucVu, String congViec, String ngaySinh, boolean gioiTinh, double luongNgayCong, double luongCoBan, double phuCap, double khauTru) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.congViec = congViec;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.luongNgayCong = luongNgayCong;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        this.khauTru = khauTru;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double getLuongNgayCong() {
        return luongNgayCong;
    }

    public void setLuongNgayCong(double luongNgayCong) {
        this.luongNgayCong = luongNgayCong;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getKhauTru() {
        return khauTru;
    }

    public void setKhauTru(double khauTru) {
        this.khauTru = khauTru;
    }

    public String getGioiTinhString() {
        return gioiTinh ? "Nam" : "Nữ";
    }

    public double getTongLuong() {
        return luongCoBan + phuCap - khauTru;
    }
}
