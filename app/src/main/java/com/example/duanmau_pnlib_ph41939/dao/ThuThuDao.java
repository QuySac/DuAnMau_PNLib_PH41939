package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;

public class ThuThuDao {
    DbHelper dbHelper;

    public ThuThuDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean checkLogin(String maTT, String matKhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",
                new String[]{maTT, matKhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean capNhatMatKhau (String username, String oldPass, String newPass) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",
                new String[]{username, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPass);
            long check = db.update("THUTHU", values, "MATT = ?",
                    new String[]{username});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
