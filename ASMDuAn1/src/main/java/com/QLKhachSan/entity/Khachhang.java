/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.entity;

import com.QLKhachSan.utils.XDate;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Khachhang {
    private String maKH;
    private String tenKH;
    private String SDT;
    private String diaChi;
    private String Email;
    private Date ngaySinh =XDate.addDays(new Date(), -365*20);
    private boolean gioiTinh;

    public Khachhang(String maKH, String tenKH, String SDT, String diaChi, String Email, boolean gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.Email = Email;
        this.gioiTinh = gioiTinh;
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
}
