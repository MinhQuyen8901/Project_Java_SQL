package Model;

public class TaiKhoan {
    private int maTK;
    private String tenTK;
    private String maKhau;

    public TaiKhoan(int maTK, String tenTK, String maKhau) {
        this.maTK = maTK;
        this.tenTK = tenTK;
        this.maKhau = maKhau;
    }

    public TaiKhoan(String tenTK, String maKhau) {
        this.tenTK = tenTK;
        this.maKhau = maKhau;
    }

    public TaiKhoan() {
    }

    public int getMaTK() {
        return maTK;
    }

    public boolean setMaTK(String str) {
        return str.matches("[0-9]+");
    }

    public String getTenTK() {
        return tenTK;
    }

    public boolean setTenTK(String tenTK) {
        if (tenTK.equals("")){
            return false;
        }
        return true;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public boolean setMaKhau(String maKhau) {
        if (maKhau.equals("")){
            return false;
        }
        return true;
    }
}
