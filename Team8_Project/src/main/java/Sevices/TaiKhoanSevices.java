package Sevices;

import DAO.TaiKhoanDAO;
import Model.TaiKhoan;

import java.util.Scanner;

public class TaiKhoanSevices {
    public TaiKhoanDAO taiKhoanDAO;
    public static Scanner in = new Scanner(System.in);
    private static TaiKhoan taiKhoan = new TaiKhoan();
    public TaiKhoanSevices(){
        taiKhoanDAO = new TaiKhoanDAO();
    }
    public void suaDoiTK(){
        boolean cond = true;
        boolean cond1 = true;
        do {
            System.out.print("Nhap maTK cua tai khoan ban muon sua doi thong tin hoac nhap 'Thoat' de dung sua doi: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (taiKhoan.setMaTK(str) == true) {
                Integer maTK = Integer.parseInt(str);
                TaiKhoan taiKhoan1 = taiKhoanDAO.timTK(maTK);
                if (taiKhoan1.getMaTK() == 0){
                    System.out.println("Khong tim thay ma tai khoan, vui long nhap lai.");
                    continue;
                }else {
                    do {
                        System.out.println("___DOI_THONG_TIN_TAI_KHOAN___");
                        System.out.println("1. Sua doi ten tai khoan");
                        System.out.println("2. Sua doi mat khau");
                        System.out.println("3. Dung sua tai khoan");
                        System.out.print("Nhap lua chon cua ban: ");
                        String chon = in.nextLine();
                        switch (chon) {
                            case "1":
                                System.out.print("Ten tai khoan moi: ");
                                String tenMoi = in.nextLine();
                                while (taiKhoan.setTenTK(tenMoi) == false) {
                                    System.out.println("Ten tai khoan khong hop le, vui long nhap lai.");
                                    System.out.print("Ten tai khoan: ");
                                    tenMoi = in.nextLine();
                                }
                                taiKhoanDAO.suaTK("tenTK", tenMoi, maTK);
                                System.out.println("Sua ten tai khoan thanh cong.");
                                break;
                            case "2":
                                System.out.print("Mat khau moi: ");
                                String mkMoi = in.nextLine();
                                while (taiKhoan.setMaKhau(mkMoi) == false) {
                                    System.out.println("Mat khau khong hop le, vui long nhap lai.");
                                    System.out.print("Mat khau: ");
                                    mkMoi = in.nextLine();
                                }
                                taiKhoanDAO.suaTK("matKhau", mkMoi, maTK);
                                System.out.println("Doi mat khau thanh cong.");
                                break;
                            case "3":
                                cond = false;
                                break;
                            default:
                                System.out.println("Lua chon khong hop le, vui long nhap lai:");
                        }
                    } while (cond1);
                }
            }
        }while (cond);
    }

    //Them tai khoan
    public void themTK(){
        System.out.print("Ten tai khoan: ");
        String tenTK = in.nextLine();
        while (taiKhoan.setTenTK(tenTK) == false){
            System.out.println("Ten tai khoan khong hop le, vui long nhap lai.");
            System.out.print("Ten tai khoan: ");
            tenTK = in.nextLine();
        }
        System.out.print("Mat khau: ");
        String matKhau = in.nextLine();
        while (taiKhoan.setMaKhau(matKhau) == false){
            System.out.println("Mat khau khong hop le, vui long nhap lai.");
            System.out.print("Mat khau: ");
            matKhau = in.nextLine();
        }
        TaiKhoan ac = new TaiKhoan(tenTK, matKhau);
        taiKhoanDAO.themTK(ac);
        System.out.println("Them tai khoan thanh cong");
    }

    //Xoa tai khoan
    public void xoaTK(){
        boolean cond = true;
        do {
            System.out.print("Nhap ma tai khoan ban muon xoa hoac nhap 'Thoat' de dung xoa tai khoan: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")) {
                cond = false;
                break;
            }
            if (taiKhoan.setMaTK(str) == true) {
                Integer maTK = Integer.parseInt(str);
                taiKhoanDAO.xoaTK(maTK);
                System.out.println("Xoa tai khoan thanh cong");
            }
        }while (cond);
    }
}
