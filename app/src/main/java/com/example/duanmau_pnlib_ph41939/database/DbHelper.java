package com.example.duanmau_pnlib_ph41939.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLib";
    static final int dbVersion = 1;

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu = "CREATE TABLE THUTHU (\n" +
                "    MATT    TEXT    PRIMARY KEY,\n" +
                "    HOTEN   TEXT    NOT NULL,\n" +
                "    MATKHAU TEXT    NOT NULL,\n" +
                "    VAITRO  INTEGER NOT NULL\n" +
                ");\n";

        String createTableThanhVien = "CREATE TABLE THANHVIEN (\n" +
                "    MATV    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    HOTENTV TEXT    NOT NULL,\n" +
                "    NAMSINH TEXT    NOT NULL\n" +
                ");\n";

        String createTableLoaiSach = "CREATE TABLE LOAISACH (\n" +
                "    MALOAI  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    TENLOAI TEXT    UNIQUE\n" +
                "                    NOT NULL\n" +
                ");\n";

        String createTableSach = "CREATE TABLE SACH (\n" +
                "    MASACH  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    TENSACH TEXT    NOT NULL\n" +
                "                    UNIQUE,\n" +
                "    GIATHUE INTEGER NOT NULL,\n" +
                "    MALOAI  INTEGER REFERENCES LOAISACH (MALOAI) \n" +
                ");\n";

        String createTablePhieuMuon = "CREATE TABLE PHIEUMUON (\n" +
                "    MAPHIEU  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    MATT     TEXT    REFERENCES THUTHU (MATT),\n" +
                "    MATV     INTEGER REFERENCES THANHVIEN (MATV),\n" +
                "    MASACH   INTEGER REFERENCES SACH (MASACH),\n" +
                "    NGAYMUON DATE    NOT NULL,\n" +
                "    TRASACH  INTEGER NOT NULL,\n" +
                "    TIENTHUE INTEGER NOT NULL\n" +
                ");\n";

        db.execSQL(createTableThuThu);
        db.execSQL(createTableThanhVien);
        db.execSQL(createTableLoaiSach);
        db.execSQL(createTableSach);
        db.execSQL(createTablePhieuMuon);
        db.execSQL("INSERT INTO THUTHU VALUES ('admin','Nguyễn Sỹ Quý','111111', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }
    }
}
