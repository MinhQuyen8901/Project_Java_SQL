create database if not exists quanly_nhansu;
use quanly_nhansu;

create table if not exists taiKhoan(
	maTK int auto_increment primary key,
    tenTK varchar(50),
    matKhau varchar(50)
);
describe taiKhoan;
insert into taiKhoan value 
(1,"nhom8","nhom8"),
(2,"nguyet","nguyet");

drop table if exists taiKhoan;

create table if not exists phongBan(
	maPB int auto_increment primary key,
    tenPB varchar(50),
	moTa varchar(255),
    maTruongPhong int,
    hoatDongPB boolean
);
describe phongBan;
drop table if exists phongBan;

insert into phongBan values
(100, "Nhan su", "Cong vien lien quan den nhan su", 2,  true),
(101, "Cong nghe thong tin", "Thiet ke giao dien nguoi dung", 5, true),
(102, "Kinh doanh", "Ban hang", 6, true);


create table if not exists nhanVien(
	maNV int auto_increment primary key,
    ten varchar(50),
    ho varchar(50),
    gioiTinh varchar(11),
    tuoi varchar(50),
    email varchar(255),
    soDT varchar(255),
    luong decimal(10,2),
    maTruongPhong int,
    maPB int,
    hoatDongNV boolean,
    constraint fk_nhanVien_phongBan foreign key (maPB) references phongBan(maPB)
);
describe nhanVien;
drop table if exists nhanVien;

insert into nhanVien values
(2, "Murphy", "Diane", "Nu", "25",	"dmurphy@classicmodelcars.com", "515.123.4568", 10000000, 1, 100, true),
(3, "Mary", "Patterson", "Nam", 25,	"mpatterso@classicmodelcars.com", "515.123.4569", 12000000, 2, 100, true),
(4, "Jeff", "Firrelli", "Nam", 26,	"jfirrelli@classicmodelcars.com", "590.423.4567", 15000000, 5, 101, true),
(5, "William", "Patterson", "Nam", 23,	"wpatterson@classicmodelcars.com", "590.423.4568", 10000000, 1, 101, true),
(6, "Gerard", "Bondur", "Nam", 24,	"gbondur@classicmodelcars.com", "590.423.4569", 10000000, 1, 102, true),
(7, "Bow", "Anthony", "Nu", 25,	"abow@classicmodelcars.com", "590.423.4560", 14000000, 6, 102, true);

/* Cac truong phong se co maTruongPhong = 1, maNV = 1 la chu tich cua cong ty)*/

