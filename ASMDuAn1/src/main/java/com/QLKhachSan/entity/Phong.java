/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLKhachSan.entity;

/**
 *
 * @author ASUS
 */
public class Phong {
    private String maPhong;
    private int soPhong;
    private double giaPhong;
    private String loaiPhong;
    private String trangThaiP;

    public Phong() {
    }

    public Phong(String maPhong, int soPhong, double giaPhong, String loaiPhong, String trangThaiP) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.giaPhong = giaPhong;
        this.loaiPhong = loaiPhong;
        this.trangThaiP = trangThaiP;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTrangThaiP() {
        return trangThaiP;
    }

    public void setTrangThaiP(String trangThaiP) {
        this.trangThaiP = trangThaiP;
    }
    
    
    public Object[] toObjectQLP() {
        return new Object[]{
            maPhong, soPhong,giaPhong, loaiPhong, trangThaiP
        };
    }
}
