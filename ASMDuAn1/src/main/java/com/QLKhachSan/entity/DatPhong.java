/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.entity;

/**
 *
 * @author ASUS
 */
public class DatPhong {
    private String maDP;
    private String maPhong;
    private String maKH;
    private int soNguoi;
    private double giaPhong;
    private String ngayNP;
    private String ngayTP;

    public DatPhong() {
    }

    public DatPhong(String maDP, String maPhong, String maKH, int soNguoi, double giaPhong, String ngayNP, String ngayTP) {
        this.maDP = maDP;
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.soNguoi = soNguoi;
        this.giaPhong = giaPhong;
        this.ngayNP = ngayNP;
        this.ngayTP = ngayTP;
    }

    public String getMaDP() {
        return maDP;
    }

    public void setmaDP(String maDP) {
        this.maDP = maDP;
    }
    
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }
    
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getNgayNP() {
        return ngayNP;
    }

    public void setNgayNP(String ngayNP) {
        this.ngayNP = ngayNP;
    }

    public String getNgayTP() {
        return ngayTP;
    }

    public void setNgayTP(String ngayTP) {
        this.ngayTP = ngayTP;
    }

        public Object[] toObjectQLDP(){
        return new Object[] {
        maDP, maPhong, maKH, soNguoi, giaPhong, ngayNP, ngayTP     
        };
    }
    
    
}
