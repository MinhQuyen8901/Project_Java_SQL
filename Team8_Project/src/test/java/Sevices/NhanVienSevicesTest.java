package Sevices;

import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import Model.NhanVien;
import Model.PhongBan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NhanVienSevicesTest {
    public static Scanner in = new Scanner(System.in);
    private NhanVien nhanVien;
    private NhanVienDAO nhanVienDAO;
    @BeforeEach
    public void setUp(){
        nhanVien = new NhanVien();
        nhanVienDAO = new NhanVienDAO();
    }

    @Test
    public void testThemNv(){
        String ten = "";
        String ho = "Nguyen";
        String gioiTinh = "Nam";
        String tuoi = "25";
        String email = "a@gmail.com";
        String sdt = "0987654356";
        double luong = 15000000;
        int mTP = 0;
        int maPB = 0;
        NhanVien nv = new NhanVien(ten, ho, gioiTinh, tuoi, email,
                sdt, luong, mTP, maPB);
        nhanVienDAO.themNV(nv);
        assertEquals("Nguyen", nv.getHo());
    }

}