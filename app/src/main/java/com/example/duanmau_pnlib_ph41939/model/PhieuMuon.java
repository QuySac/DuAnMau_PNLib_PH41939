package com.example.duanmau_pnlib_ph41939.model;

public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private int tienThue;
    private int  trangThai;
    private String ngayThue;
    private String hoTenTV;
    private String tenSach;

    public PhieuMuon() {
    }

    public PhieuMuon(String maTT, int maTV, int maSach, int tienThue, int trangThai, String ngayThue) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.trangThai = trangThai;
        this.ngayThue = ngayThue;
    }

    public PhieuMuon(int maPM, int tienThue, int trangThai, String ngayThue, String hoTenTV, String tenSach) {
        this.maPM = maPM;
        this.tienThue = tienThue;
        this.trangThai = trangThai;
        this.ngayThue = ngayThue;
        this.hoTenTV = hoTenTV;
        this.tenSach = tenSach;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getHoTenTV() {
        return hoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        this.hoTenTV = hoTenTV;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
