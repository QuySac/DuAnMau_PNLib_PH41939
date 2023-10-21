package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    private SQLiteDatabase db;

    public SachDao (Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENSACH",obj.getTenSach());
        contentValues.put("GIATHUE",obj.getGiaThue());
        contentValues.put("MALOAI",obj.getMaLoai());

        return db.insert("SACH",null,contentValues);
    }

    public long update(Sach obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENSACH",obj.getTenSach());
        contentValues.put("GIATHUE",obj.getGiaThue());
        contentValues.put("MALOAI",obj.getMaLoai());

        return db.update("SACH",contentValues,"MASACH = ?",new String[]{String.valueOf(obj.getMaSach())});
    }

    public int delete(String id) {
        return db.delete("SACH","MASACH = ?",new String[]{String.valueOf(id)});
    }

    private List<Sach> getData(String sql, String ... selectionArgs) {
        List<Sach> lstSach = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstSach.add(new Sach(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3))
            ));
        }
        return lstSach;
    }
    public Sach getID (String id) {
        String sql = "SELECT * FROM Sach WHERE MASACH = ?";
        List<Sach> lstTT = getData(sql,id);
        return lstTT.get(0);
    }

    public List<Sach> getAll() {
        String sql = "SELECT * FROM SACH";
        return getData(sql);
    }

    public boolean checkID(String fieldValue) {
        String Query = "SELECT * FROM SACH WHERE MALOAI = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
