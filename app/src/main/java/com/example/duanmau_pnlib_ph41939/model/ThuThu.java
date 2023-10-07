package com.example.duanmau_pnlib_ph41939.model;

public class ThuThu {
    private String maTT;
    private String matKhau;
    private String hoTen;

    public ThuThu() {
    }

    public ThuThu(String maTT, String matKhau, String hoTen) {
        this.maTT = maTT;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
