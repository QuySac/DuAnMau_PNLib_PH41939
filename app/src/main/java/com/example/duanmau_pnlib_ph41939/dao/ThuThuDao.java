package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDao {
    private SQLiteDatabase db;

    public ThuThuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATT",obj.getMaTT());
        contentValues.put("HOTEN", obj.getHoTen());
        contentValues.put("MATKHAU",obj.getMatKhau());

        return db.insert("THUTHU",null,contentValues);
    }

    public long update(ThuThu obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTEN", obj.getHoTen());
        contentValues.put("MATKHAU",obj.getMatKhau());

        return db.update("THUTHU",contentValues,"MATT = ?",new String[]{String.valueOf(obj.getMaTT())});
    }

    public int delete(String id) {
        return db.delete("THUTHU","MATT = ?",new String[]{String.valueOf(id)});
    }

    private List<ThuThu> getData(String sql, String ... selectionArgs) {
        List<ThuThu> lstTT = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstTT.add(new ThuThu(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }
        return lstTT;
    }

    public ThuThu getID (String id) {
        String sql = "SELECT * FROM THUTHU WHERE MATT = ?";
        List<ThuThu> lstTT = getData(sql,id);
        return lstTT.get(0);
    }

    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }

    public long checkLogin(String username,String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",new String[]{username,password});
        if (cursor.getCount() > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
