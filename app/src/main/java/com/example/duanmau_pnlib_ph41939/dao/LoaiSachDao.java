package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDao {
    private SQLiteDatabase db;

    public LoaiSachDao (Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENLOAI",obj.getTenLoai());

        return db.insert("LOAISACH",null,contentValues);
    }

    public long update(LoaiSach obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENLOAI",obj.getTenLoai());

        return db.update("LOAISACH",contentValues,"MALOAI = ?",new String[]{String.valueOf(obj.getMaLoai())});
    }

    public int delete(String id) {
        return db.delete("LOAISACH","MALOAI = ?",new String[]{String.valueOf(id)});
    }

    private List<LoaiSach> getData(String sql, String ... selectionArgs) {
        List<LoaiSach> lstLoai = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstLoai.add(new LoaiSach(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1)
            ));
        }
        return lstLoai;
    }

    public LoaiSach getID (String id) {
        String sql = "SELECT * FROM LOAISACH WHERE MALOAI = ?";
        List<LoaiSach> lstLS = getData(sql,id);
        return lstLS.get(0);
    }

    public List<LoaiSach> getAll() {
        String sql = "SELECT * FROM LOAISACH";
        return getData(sql);
    }

    public boolean checkID(String fieldValue) {
        String Query = "SELECT * FROM LOAISACH WHERE MALOAI = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
