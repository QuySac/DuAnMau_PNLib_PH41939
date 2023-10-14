package com.example.duanmau_pnlib_ph41939.model;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private int namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, int namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
