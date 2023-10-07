package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.Sach;

import java.util.ArrayList;

public class SachDao {
    DbHelper dbHelper;

    public SachDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> layDanhSach() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT sc.MASACH, sc.TENSACH, sc.GIATHUE, ls.MALOAI, ls.TENLOAI FROM SACH sc, LOAISACH ls WHERE sc.MALOAI = ls.MALOAI", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {

                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSach(String tenSach, int tienThue, int maLoai) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", tenSach);
        values.put("GIATHUE", tienThue);
        values.put("MALOAI", maLoai);
        long check = db.insert("SACH", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean capNhat(int maSach, String tenSach, int giaThue, int maLoai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH", tenSach);
        values.put("GIATHUE", giaThue);
        values.put("MALOAI", maLoai);
        long check = db.update("Sach", values, "MaSach = ?", new String[]{String.valueOf(maSach)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int xoaSach(int maSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHIEUMUON where MASACH = ?", new String[]{String.valueOf(maSach)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("SACH", "MASACH = ?", new String[]{String.valueOf(maSach)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }
}
