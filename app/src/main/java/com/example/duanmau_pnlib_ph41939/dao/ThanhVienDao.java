package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    DbHelper dbHelper;

    public ThanhVienDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ThanhVien> layDanhSach() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM THANHVIEN", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themTV(String hoTen, String namSinh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTENTV", hoTen);
        values.put("NAMSINH", namSinh);
        long check = db.insert("THANHVIEN", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean capNhatTV(int maTV, String hoTen, String namSinh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTENTV", hoTen);
        values.put("NAMSINH", namSinh);
        long check = db.update("THANHVIEN", values, "MATV = ?", new String[]{String.valueOf(maTV)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int xoaTV(int maTV) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHIEUMUON WHERE MATV = ?", new String[]{String.valueOf(maTV)});
        if (cursor.getCount() != 0) {
            return -1;
        }

        long check = db.delete("THANHVIEN", "MATV = ?", new String[]{String.valueOf(maTV)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }
}
