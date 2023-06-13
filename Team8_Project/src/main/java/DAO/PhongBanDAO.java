package DAO;

import Connect.Connect;
import Model.NhanVien;
import Model.PhongBan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDAO {
    //Them phong ban
    public void themPB(PhongBan pB){
        Connection conn = null;
        PreparedStatement prst = null;
        try {
            conn = Connect.getInstance().getConnection();
            String sql = "INSERT INTO phongBan(tenPB, moTa, maTruongPhong, hoatDongPB) " +
                    "VALUES (?,?,null,true)";
            prst = conn.prepareStatement(sql);
            prst.setString(1, pB.getTenPB());
            prst.setString(2, pB.getMoTa());
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

    // sua phong ban
    public <T> void suaPB(T a, T b, T c, T d){
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE phongBan SET " + a + "='" + b + "' WHERE " + c + "="+ d;
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

    // xoa phong ban
    public void xoaPB(int maPB){
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "UPDATE phongBan SET hoatDongPB = false WHERE maPB="+ maPB;
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

    // show cac phong ban
    public List<PhongBan> dsPhongBan(){
        List<PhongBan> pB = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Connect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT*FROM phongBan WHERE hoatDongPB = true";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                PhongBan show = new PhongBan(
                        rs.getInt("maPB"),
                        rs.getString("tenPB"),
                        rs.getString("moTa"),
                        rs.getInt("maTruongPhong"));
                pB.add(show);
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
        return pB;
    }


    // Tim phong ban
    public <T> PhongBan timKiemPB(T x, T y) {
        PhongBan phongBan = new PhongBan();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Connect.getInstance().getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT*FROM phongBan WHERE " + x + "='" + y+ "' AND hoatDongPB = true";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Integer maPB = rs.getInt("maPB");
                String tenPB = rs.getString("tenPB");
                String moTa = rs.getString("moTa");
                Integer maTruongPhong = rs.getInt("maTruongPhong");
                phongBan = new PhongBan(maPB,tenPB,moTa,maTruongPhong);
            }
            return phongBan;
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
}
