﻿CREATE DATABASE QLKHACHSAN;
USE QLKHACHSAN

CREATE TABLE NHANVIEN (
    MaNV INT PRIMARY KEY,
    TenNV NVARCHAR(100),
    ChucVu NVARCHAR(50),
    CongViec NVARCHAR(100),
    NamSinh DATE,
    GioiTinh NVARCHAR(3)
);
ALTER TABLE NHANVIEN
ADD 
    LuongNgayCong DECIMAL(18, 2),
    LuongCoBan DECIMAL(18, 2),
    PhuCap DECIMAL(18, 2),
    KhauTru DECIMAL(18, 2);




INSERT INTO NHANVIEN (MaNV, TenNV, ChucVu, CongViec, NamSinh, GioiTinh, LuongNgayCong, LuongCoBan, PhuCap, KhauTru)
VALUES 
(3, 'Nguyen Van A', 'Nhan Vien', 'Quan Ly', '1980-01-15', 'Nam', 3000000, 5000000, 1000000, 500000),
(4, 'Le Thi B', 'Truong Phong', 'Ke Toan', '1975-07-22', 'Nu', 4000000, 6000000, 800000, 400000);

SELECT * FROM NHANVIEN;

UPDATE NHANVIEN
SET 
    LuongNgayCong = 300000,
    LuongCoBan = 9000000,
    PhuCap = 500000,
    KhauTru = 200000
WHERE MaNV = 2;


UPDATE NHANVIEN
SET GioiTinh = 'Nam'
WHERE MaNV = 2;

CREATE TABLE KHACHHANG (
    MaKH INT PRIMARY KEY,
    TenKH NVARCHAR(100),
    SDT VARCHAR(15),
    DIACHI NVARCHAR(200),
    Email VARCHAR(100),
    NamSinh DATE,
    GioiTinh NVARCHAR(3)
);

CREATE TABLE PHONG (
    MaPhong INT PRIMARY KEY,
    SoPhong VARCHAR(10),
    GiaPhong DECIMAL(10, 2),
    LoaiPhong NVARCHAR(50),
    TrangThaiP NVARCHAR(20)
);

CREATE TABLE DATPHONG (
    MaDatPhong INT PRIMARY KEY,
    MaKH INT,
    MaPhong INT,
    NgNhanPhong DATE,
    NgTraPhong DATE,
    TrangThaiDP NVARCHAR(20),
    FOREIGN KEY (MaKH) REFERENCES KHACHHANG(MaKH),
    FOREIGN KEY (MaPhong) REFERENCES PHONG(MaPhong)
);


CREATE TABLE THANHTOAN (
    MaThanhToan INT PRIMARY KEY,
    MaDatPhong INT,
    MaNV INT,
    NgThanhToan DATE,
    TrangThaiTT NVARCHAR(20),
    SoTien DECIMAL(10, 2),
    FOREIGN KEY (MaDatPhong) REFERENCES DATPHONG(MaDatPhong),
    FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV)
);