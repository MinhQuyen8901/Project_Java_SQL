package Model;

import java.text.DecimalFormat;
import java.util.Scanner;

public class NhanVien {
    public static Scanner in = new Scanner(System.in);
    private int maNV;
    private String ten;
    private String ho;
    private String gioiTinh;
    private String tuoi;
    private String email;
    private String soDT;
    private double luong;
    private int maTruongPhong;
    private int maPB;
    private boolean hoatDongNV;

    public NhanVien() {
    }

    public NhanVien(int maNV, String ten, String ho, String gioiTinh, String tuoi, String email,
                    String soDT, double luong, int maTruongPhong, int maPB) {
        this.maNV = maNV;
        this.ten = ten;
        this.ho = ho;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
        this.email = email;
        this.soDT = soDT;
        this.luong = luong;
        this.maTruongPhong = maTruongPhong;
        this.maPB = maPB;
    }

    public NhanVien(String ten, String ho, String gioiTinh, String tuoi, String email, String soDT, double luong, int maTruongPhong, int maPB) {
        this.ten = ten;
        this.ho = ho;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
        this.email = email;
        this.soDT = soDT;
        this.luong = luong;
        this.maTruongPhong = maTruongPhong;
        this.maPB = maPB;
    }

    public int getMaNV() {
        return maNV;
    }


    public boolean setMaNV(String str) {
        if (str.matches("[0-9]+")) {  // Chi nhan cac so nguyen va so 0 de Dung cac hoat dong
            return true;
        } else {
            System.out.println("Ma nhan vien khong hop le, vui long nhap lai.");
            return false;
        }
    }


    public String getTen() {
        return ten;
    }

    public boolean setTen(String ten) {
        if (ten.equals("") || ten.length() > 50){  // khong duoc bo trong va khong duoc dai hơn 50 ky tu
            return false;
        }
        return true;
    }

    public String getHo() {
        return ho;
    }

    public boolean setHo(String ho) {
        if (ho.equals("") || ho.length() > 50){  // khong duoc bo trong va khong duoc dai hơn 50 ky tu
            return false;
        }
        return true;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public boolean setGioiTinh(String gioiTinh) {
        return gioiTinh.matches("nam|nu|khac");
    }

    public String getTuoi() {
        return tuoi;
    }

    public boolean setTuoi(String tuoi) {
        return tuoi.matches("[1-9][0-9]*");
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    public String getSoDT() {
        return soDT;
    }

    public boolean setSoDT(String soDT) {
        return soDT.matches("[0-9]*.[0-9]*.[0-9]*");
    }

    public double getLuong() {
        return luong;
    }

    //dinh dang luong
    public static String dinhDangLuong(double luong){
        DecimalFormat decimalFormat = new DecimalFormat("#,###");  //chuyển đổi số lương từ kiểu dữ liệu double sang chuỗi (string) có định dạng số có dấu phẩy ngăn cách hàng nghìn.
        String dangLuong = decimalFormat.format(luong);
        return dangLuong;
    }

    public boolean setLuong(String str){
        try {
            Double num = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            //throw new RuntimeException(e);
            //System.out.println("Luong khong hop le, vui long nhap lai.");
            return false;
        }
    }

    public int getMaTruongPhong() {
        return maTruongPhong;
    }

    public void setMaTruongPhong(int maTruongPhong) {
        this.maTruongPhong = maTruongPhong;
    }

    public int getMaPB() {
        return maPB;
    }

    public void setMaPB(int maPB) {
        this.maPB = maPB;
    }

    public boolean isHoatDongNV() {
        return hoatDongNV;
    }

    public void setHoatDongNV(boolean hoatDongNV) {
        this.hoatDongNV = hoatDongNV;
    }

    public double tinhThue(double luong){
//        if (str.matches("[0-9]+")) {  // Chi nhan cac so nguyen va so 0 de Dung cac hoat dong
//            return true;
//        } else {
//            System.out.println("Ma nhan vien khong hop le, vui long nhap lai.");
//            return false;
//        }
        double thueSuat = 0;
        if (luong <= 5000000){
            thueSuat = 0.05;
        }else if (luong > 5000000 && luong <= 10000000){
            thueSuat = 0.1;
        }else if (luong > 10000000 && luong <= 18000000){
            thueSuat = 0.15;
        }else if (luong > 18000000 && luong <= 32000000){
            thueSuat = 0.2;
        }else if (luong > 32000000 && luong <= 52000000){
            thueSuat = 0.25;
        }else if (luong > 52000000 && luong <= 80000000){
            thueSuat = 0.3;
        }else {
            thueSuat = 0.35;
        }
        double thueThuNhapCaNhan = luong * thueSuat;
        String dinhDangThue = dinhDangLuong(thueThuNhapCaNhan);
        //System.out.println("Thue thu nhap ca nhan la: "+ thueThuNhapCaNhan);
        return thueThuNhapCaNhan;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV=" + maNV +
                ", ten='" + ten + '\'' +
                ", ho='" + ho + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", email='" + email + '\'' +
                ", soDT='" + soDT + '\'' +
                ", luong=" + dinhDangLuong(luong)+ "VND" +
                ", maTruongPhong=" + maTruongPhong +
                ", maPB=" + maPB +
                '}';
    }
}
