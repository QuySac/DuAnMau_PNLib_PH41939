package com.example.duanmau_pnlib_ph41939.model;

public class ThongKeTop {
    private String tenSach;
    private int soLuong;

    public ThongKeTop() {}

    public ThongKeTop(String tenSach, int soLuong) {
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
