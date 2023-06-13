package DAO;

import Connect.Connect;
import Model.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaiKhoanDAO {
    public static Scanner in = new Scanner(System.in);
    public <T> TaiKhoan dangNhap(T x, T y){
        TaiKhoan taiKhoan = new TaiKhoan();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM taiKhoan WHERE tenTK = '" + x + "' AND matKhau = '" + y + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int maTK = rs.getInt("maTK");
                String tenTK = rs.getString("tenTK");
                String matKhau = rs.getString("matKhau");
                taiKhoan = new TaiKhoan(maTK, tenTK, matKhau);
            }
            return taiKhoan;
        } catch (SQLException e) {
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

    public TaiKhoan timTK(int maTK){
        TaiKhoan taiKhoan = new TaiKhoan();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM taiKhoan WHERE maTK = " + maTK;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int maTK1 = rs.getInt("maTK");
                String tenTK = rs.getString("tenTK");
                String matKhau = rs.getString("matKhau");
                taiKhoan = new TaiKhoan(maTK1, tenTK, matKhau);
            }
            return taiKhoan;
        } catch (SQLException e) {
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

    // sua thong tin tai khoan
    public <T> void suaTK(T x, T y, int maTK){
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE taiKhoan SET " + x + "='" + y + "' WHERE maTK = " + maTK;
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

    // them tai khoan
    public void themTK(TaiKhoan taiKhoan){
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "INSERT INTO taiKhoan(tenTK, matKhau) VALUES(?,?)";
            prst = conn.prepareStatement(sql);
            prst.setString(1, taiKhoan.getTenTK());
            prst.setString(2, taiKhoan.getMaKhau());
            prst.executeUpdate();
        } catch (Exception e) {
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

    // xoa tai khoan
    public void xoaTK(int maTK){
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM taiKhoan WHERE maTK = " + maTK;
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

    //Danh sach tk
    public List<TaiKhoan> dsTaiKhoan(){
        List<TaiKhoan> dsTK = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT*FROM taiKhoan";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                TaiKhoan tk = new TaiKhoan(
                        rs.getInt("maTK"),
                        rs.getString("tenTK"),
                        rs.getString("matKhau")
                );
                dsTK.add(tk);
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
        return dsTK;
    }
}
