package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    private SQLiteDatabase db;

    public ThanhVienDao (Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTENTV", obj.getHoTen());
        contentValues.put("NAMSINH",obj.getNamSinh());

        return db.insert("THANHVIEN",null,contentValues);
    }

    public long update(ThanhVien obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTENTV", obj.getHoTen());
        contentValues.put("NAMSINH",obj.getNamSinh());

        return db.update("THANHVIEN",contentValues,"MATV = ?",new String[]{String.valueOf(obj.getMaTV())});
    }

    public int delete(String id) {
        return db.delete("THANHVIEN","MATV = ?",new String[]{String.valueOf(id)});
    }

    private List<ThanhVien> getData(String sql, String ... selectionArgs) {
        List<ThanhVien> lstTV = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstTV.add(new ThanhVien(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2))
            ));
        }
        return lstTV;
    }

    public ThanhVien getID (String id) {
        String sql = "SELECT * FROM THANHVIEN WHERE MATV = ?";
        List<ThanhVien> lstTV = getData(sql,id);
        return lstTV.get(0);
    }

    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM THANHVIEN";
        return getData(sql);
    }
}
