package Sevices;

import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import Model.PhongBan;

import java.util.List;
import java.util.Scanner;

public class PhongBanSevices {
    public PhongBanDAO phongBanDAO;
    public PhongBanSevices(){
        phongBanDAO = new PhongBanDAO();
    }
    public static NhanVienDAO nhanVienDAO = new NhanVienDAO();
    public static PhongBan phongBan = new PhongBan();
    public static Scanner in = new Scanner(System.in);

    //1.(MENU DEP) them phong ban
    public void themPhongBan(){
        System.out.println("___TAO_PHONG_BAN_MOI___");
        System.out.print("Ten phong ban: ");
        String tenPB = in.nextLine();
        while (phongBan.setTenPB(tenPB) == false){
            System.out.println("Ten khong hop le, vui long nhap lai.");
            System.out.print("Ten phong ban: ");
            tenPB = in.nextLine();
        }
        System.out.print("Mo ta: ");
        String moTa = in.nextLine();
        while (phongBan.setMoTa(moTa) == false){
            System.out.println("Mo ta khong hop le, vui long nhap lai.");
            System.out.print("Mo ta: ");
            moTa = in.nextLine();
        }
        PhongBan newPb = new PhongBan(tenPB, moTa);
        phongBanDAO.themPB(newPb);
        System.out.println("Them phong ban thanh cong.");
        dsPhongBan();
    }

    //2.(MENU DEP) sua phong ban
    public void suaDoiPB() {
        boolean cond = true;
        boolean cond1 = true;
        do {
            dsPhongBan();
            System.out.print("Nhap maPB cua phong ban ban muon sua doi thong tin hoac nhap 'Thoat' de dung sua doi: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (phongBan.setMaPB(str) == true) {
                Integer maPB = Integer.parseInt(str);
                PhongBan phongBan1 = phongBanDAO.timKiemPB("maPB", maPB);
                if (phongBan1.getMaPB() == 0) {
                    System.out.println("Khong tim thay phong ban, vui long nhap lai.");
                    continue;
                } else {
                    do {
                        System.out.println("___SUA_DOI_THONG_TIN_PHONG_BAN___");
                        System.out.println("1. Sua ten phong ban: ");
                        System.out.println("2. Sua mo ta ve phong ban: ");
                        System.out.println("3. Dung sua doi");
                        System.out.print("Nhap lua chon cua ban: ");
                        String chon = in.nextLine();
                        switch (chon) {
                            case "1":
                                System.out.print("Ten phong ban moi: ");
                                String tenMoi = in.nextLine();
                                while (phongBan.setTenPB(tenMoi) == false){
                                    System.out.println("Ten khong hop le, vui long nhap lai.");
                                    System.out.print("Ten phong ban: ");
                                    tenMoi = in.nextLine();
                                }
                                phongBanDAO.suaPB("tenPB", tenMoi, "maPB", maPB);
                                System.out.println("Sua ten phong ban thanh cong.");
                                break;
                            case "2":
                                System.out.print("Mo ta moi ve phong ban: ");
                                String moTa = in.nextLine();
                                while (phongBan.setMoTa(moTa) == false){
                                    System.out.println("Ho khong hop le, vui long nhap lai.");
                                    System.out.print("Mo ta: ");
                                    moTa = in.nextLine();
                                }
                                phongBanDAO.suaPB("moTa", moTa, "maPB", maPB);
                                System.out.println("Sua mo ta ve phong ban thanh cong.");
                                break;
                            case "3":
                                System.out.println("Dung sua doi thong tin phong ban.");
                                cond1 = false;  // Thoat khoi vong lap do...while
                                break;
                            default:
                                System.out.println("Lua chon khong hop le, vui long nhap lai:");
                        }
                    } while (cond1);
                }
            }
        }while (cond);
    }

    //3.(MENU DEP) Xoa phong ban
    public void xoaPB(){
        boolean cond = true;
        do {
            dsPhongBan();
            System.out.print("Nhap maPB cua phong ban ma ban muon xoa hoac nhap 'Thoat' de dung xoa: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (phongBan.setMaPB(str) == true) {
                Integer xoaPB = Integer.parseInt(str);
                PhongBan phongBan1 = phongBanDAO.timKiemPB("maPB", xoaPB);
                if (phongBan1.getMaPB() == 0) {
                    System.out.println("Khong tim thay phong ban, vui long nhap lai.");
                    continue;
                } else {
                    phongBanDAO.xoaPB(xoaPB);
                    nhanVienDAO.suaNVtrongPB("maTruongPhong", null, "maPB", xoaPB); // xoa maTruongPhong ve nulln
                    nhanVienDAO.suaNVtrongPB("maPB", null, "maPB", xoaPB);  // Xoa maPB cac nhan vien trong phong ban do
                    //phongBanDAO.suaPB("maTruongPhong", null,"maPB", xoaPB);  // xoa maTruongPhong của phòng ban đó
                    System.out.println("Xoa phong ban thanh cong");
                }
            }
        }while (cond);
    }


    //4.(MENU DEP) In danh sách các phòng ban
    public void dsPhongBan(){
        List<PhongBan> dep = phongBanDAO.dsPhongBan();
        System.out.println("Danh sach cac phong ban.");
        dep.stream().forEach(s -> System.out.println(s + " "));
    }
}
