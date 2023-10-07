package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDao {
    DbHelper dbHelper;

    public PhieuMuonDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuMuon> layDanhSach() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pm.MAPM, tv.HOTENTV, sc.TENSACH, pm.TIENTHUE, pm.TRASACH, pm.NGAYMUON  From PHIEUMUON pm, THANHVIEN tv, SACH sc, THUTHU tt where pm.MATV = tv.MATV and pm.MASACH = sc.MASACH and pm.MATT = tt.MATT ORDER BY pm.MAPM DESC",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maPhieu = cursor.getInt(0);
                String hoTenTV = cursor.getString(1);
                String tenSach = cursor.getString(2);
                int tienThue = cursor.getInt(3);
                int trangThai = cursor.getInt(4);
                String ngayThue = cursor.getString(5);
                list.add(new PhieuMuon(maPhieu, tienThue, trangThai, ngayThue, hoTenTV, tenSach));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean capNhatPhieuMuon (PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MATT",pm.getMaTT());
        values.put("MATV", pm.getMaTV());
        values.put("MASACH",pm.getMaSach());
        values.put("TIENTHUE",pm.getTienThue());
        values.put("TRASACH",pm.getTrangThai());
        values.put("NGAYMUON",pm.getNgayThue());

        long check = db.update("PHIEUMUON",values,"MAPM = ?",new String[]{String.valueOf(pm.getMaPM())});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("PHIEUMUON","MAPM = ?",new String[]{String.valueOf(id)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateTrangThai (int maPM){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TRASACH", 1);
        long check = db.update("PHIEUMUON",values,"MAPM = ?",new String[]{String.valueOf(maPM)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean themPhieu (PhieuMuon pm){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MATT",pm.getMaTT());
        values.put("MATV", pm.getMaTV());
        values.put("MASACH",pm.getMaSach());
        values.put("TIENTHUE",pm.getTienThue());
        values.put("TRASACH",pm.getTrangThai());
        values.put("NGAYMUON",pm.getNgayThue());
        long check = db.insert("PHIEUMUON",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
}
