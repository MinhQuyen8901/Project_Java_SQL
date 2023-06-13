package DAO;

import Connect.Connect;
import Model.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NhanVienDAO {
    public static Scanner in = new Scanner(System.in);
    // Them nv mới
    public void themNV(NhanVien nV){
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "INSERT INTO nhanVien(ten, ho, gioiTinh, tuoi, Email, soDT, luong, hoatDongNV) " +
                    "VALUES (?,?,?,?,?,?,?,true)";    // mac dinh them nhan vien moi isActive = true
            prst = conn.prepareStatement(sql);
            prst.setString(1, nV.getTen());
            prst.setString(2, nV.getHo());
            prst.setString(3, nV.getGioiTinh());
            prst.setString(4, nV.getTuoi());
            prst.setString(5, nV.getEmail());
            prst.setString(6, nV.getSoDT());
            prst.setDouble(7, nV.getLuong());
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (prst != null){
                try {
                    prst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //sua thong tin nv
    public <T> void suaNV(T a, T b, int maNV){
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE nhanVien SET " + a + "='" + b + "' WHERE maNV=" + maNV;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //Xoa nhan vien, Updata phong ban cho nhan vien
    public <T> void suaNVtrongPB(T a, T b, T c, T d){
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE nhanVien SET " + a + "=" + b + " WHERE " + c + "="+ d;
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //In danh sach nhan vien
    public List<NhanVien> dsNhanVien(){
        List<NhanVien> dsNhanVien = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT*FROM nhanVien WHERE hoatDongNV = true";  // in ds cac nhan vien dang hoat dong
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                NhanVien em = new NhanVien(
                        rs.getInt("maNV"),
                        rs.getString("ten"),
                        rs.getString("ho"),
                        rs.getString("gioiTinh"),
                        rs.getString("tuoi"),
                        rs.getString("Email"),
                        rs.getString("soDT"),
                        rs.getDouble("luong"),
                        rs.getInt("maTruongPhong"),
                        rs.getInt("maPB"));
                dsNhanVien.add(em);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return dsNhanVien;
    }


    // Xoa nhan vien
//    public void deleteEm(int employeeID){
//        Connection conn = null;
//        try {
//            conn = Connect.getInstance().getConnection();
//            Statement stmt = conn.createStatement();
//            String sql = "DELETE FROM employees WHERE employeeID = " + employeeID;
//            stmt.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

    // Tim nhan vien
    public <T> NhanVien timKiemNV(T x, T y) {
        NhanVien nhanVien = new NhanVien();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT*FROM nhanVien WHERE " + x + "='" + y+ "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Integer maNV = rs.getInt("maNV");
                String ten = rs.getString("ten");
                String ho = rs.getString("ho");
                String gioiTinh = rs.getString("gioiTinh");
                String tuoi = rs.getString("tuoi");
                String Email = rs.getString("Email");
                String soDT = rs.getString("soDT");
                Double luong = rs.getDouble("luong");
                Integer maPB = rs.getInt("maTruongPhong");
                Integer maTuongPhong = rs.getInt("maPB");
                nhanVien = new NhanVien(maNV, ten, ho, gioiTinh, tuoi, Email, soDT, luong, maPB, maTuongPhong);
            }
            return nhanVien;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Tim nhan vien co luong cao nhat theo tung phong ban
    public NhanVien timNVLuongMax(int maPB) {
        NhanVien nhanVien = new NhanVien();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT*FROM nhanVien WHERE maPB=" + maPB + " AND hoatDongNV = true" +
                          " ORDER BY luong DESC LIMIT 0,1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Integer maNV = rs.getInt("maNV");
                String ten = rs.getString("ten");
                String ho = rs.getString("ho");
                String gioiTinh = rs.getString("gioiTinh");
                String tuoi = rs.getString("tuoi");
                String Email = rs.getString("Email");
                String soDT = rs.getString("soDT");
                Double luong = rs.getDouble("luong");
                Integer maPB1 = rs.getInt("maTruongPhong");
                Integer maTuongPhong = rs.getInt("maPB");
                nhanVien = new NhanVien(maNV, ten, ho, gioiTinh, tuoi, Email, soDT, luong, maPB1, maTuongPhong);
            }
            return nhanVien;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Tim nhan vien
//    public <T> Employees showOneEm(T x, T y) {
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            boolean cond = true;
//            conn = Connect.getInstance().getConnection();
//            stmt = conn.createStatement();
//            String sql = "SELECT*FROM employees WHERE " + x + "='" + y+ "'";
//            ResultSet rs = stmt.executeQuery(sql);
//            Employees e = null;
//            while (cond) {
//                if (rs.next()) {
//                    Integer emCode = rs.getInt("employeeCode");
//                    String firstName = rs.getString("firstName");
//                    String lastName = rs.getString("lastName");
//                    String Gender = rs.getString("Gender");
//                    Integer Age = rs.getInt("Age");
//                    String Email = rs.getString("Email");
//                    String Phone = rs.getString("Phone");
//                    Integer maID = rs.getInt("managerID");
//                    Double salary = rs.getDouble("Salary");
//                    Integer depID = rs.getInt("departmentID");
//                    e = new Employees(emCode, firstName, lastName, Gender, Age, Email, Phone, maID, salary, depID);
//                    cond = false;
//                }else {
//                    System.out.println("Employee not found. Please enter a valid ID or enter 'exit' to exit");
//                    cond = false;
//                }
//            }
//            return e;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (stmt != null){
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            if (conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
}
