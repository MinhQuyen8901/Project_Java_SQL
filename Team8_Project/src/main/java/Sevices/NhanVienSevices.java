package Sevices;

import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import Model.NhanVien;
import Model.PhongBan;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NhanVienSevices {
    public NhanVienDAO nhanVienDAO;
    public static Scanner in = new Scanner(System.in);
    public NhanVienSevices(){
        nhanVienDAO = new NhanVienDAO();
    }
    public static PhongBanDAO phongBanDAO = new PhongBanDAO();
    public static NhanVien nhanVien = new NhanVien();
    public static PhongBan phongBan = new PhongBan();

    //1.(MENU EM) them nhan vien moi
    public void themNV() {
        System.out.println("___THEM_NHAN_VIEN___");
        System.out.print("Ten nhan vien: ");
        String ten = in.nextLine();
        while (nhanVien.setTen(ten) == false) {  // neu bo trong
            System.out.println("Ten khong hop le, vui long nhap lai.");
            System.out.print("Ten nhan vien: ");
            ten = in.nextLine();  // cho phep nhap lai, nhap dung ms thoat vong lap cho nhap thong tin khac
        }
        System.out.print("Ho nhan vien: ");
        String ho = in.nextLine();
        while (nhanVien.setHo(ho) == false) {
            System.out.println("Ho khong hop le, vui long nhap lai.");
            System.out.print("Ho nhan vien: ");
            ho = in.nextLine();
        }
        System.out.print("Gioi tinh: ");
        String gioiTinh = in.nextLine();
        while (nhanVien.setGioiTinh(gioiTinh.toLowerCase()) == false) {
            System.out.println("Gioi tinh khong hop le, vui long nhap lai.");
            System.out.print("Gioi tinh: ");
            gioiTinh = in.nextLine();
        }
        System.out.print("Tuoi cua nhan vien: ");
        String tuoi = in.nextLine();
        while (nhanVien.setTuoi(tuoi) == false) {
            System.out.println("Tuoi khong hop le, vui long nhap lai.");
            System.out.print("Tuoi cua nhan vien: ");
            tuoi = in.nextLine();
        }
        System.out.print("Email cua nhan vien: ");
        String Email = in.nextLine();
        while (nhanVien.setEmail(Email) == false) {
            System.out.println("Email khong hop le, vui long nhap lai.");
            System.out.print("Email cua nhan vien: ");
            Email = in.nextLine();
        }
        System.out.print("So dien thoai cua nhan vien: ");
        String soDT = in.nextLine();
        while (nhanVien.setSoDT(soDT) == false) {
            System.out.println("So dien thoai khong hop le, vui long nhap lai.");
            System.out.print("So dien thoai cua nhan vien: ");
            soDT = in.nextLine();
        }
        System.out.print("Luong cua nhan vien: ");  //ang loi can xem lai.
        String str = in.nextLine();
        while (nhanVien.setLuong(str) == false) {
            System.out.println("Luong khong hop le, vui long nhap lai.");
            System.out.print("Luong cua nhan vien: ");
            str = in.nextLine();
        }
        double luong = Double.parseDouble(str);  // nhanVien.docLuong(str) == true
        int maTruongPhong = 0;
        int maPB = 0;
        NhanVien nv = new NhanVien(ten, ho, gioiTinh, tuoi, Email, soDT, luong, maTruongPhong, maPB);
        nhanVienDAO.themNV(nv);
        System.out.println("Them nhan vien moi thanh cong.");
        inDsNhanVien(); // in lai danh sach sau khi them nv moi
    }

    //2.(MENU EM) Sua thong tin nhan vien
    public void suaTtNv(){
        boolean cond = true;
        do {
            inDsNhanVien();
            System.out.print("Nhap maNV ban muon sua doi thong tin hoac nhap 'Thoat' de dung sua thong tin: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){  // dau vao chuyen thanh chu thuong (toLowerCase) va bang chuoi "thoat"
                cond = false;
                break;
            }
            if (nhanVien.setMaNV(str) == true){
                Integer maNV = Integer.parseInt(str);
                NhanVien nv = nhanVienDAO.timKiemNV("maNV", maNV);
                if (nv.getMaNV() == 0) {    // k ton tai nhan vien
                    System.out.println("Khong tim thay nhan vien, vui long nhap lai.");
                    continue;
                } else {
                    boolean cond1 = true;
                    do {
                        System.out.println("___SUA_THONG_TIN_NHAN_VIEN___");
                        System.out.println("1. Sua ten.");
                        System.out.println("2. Sua ho");
                        System.out.println("3. Sua gioi tinh");
                        System.out.println("4. Sua tuoi");
                        System.out.println("5. Sua Email");
                        System.out.println("6. Sua so dien thoai");
                        System.out.println("7. Sua luong");
                        System.out.println("8. Dung sua");
                        System.out.print("Nhap lua chon cua ban: ");
                        String chon = in.nextLine();
                        switch (chon) {
                            case "1":
                                System.out.print("Ten moi: ");
                                String newTen = in.nextLine();
                                while (nhanVien.setTen(newTen) == false) {  // neu bo trong
                                    System.out.println("Ten khong hop le, vui long nhap lai.");
                                    System.out.println("Ten moi: ");
                                    newTen = in.nextLine();  // cho phep nhap lai, nhap dung ms thoat vong lap cho nhap thong tin khac
                                }
                                nhanVienDAO.suaNV("ten", newTen, maNV);
                                System.out.println("Sua ten thanh cong.");
                                break;
                            case "2":
                                System.out.print("Ho moi: ");
                                String newHo = in.nextLine();
                                while (nhanVien.setTen(newHo) == false) {  // neu bo trong
                                    System.out.println("Ho khong hop le, vui long nhap lai.");
                                    System.out.print("Ho moi: ");
                                    newHo = in.nextLine();  // cho phep nhap lai, nhap dung ms thoat vong lap cho nhap thong tin khac
                                }
                                nhanVienDAO.suaNV("ho", newHo, maNV);
                                System.out.println("Sua ho thanh cong.");
                                break;
                            case "3":
                                System.out.print("Gioi tinh moi: ");
                                String gioiTinh = in.nextLine();
                                while (nhanVien.setGioiTinh(gioiTinh.toLowerCase()) == false) {
                                    System.out.println("Gioi tinh khong hop le, vui long nhap lai.");
                                    System.out.print("Gioi tinh moi: ");
                                    gioiTinh = in.nextLine();
                                }
                                nhanVienDAO.suaNV("gioiTinh", gioiTinh, maNV);
                                System.out.println("Sua gioi tinh thanh cong.");
                                break;
                            case "4":
                                System.out.print("Tuoi moi: ");
                                String tuoi = in.nextLine();
                                while (nhanVien.setTuoi(tuoi) == false) {
                                    System.out.println("Tuoi khong hop le, vui long nhap lai.");
                                    System.out.print("Tuoi moi: ");
                                    tuoi = in.nextLine();
                                }
                                nhanVienDAO.suaNV("tuoi", tuoi, maNV);
                                System.out.println("Sua tuoi thanh cong.");
                                break;
                            case "5":
                                System.out.print("Email moi: ");
                                String Email = in.nextLine();
                                while (nhanVien.setEmail(Email) == false) {
                                    System.out.println("Email khong hop le, vui long nhap lai.");
                                    System.out.print("Email moi: ");
                                    Email = in.nextLine();
                                }
                                nhanVienDAO.suaNV("Email", Email, maNV);
                                System.out.println("Sua email thanh cong.");
                                break;
                            case "6":
                                System.out.print("So dien thoai moi: ");
                                String soDT = in.nextLine();
                                while (nhanVien.setSoDT(soDT) == false) {
                                    System.out.println("So dien thoai khong hop le, vui long nhap lai.");
                                    System.out.print("So dien thoai moi: ");
                                    soDT = in.nextLine();
                                }
                                nhanVienDAO.suaNV("soDT", soDT, maNV);
                                System.out.println("Sua so dien thoai thanh cong.");
                                break;
                            case "7":
                                System.out.print("Luong moi: ");
                                String string = in.nextLine();
                                while (nhanVien.setLuong(string) == false) {
                                    System.out.println("Luong khong hop le, vui long nhap lai.");
                                    System.out.print("Luong cua nhan vien: ");
                                    str = in.nextLine();
                                }
                                double luong = Double.parseDouble(string);  // nhanVien.docLuong(str) == true
                                nhanVienDAO.suaNV("luong", luong, maNV);
                                System.out.println("Sua luong thanh cong.");
                                break;
                            case "8":
                                System.out.println("Dung sua thong tin");
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



    // 3.(MENU EM) Xoa nhan vien
    // 3.1. Xoa tat ca cac nhan vien co truong phong bi xoa
    public void nVlaTruongphong(){
        List<NhanVien> nv = nhanVienDAO.dsNhanVien();  // lay ds hien tai
        List<NhanVien> nvLaTP = nv.stream().filter(s -> s.getMaTruongPhong() == 1).collect(Collectors.toList());  // tim truong phongm truong phong co maTruongPhong = 1.
        System.out.println("Danh sach cac nhan vien la truong phong.");
        nvLaTP.stream().forEach(s -> System.out.println(s + " "));   //in danh sach nv la truong phong
    }
    public void xoaNvlaTruongPhong(){
        boolean cond = true;
        do {
            nVlaTruongphong();
            System.out.print("Nhap maNV truong phong ban muon xoa hoac nhap 'Thoat' de dung xoa: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (nhanVien.setMaNV(str) == true) {
                Integer xoaMa = Integer.parseInt(str);
                NhanVien nv = nhanVienDAO.timKiemNV("maNV", xoaMa);
                if (nv.getMaTruongPhong() != 1) {
                    System.out.println("Khong tim thay truong phong, vui long nhap lai.");
                    continue;
                } else {
                    nhanVienDAO.suaNVtrongPB("hoatDongNV", false, "maNV", xoaMa);  // xoa truong phong
                    nhanVienDAO.suaNVtrongPB("maTruongPhong", null, "maTruongPhong", xoaMa);  // xoa cac nhan vien có truong phong tren
                    phongBanDAO.suaPB("maTruongPhong", 0, "maTruongPhong", xoaMa); //Xoa ma thuong phong cho phong ban
                    System.out.println("Xoa truong phong thanh cong.");
                }
            }
        }while (cond);
    }

    // 3.2. Xoa nhan vien bt

    public void nVBinhThuong(){
        List<NhanVien> nv = nhanVienDAO.dsNhanVien();  // lay ds hien tai
        List<NhanVien> nvKhongPhaiTP = nv.stream().filter(s -> s.getMaTruongPhong() != 1).collect(Collectors.toList());
        System.out.println("Danh sach nhan vien khong phai truong phong.");
        nvKhongPhaiTP.stream().forEach(s -> System.out.println(s + " ")); // in ds nv binh thuong
    }
    public void xoaNvBt(){
        boolean cond = true;
        do {
            nVBinhThuong();
            System.out.print("Nhap maNV cua nhan vien ban muon xoa hoac nhap 'Thoat' de dung xoa: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (nhanVien.setMaNV(str) == true) {
                Integer xoaMa = Integer.parseInt(str);
                NhanVien nv = nhanVienDAO.timKiemNV("maNV", xoaMa);  // doc nhan vien có maNV = xoaMa
                if (nv.getMaNV() == 0 || nv.getMaTruongPhong() == 1) {  // khong thay nhan vien hoac tim thay nvBt k phai TP
                    System.out.println("Khong tim thay nhan vien, vui long nhap lai.");
                    continue;
                } else {
                    nhanVienDAO.suaNVtrongPB("hoatDongNV", false, "maNV", xoaMa);  // Gan ve false de xoa.
                    System.out.println("Xoa nhan vien thanh cong");
                }
            }
        }while (cond);
    }

    //4.(MENU EM) in danh sach
    public void inDsNhanVien(){
        List<NhanVien> nv = nhanVienDAO.dsNhanVien();
        System.out.println("Danh sach tat ca cac nhan vien.");
        nv.stream().forEach(s -> System.out.println(s + " "));
    }

    //5.(MENU EM) Tim kiem 1 nhan vien
    public void timNhanVien(){
        boolean cond = true;
        do {
            System.out.println("___TIM_KIEM_NHAN_VIEN___");
            System.out.println("1. Tim theo ma nhan vien.");
            System.out.println("2. Tim theo ten nhan vien.");
            System.out.println("3. Tim theo so dien thoai.");
            System.out.println("4. Tim theo Email.");
            System.out.println("5. Dung tim nhan vien");
            System.out.print("Nhap lua chon cua ban: ");
            String chon = in.nextLine();
            switch (chon){
                case "1":
                    boolean t1 = true;
                    while (t1) {
                        System.out.print("Nhap maNV cua nhan vien hoac nhap 'Thoat' de dung tim kiem: ");
                        String str = in.nextLine();
                        if (str.toLowerCase().equals("thoat")){
                            t1 = false;
                            break;
                        }
                        if (nhanVien.setMaNV(str) == true) {
                            Integer maNV = Integer.parseInt(str);
                            NhanVien nv = nhanVienDAO.timKiemNV("maNV", maNV);
                            if (nv.getMaNV() == 0) {
                                System.out.println("Khong tim thay nhan vien, vui long nhap lai.");
                            } else {
                                System.out.println(nv.toString());
                            }
                        }
                    }
                    break;
                case "2":
                    boolean t2 = true;
                    while (t2) {
                        System.out.print("Nhap ten cua nhan vien hoac nhap 'Thoat' de dung tim kiem: ");
                        String ten = in.nextLine();
                        NhanVien nv1 = nhanVienDAO.timKiemNV("ten", ten);
                        if (ten.toLowerCase().equals("thoat")) {
                            t2 = false;
                            break;
                        }else if (nv1.getMaNV() == 0) {
                            System.out.println("Khong tim thay nhan vien, vui long nhap lai. ");
                        }else {
                            System.out.println(nv1.toString());
                        }
                    }
                    break;
                case "3":
                    boolean t3 = true;
                    while (t3) {
                        System.out.print("Nhap so dien thoai cua nhan vien hoac nhap 'Thoat' de dung tim kiem: ");
                        String soDT = in.nextLine();
                        NhanVien nv2 = nhanVienDAO.timKiemNV("soDT", soDT);
                        if (soDT.toLowerCase().equals("thoat")) {
                            t3 = false;
                            break;
                        }else if (nv2.getMaNV() == 0) {
                            System.out.println("Khong tim thay nhan vien, vui long nhap lai. ");
                        }else {
                            System.out.println(nv2.toString());
                        }
                    }
                    break;
                case "4":
                    boolean t4 = true;
                    while (t4) {
                        System.out.print("Nhap Email cua nhan vien hoac nhap 'Thoat' de dung tim kiem: ");
                        String Email = in.nextLine();
                        NhanVien nv3 = nhanVienDAO.timKiemNV("Email", Email);
                        if (Email.toLowerCase().equals("thoat")) {
                            t4 = false;
                            break;
                        }else if (nv3.getMaNV() == 0) {
                            System.out.println("Khong tim thay nhan vien, vui long nhap lai. ");
                        }else {
                            System.out.println(nv3.toString());
                        }
                    }
                    break;
                case "5":
                    System.out.println("Exit");
                    cond = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai:");
            }
        }while (cond);
    }

    //6. (MENU EM) Tim nv co luong cao nhat trong cac phong ban
    public void nvLuongMax(){
        boolean cond = true;
        while (cond) {
            System.out.print("Nhap phong ban muon tim nhan vien co muc luong cao nhat hoac nhap 'Thoat' de dung tim kiem:");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (phongBan.setMaPB(str) == true) {
                Integer luongMax = Integer.parseInt(str);
                PhongBan pBan = phongBanDAO.timKiemPB("maPB", luongMax); // Tìm phòng ban
                if (pBan.getMaPB() == 0) {
                    System.out.println("Phong ban khong ton tai, vui long nhap lai."); // neu k ton tai phong ban thi cho nhap lai
                } else {
                    NhanVien nvLuongMax = nhanVienDAO.timNVLuongMax(luongMax);
                    System.out.println("Nhan vien co muc luong cao nhat phong ban " + luongMax + " la:");
                    System.out.println(nvLuongMax.toString());  // hien thi nv lương cao nhat phong ban
                }
            }
        }
    }

    //7. (MENU EM) Thong ke cac nhan vien trong moi phong ban
    public void thongKePB(){
        boolean cond = true;
        do {
            List<NhanVien> nv = nhanVienDAO.dsNhanVien();
            System.out.print("Nhap phong ban ban muon in danh sach nhan vien hoac nhap 'Thoat' de dung in danh sach:");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (phongBan.setMaPB(str) == true) {
                Integer maPB = Integer.parseInt(str);
                PhongBan pBan = phongBanDAO.timKiemPB("maPB", maPB); // Tìm phòng ban
                if (pBan.getMaPB() == 0) {
                    System.out.println("Phong ban khong ton tai, vui long nhap lai."); // neu k ton tai phong ban thi cho nhap lai
                } else {
                    List<NhanVien> truongPhong = nv.stream().filter(s -> s.getMaPB() == maPB && s.getMaTruongPhong() == 1).collect(Collectors.toList());
                    System.out.println("Truong phong cua phong ban " + maPB + " la: ");
                    truongPhong.stream().forEach(s -> System.out.println(s + " "));

                    List<NhanVien> nhanVien = nv.stream().filter(s -> s.getMaPB() == maPB && s.getMaTruongPhong() != 1).collect(Collectors.toList());
                    System.out.println("Danh sach nhan vien cua phong ban " + maPB + " la: ");
                    nhanVien.stream().forEach(s -> System.out.println(s + " "));
                }
            }
        }while (cond);
    }

    //8. Tinh thue thu nhap ca nhan
    public void thueThuNhapCN(){
        boolean cond = true;
        do {
            inDsNhanVien();
            System.out.print("Nhap maNV cua NV ban muon tinh thue thu nhap ca nhan hoac nhap 'Thoat' de dung tinh thue: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")) {
                cond = false;
                break;
            }else {
                if (nhanVien.setMaNV(str) == true) {
                    Integer maNV = Integer.parseInt(str);
                    NhanVien nhanVien1 = nhanVienDAO.timKiemNV("maNV", maNV);
                    if (nhanVien1.getMaNV() == 0){
                        System.out.println("Khong tim thay nhan vien, vui long nhap lai.");
                        continue;
                    }else {
                        double thue = nhanVien.tinhThue(nhanVien1.getLuong());
                        DecimalFormat decimalFormat = new DecimalFormat("#,###");  // định dạng thuế
                        String dangThue = decimalFormat.format(thue);  //
                        System.out.println("Thue thu nhap ca nhan cua nhan vien co maNv=" + maNV + " la: " + dangThue + "VND");
                    }
                }
            }
        }while (cond);
    }


    //5.(MENU DEP) them phong ban cho nhan vien chưa co phong ban

    //5.1.  in danh sách có depid =0; de cho vao phong ban
    public void dsNVchuaCoPB(){
        List<NhanVien> nv = nhanVienDAO.dsNhanVien();  // lay danh sach hien tai
        //tim nhưng ngươi chua cho phong ban tu danh sach hien tai
        List<NhanVien> nvKhongCoPB = nv.stream().filter(s -> s.getMaPB() == 0).collect(Collectors.toList());
        nvKhongCoPB.stream().forEach(s -> System.out.println(s + " "));  // in danh sach chua phong ban
    }
    //5.2. them nhan vien binhthuong vào phong ban
    public void themNVvaoPB(){
        System.out.print("Nhap maPB phong ban muon them nhan vien: ");
        Integer maPB = Integer.parseInt(in.nextLine());

        boolean cond = true;
        do {
            System.out.println("Danh sanh cac nhan vien chua co phong ban.");
            dsNVchuaCoPB();
            System.out.print("Nhap maNV cua nhan vien muon them hoac nhap 'Thoat' de thoat khoi phong ban "+ maPB+" :");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (nhanVien.setMaNV(str) == true) {
                Integer maNV = Integer.parseInt(str);
                NhanVien nhanVien1 = nhanVienDAO.timKiemNV("maNV", maNV);
                if (nhanVien1.getMaNV() == 0 || nhanVien1.getMaPB() == maPB || nhanVien1.getMaPB() != 0) {  // nhan viên bị xóa nhanVien1.isHoatDongNV() == false
                    System.out.println("Nhan vien da o trong phong ban, hoac khong tim thay nhan vien.");
                    System.out.println("Vui long nhap lai.");
                    continue;
                } else {
                    nhanVienDAO.suaNVtrongPB("maPB", maPB, "maNV", maNV);
                    System.out.println("Them nhan vien vao phong ban thanh cong.");

                    System.out.println("Truong phong cua phong ban " + maPB + " la:");
                    List<NhanVien> nv = nhanVienDAO.dsNhanVien();
                    List<NhanVien> truongPhong = nv.stream().filter(s -> s.getMaPB() == maPB && s.getMaTruongPhong() == 1)
                            .collect(Collectors.toList());
                    truongPhong.stream().forEach(s -> System.out.println(s));  //hien thị trương phong

                    System.out.print("Hay nhap maNV cua truong phong neu phong ban chua co truong phong thi nhap 0: ");
                    Integer maTP = Integer.parseInt(in.nextLine());
                    nhanVienDAO.suaNVtrongPB("maTruongPhong", maTP, "maNV", maNV);
                    System.out.println("Them truong phong cho nhan vien thanh cong.");
                }
            }
        }while (cond);
    }
    //5.3. them truong phong vào phong ban
    public void themTruongPhongVaoPB(){

        List<PhongBan> dsPb = phongBanDAO.dsPhongBan();
        List<PhongBan> dsChuaTrPhong = dsPb.stream().filter(s -> s.getMaTrPhong() == 0).collect(Collectors.toList());
        System.out.println("Danh sach cac phong ban chua co truong phong.");
        dsChuaTrPhong.stream().forEach(s -> System.out.println(s + " "));  //In danh sách phong ban chua co truong phong
        System.out.print("Nhap maPB cua phong ban muon them truong phong: ");
        String str = in.nextLine();
        if (phongBan.setMaPB(str) == true) {
            Integer maPB = Integer.parseInt(str);
            System.out.println("Danh sanh cac nhan vien trong phong ban.");
            List<NhanVien> nv = nhanVienDAO.dsNhanVien();  // lay danh sach hien tai
            //Hien thi ds nv trong phong ban
            List<NhanVien> nvCoPB = nv.stream().filter(s -> s.getMaPB() == maPB).collect(Collectors.toList());
            nvCoPB.stream().forEach(s -> System.out.println(s + " "));  // in danh sach nhan vien trong phong ban

            boolean cond = true;
            do {
                System.out.print("Nhap maNV nhan vien len lam truong phong hoac nhap 'Thoat' de dung them truong phong: ");
                String str1 = in.nextLine();
                if (str1.toLowerCase().equals("thoat")){
                    cond = false;
                    break;
                }
                if (nhanVien.setMaNV(str1) == true) {
                    Integer maNV = Integer.parseInt(str1);
                    NhanVien nhanVien1 = nhanVienDAO.timKiemNV("maNV", maNV);
                    if (nhanVien1.getMaNV() == 0 || nhanVien1.getMaPB() != maPB) {
                        System.out.println("Khong tim thay nhan vien trong phong ban nay, vui long nhap lai.");
                        continue;
                    } else {
                        phongBanDAO.suaPB("maTruongPhong", maNV, "maPB", maPB); //Them maTruongPhong cho phong ban do
                        nhanVienDAO.suaNVtrongPB("maTruongPhong", maNV, "maPB", maPB);  //Moi ng trong phòng ban set maTruongPhong = maNv
                        nhanVienDAO.suaNVtrongPB("maTruongPhong", 1, "maNV", maNV); //sau dó set truong phong co maTruongPhong la 1(1 la maNV của chủ tịch)
                        System.out.println("Them truong phong vao phong ban thanh cong.");
                    }
                    List<NhanVien> ds = nhanVienDAO.dsNhanVien();
                    List<NhanVien> dsPBsauKhithemTrP = ds.stream().filter(s -> s.getMaPB() == maPB).collect(Collectors.toList());
                    System.out.println("Danh sach phong ban " + maPB + " sau khi sua doi:");
                    dsPBsauKhithemTrP.stream().forEach(s -> System.out.println(s + " "));  // in danh sach nhan vien trong phong ban
                }
            } while (cond);
        }
    }

    //6.(MENU DEP)  Xoa nhan vien khoi phong ban

    //6.1. Xoa nhan vien khong phải truong phong khoi phong ban
    public void xoaNVkhoiPB(){
        List<NhanVien> nv = nhanVienDAO.dsNhanVien();
        System.out.print("Nhap maPB cua phong ban ban muon xoa nhan vien trong do: ");
        String str = in.nextLine();
        if (phongBan.setMaPB(str) == true) {
            Integer maPB = Integer.parseInt(str);
            List<NhanVien> nvTrongPB = nv.stream().filter(s -> s.getMaPB() == maPB && s.getMaTruongPhong() != 1)
                    .collect(Collectors.toList());
            System.out.println("Danh sach cac nhan vien phong ban " + maPB + " la:");
            nvTrongPB.stream().forEach(s -> System.out.println(s + " "));
            boolean cond = true;
            do {
                System.out.print("Nhap maNV nhan vien muon xoa hoac nhap 'Thoat' de dung xoa nhan vien: ");
                String str1 = in.nextLine();
                if (str1.toLowerCase().equals("thoat")){
                    cond = false;
                    break;
                }
                if (nhanVien.setMaNV(str1) == true) {
                    Integer maNV = Integer.parseInt(str1);
                    NhanVien nhanVien1 = nhanVienDAO.timKiemNV("maNV", maNV);
                    if (nhanVien1.getMaNV() == 0 || nhanVien1.getMaPB() != maPB) {  //|| nhanVien1.isHoatDongNV() == false: lỗi chưa tim duoc cah khac phuc
                        System.out.println("Khong tim thay nhan vien, vui long nhap lai:");
                        continue;
                    } else {
                        nhanVienDAO.suaNVtrongPB("maPB", null, "maNV", maNV);
                        nhanVienDAO.suaNVtrongPB("maTruongPhong", 0, "maNV", maNV);
                        List<NhanVien> emHaveDeAf = nv.stream().filter(s -> s.getMaNV() == maNV)
                                .collect(Collectors.toList());
                        emHaveDeAf.stream().forEach(s -> System.out.println("Xoa nhan vien co maNv: " +
                                s.getMaNV() + " " + s.getTen() + " " + s.getHo() +
                                " khoi phong ban thanh cong"));
                    }
                }
            } while (cond);
        }
    }

    //6.2 Xoa nhan vien la truong phong khoi phong ban
    public void xoaTruongPhongKhoiPB(){
        List<NhanVien> nv = nhanVienDAO.dsNhanVien();
        System.out.print("Nhap maPB cua phong ban ban muon xoa truong.: ");
        String str = in.nextLine();
        if (phongBan.setMaPB(str) == true) {
            Integer maPB = Integer.parseInt(str);
            List<NhanVien> truongPhong = nv.stream().filter(s -> s.getMaPB() == maPB && s.getMaTruongPhong() == 1)
                    .collect(Collectors.toList());
            truongPhong.stream().forEach(s -> System.out.println(s + " "));
            boolean cond = true;
            do {
                System.out.print("Nhap maNV cua truong phong hoac nhap 'Thoat' de dung xoa truong phong: ");
                String str1 = in.nextLine();
                if (str1.toLowerCase().equals("thoat")){
                    cond = false;
                    break;
                }
                if (nhanVien.setMaNV(str1)) {
                    Integer maNV = Integer.parseInt(str1);
                    NhanVien nhanVien1 = nhanVienDAO.timKiemNV("maNV", maNV);
                    //System.out.println(nhanVien1.toString());
                    if (nhanVien1.getMaNV() == 0 || nhanVien1.getMaPB() != maPB || nhanVien1.getMaTruongPhong() != 1) {  //|| nhanVien1.isHoatDongNV() == false: lỗi chưa tim duoc cah khac phuc
                        System.out.println("Khong tim thay truong phong, vui long nhap lai:");
                        continue;
                    } else {
                        phongBanDAO.suaPB("maTruongPhong", 0, "maTruongPhong", maNV); // xoa ma truong phong của phong ban do
                        nhanVienDAO.suaNVtrongPB("maPB", null, "maNV", maNV);  // xoa ma phong ban
                        nhanVienDAO.suaNVtrongPB("maTruongPhong", 0, "maNV", maNV);  // xoa ma truong phong
                        nhanVienDAO.suaNVtrongPB("maTruongPhong", 0, "maTruongPhong", maNV); // xóa maTruongPhong cua cac nhan vien trong phong
                        System.out.println("Xoa truong phong khoi phong ban thanh cong.");
                    }
                }
            } while (cond);
        }
    }

    //7.(MENU DEP) chuyen pb cho nv

    //7.1. In cac phong ban

    //7.2.
    public void chuyenDoiPBchoNV(){
        boolean cond = true;
        boolean cond1 = true;
        do {
            System.out.print("Chon maPB cua phong ban muon chuyen nhan vien vao hoac nhap 'Thoat' de dung chuyen phong ban: ");
            String str = in.nextLine();
            if (str.toLowerCase().equals("thoat")){
                cond = false;
                break;
            }
            if (phongBan.setMaPB(str) == true) {
                    Integer maPBMoi = Integer.parseInt(str);
                do {
                    List<NhanVien> dsNv = nhanVienDAO.dsNhanVien();
                    List<NhanVien> dsNvkhongthuocPBNay = dsNv.stream().filter(s -> s.getMaPB() != maPBMoi && s.getMaTruongPhong() != 1).collect(Collectors.toList());
                    System.out.println("Danh sach cac nhan vien thuoc phong ban khac.");
                    dsNvkhongthuocPBNay.stream().forEach(s -> System.out.println(s + " "));  // in danh sách các nhan vien khong thuộc phòng ban này
                    System.out.print("Nhap maNV cua nhan vien ban muon chuyen sang phong ban nay hoac nhap 'Thoat' de thoat phong ban: ");
                    String str1 = in.nextLine();
                    if (str1.toLowerCase().equals("thoat")) {
                        cond1 = false;
                        break;
                    }
                    if (nhanVien.setMaNV(str1) == true) {
                        Integer maNV = Integer.parseInt(str1);
                        NhanVien nhanVien1 = nhanVienDAO.timKiemNV("maNV", maNV);

                        if (nhanVien1.getMaNV() == 0 || nhanVien1.getMaPB() == maPBMoi || nhanVien1.getMaTruongPhong() ==1) {
                            System.out.println("Khong tim thay nhan vien hoac nhan vien da o phong ban nay.");
                            System.out.println("Vui long nhap lai.");
                            continue;
                        } else {
                            nhanVienDAO.suaNVtrongPB("maPB", maPBMoi, "maNV", maNV);
                            System.out.println("Chuyen phong ban cho nhan vien thanh cong.");
                        }
                    }
                }while (cond1);
            }
        }while (cond);
    }
}
