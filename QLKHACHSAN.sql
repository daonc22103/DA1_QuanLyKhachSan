CREATE DATABASE QLKHACHSAN;
USE QLKHACHSAN

CREATE TABLE NHANVIEN (
    MaNV INT PRIMARY KEY,
    TenNV NVARCHAR(100),
    ChucVu NVARCHAR(50),
    CongViec NVARCHAR(100),
    NamSinh DATE,
    GioiTinh NVARCHAR(3)
);

SELECT * FROM NHANVIEN
INSERT INTO NHANVIEN (MaNV, TenNV, ChucVu, CongViec, NamSinh, GioiTinh)
VALUES (3, N'Trần Văn C', N'Lễ Tân', N'Hướng dẫn khách hàng', '1992-05-15', N'Nam'),
(2, N'Nguyễn Thị B', N'Lễ tân', N'Tiếp đón khách và quản lý hồ sơ', '1985-12-22', N'Nữ');



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