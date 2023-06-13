package Main;

import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import Model.NhanVien;
import Model.TaiKhoan;
import Sevices.NhanVienSevices;
import Sevices.PhongBanSevices;
import Sevices.TaiKhoanSevices;

import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static TaiKhoanSevices tK = new TaiKhoanSevices();
    public static  TaiKhoan taiKhoan = new TaiKhoan();
    public static NhanVienSevices nV = new NhanVienSevices();
    //public static NhanVienDAO nhanVienDAO = new NhanVienDAO();
    public static PhongBanSevices pB = new PhongBanSevices();
    public static void xoaNV(){
        boolean cond = true;
        do {
            System.out.println("___XOA_NHAN_VIEN___");
            System.out.println("1. Xoa nhan vien khong phai truong phong");
            System.out.println("2. Xoa nhan vien la truong phong");
            System.out.println("3. Dung xoa nhan vien");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon){
                case "1":
                    nV.xoaNvBt();
                    break;
                case "2":
                    nV.xoaNvlaTruongPhong();
                    break;
                case "3":
                    cond = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai:");
            }

        }while (cond);
    }

    public static void xoaNVkhoiPB(){
        boolean cond = true;
        do {
            System.out.println("___XOA_NHAN_VIEN_KHOI_PHONG_BAN___");
            System.out.println("1. Xoa nhan vien khong phai truong phong khoi phong ban.");
            System.out.println("2. Xoa nhan vien la truong phong khoi phong ban.");
            System.out.println("3. Dung xoa nhan vien khoi phong ban.");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon){
                case "1":
                    pB.dsPhongBan();
                    nV.xoaNVkhoiPB();
                    break;
                case "2":
                    pB.dsPhongBan();
                    nV.xoaTruongPhongKhoiPB();
                    break;
                case "3":
                    cond = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai:");
            }
        }while (cond);
    }

    public static void themNVvaoPB(){
        boolean cond = true;
        do {
            System.out.println("___Them_nhan_vien_vao_phong_ban___");
            System.out.println("1. Them nhan vien cho phong ban");
            System.out.println("2. Them truong phong cho phong ban");
            System.out.println("3. Dung them nhan vien");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon){
                case "1":
                    pB.dsPhongBan();
                    nV.themNVvaoPB();
                    break;
                case "2":
                    nV.themTruongPhongVaoPB();
                    break;
                case "3":
                    cond = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai:");
            }

        }while (cond);
    }

    // MENU EMPLOYEES
    public static void nhanVien(){
        boolean cond = true;
        do {
            System.out.println("___NHAN_VIEN___");
            System.out.println("1. Them nhan vien moi");
            System.out.println("2. Sua thong tin nhan vien");
            System.out.println("3. Xoa nhan vien");
            System.out.println("4. In danh sach cac nhan vien");
            System.out.println("5. Tim kiem mot nhan vien");
            System.out.println("6. Tim nhan vien co muc luong cao nhat theo tung phong ban.");
            System.out.println("7. Thong ke cac nhan vien trong mot phong ban.");
            System.out.println("8. Tinh thue thu nhap ca nhan cho mot nhan vien.");
            System.out.println("9. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon) {
                case "1":
                    nV.themNV();
                    break;
                case "2":
                    nV.suaTtNv();
                    break;
                case "3":
                    xoaNV();
                    break;
                case "4":
                    nV.inDsNhanVien();
                    break;
                case "5":
                    nV.timNhanVien();
                    break;
                case "6":
                    pB.dsPhongBan();
                    nV.nvLuongMax();
                    break;
                case "7":
                    pB.dsPhongBan();
                    nV.thongKePB();
                    break;
                case "8":
                    nV.thueThuNhapCN();
                    break;
                case "9":
                    cond = false;  // thoat menu em
                    break;
                default:
                    System.out.println("Lua chon kong hop le, vui long nhap lai:");
            }
        }while (cond);
    }

    // MENU DEPARTMENTS
    public static void phongBan(){
        boolean cond = true;
        do {
            System.out.println("___PHONG_BAN___");
            System.out.println("1. Them phong ban moi");
            System.out.println("2. Sua thong tin phong ban");
            System.out.println("3. Xoa phong ban");
            System.out.println("4. In danh sach cac phong ban");
            System.out.println("5. Them nhan vien vao phong ban");
            System.out.println("6. Xoa nhan vien khoi phong ban");
            System.out.println("7. Chuyen doi phong ban cho nhan vien");
            System.out.println("8. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon) {
                case "1":
                    pB.themPhongBan();
                    break;
                case "2":
                    pB.suaDoiPB();
                    break;
                case "3":
                    pB.xoaPB();  // Xoa phong ban
                    break;
                case "4":
                    pB.dsPhongBan();
                    break;
                case "5":
                    themNVvaoPB();
                    break;
                case "6":
                    xoaNVkhoiPB();
                    break;
                case "7":
                    pB.dsPhongBan();
                    nV.chuyenDoiPBchoNV();
                    break;
                case "8":
                    cond = false;  // Thoat menu Dep
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai:");
            }
        }while (cond);
    }

    public static void taiKhoan(){
        boolean cond = true;
        do {
            System.out.println("___TAI_KHOAN___");
            System.out.println("1. Them tai khoan moi");
            System.out.println("2. Sua thong tin tai khoan");
            System.out.println("3. Xoa tai khoan");
            System.out.println("4. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon) {
                case "1":
                    tK.themTK();
                    break;
                case "2":
                    tK.suaDoiTK();
                    break;
                case "3":
                    tK.xoaTK();
                    break;
                case "4":
                    cond = false;  // Thoat menu Dep
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai:");
            }
        }while (cond);
    }

    // MENU chinh
    public static void main(String[] args) {
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        boolean cond = true;
        boolean cond1 = true;
        // OK
        do {
            System.out.println("___DANG_NHAP___");
            System.out.print("Ten tai khoan: ");
            String tenTK = in.nextLine();
            while (taiKhoan.setTenTK(tenTK) == false){
                System.out.println("Ten tai khoan khong duoc de trong, vui long nhap lai. ");
                System.out.print("Ten tai khoan: ");
                tenTK = in.nextLine();
            }
            System.out.print("Mat khau: ");
            String mk = in.nextLine();
            while (taiKhoan.setMaKhau(mk) == false){
                System.out.println("Mat khau khong duoc de trong, vui long nhap lai. ");
                System.out.print("Mat khau: ");
                mk = in.nextLine();
            }
            TaiKhoan tk = taiKhoanDAO.dangNhap(tenTK, mk);
            //System.out.println(ac.toString());
            if (tk.getMaTK() == 0) {  // không có tk nào có id = 0
                System.out.println("Dang nhap that bai, ten tai khoan hoac mat khau bi sai.");
                continue;
            } else {
                System.out.println("Dang nhap thanh cong.");
                do{
                    System.out.println("___QUAN_LY_NHAN_SU___");
                    System.out.println("1. NHAN VIEN");
                    System.out.println("2. PHONG BAN");
                    System.out.println("3. TAI KHOAN");
                    System.out.println("4. DANG XUAT");
                    System.out.print("Nhap lua chon cua ban: ");
                    String chon = in.nextLine();
                        switch (chon) {
                            case "1":
                                nhanVien();
                                break;
                            case "2":
                                phongBan();
                                break;
                            case "3":
                                taiKhoan();
                                break;
                            case "4":
                                cond1 = false;  // Log out, quay lại đăng nhập
                                break;
                            default:
                                System.out.println("Lua chon khong hop le, vui long nhap lai:");
                    }
                }while (cond1);
            }
        }while (cond);
    }
}
