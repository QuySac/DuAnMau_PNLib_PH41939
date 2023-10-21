package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao {
    private SQLiteDatabase db;

    public PhieuMuonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATT",obj.getMaTT());
        contentValues.put("MATV",obj.getMaTV());
        contentValues.put("MASACH",obj.getMaSach());
        contentValues.put("NGAYMUON",obj.getNgayThue());
        contentValues.put("TIENTHUE",obj.getTienThue());
        contentValues.put("TRASACH",obj.getTrangThai());

        return db.insert("PhieuMuon",null,contentValues);
    }

    public long update(PhieuMuon obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATT",obj.getMaTT());
        contentValues.put("MATV",obj.getMaTV());
        contentValues.put("MASACH",obj.getMaSach());
        contentValues.put("NGAYMUON",obj.getNgayThue());
        contentValues.put("TIENTHUE",obj.getTienThue());
        contentValues.put("TRASACH",obj.getTrangThai());

        return db.update("PHIEUMUON",contentValues,"MAPM = ?",new String[]{String.valueOf(obj.getMaPM())});
    }

    public int delete(String id) {
        return db.delete("PHIEUMUON","MAPM = ?",new String[]{String.valueOf(id)});
    }

    private List<PhieuMuon> getData(String sql, String ... selectionArgs) {
        List<PhieuMuon> lstPM = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstPM.add(new PhieuMuon(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)),
                    cursor.getString(4)
            ));
        }
        return lstPM;
    }

    public PhieuMuon getID (String id) {
        String sql = "SELECT * FROM PHIEUMUON WHERE MAPM = ?";
        List<PhieuMuon> lstTV = getData(sql,id);
        return lstTV.get(0);
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PHIEUMUON";
        return getData(sql);
    }
    public boolean checkID(String id,String value) {
        String Query = "SELECT * FROM PHIEUMUON WHERE " + id +  " = " + value;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
