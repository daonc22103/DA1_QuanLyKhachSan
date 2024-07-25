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
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String chucVu;
    private String congViec;
    private Date ngaySinh =XDate.addDays(new Date(), -365*20);
    private boolean gioTinh;

    public NhanVien(String maNV, String tenNV, String chucVu, String congViec, boolean gioTinh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.congViec = congViec;
        this.gioTinh = gioTinh;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioTinh() {
        return gioTinh;
    }

    public void setGioTinh(boolean gioTinh) {
        this.gioTinh = gioTinh;
    }
    
    
}
