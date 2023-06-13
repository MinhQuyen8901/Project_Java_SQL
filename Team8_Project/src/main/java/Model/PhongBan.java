package Model;

public class PhongBan {
    private int maPB;
    private String tenPB;
    private String moTa;
    private int maTrPhong;
    private boolean hoatDongPB;

    public PhongBan(int maPB, String tenPB, String moTa, int maTrPhong) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.moTa = moTa;
        this.maTrPhong = maTrPhong;
    }

    public PhongBan(String tenPB, String moTa) {
        this.tenPB = tenPB;
        this.moTa = moTa;
    }

    public PhongBan() {
    }

    public int getMaTrPhong() {
        return maTrPhong;
    }

    public void setMaTrPhong(int maTrPhong) {
        this.maTrPhong = maTrPhong;
    }

    public int getMaPB() {
        return maPB;
    }

    public boolean setMaPB(String str) {
        if (str.matches("[0-9]+")) {  // Chi nhan cac so nguyen va so 0 de Dung cac hoat dong
            return true;
        } else {
            System.out.println("Ma phong ban khong hop le, vui long nhap lai.");
            return false;
        }
    }

    public String getTenPB() {
        return tenPB;
    }

    public boolean setTenPB(String tenPB) {
        if (tenPB.equals("")){
            return false;
        }
        return true;
    }

    public String getMoTa() {
        return moTa;
    }

    public boolean setMoTa(String moTa) {
        if (moTa.equals("")){
            return false;
        }
        return true;
    }

    public boolean isHoatDongPB() {
        return hoatDongPB;
    }

    public void setHoatDongPB(boolean hoatDongPB) {
        this.hoatDongPB = hoatDongPB;
    }


    @Override
    public String toString() {
        return "PhongBan{" +
                "maPB=" + maPB +
                ", tenPB='" + tenPB + '\'' +
                ", moTa='" + moTa + '\'' +
                ", maTrPhong=" + maTrPhong +
                '}';
    }
}
