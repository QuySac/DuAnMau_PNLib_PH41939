package com.example.duanmau_pnlib_ph41939.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDao {
    DbHelper dbHelper;

    public LoaiSachDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiSach> layDanhSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LoaiSach",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int loaiSach = cursor.getInt(0);
                String tenLoai = cursor.getString(1);
                list.add(new LoaiSach(loaiSach, tenLoai));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoai (String tenLoai){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI", tenLoai);
        long check = db.insert("LOAISACH",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public int deleteLS(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SACH WHERE MALOAI = ?", new String[]{String.valueOf(id)});
        if(cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("LOAISACH","MALOAI = ?", new String[]{String.valueOf(id)});
        if(check == -1){
            return 0;
        }else{
            return 1;
        }
    }

    public boolean capNhatLoai (LoaiSach loaiSach){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI", loaiSach.getTenLoai());
        long check = db.update("LOAISACH",values,"MALOAI = ?", new String[]{String.valueOf(loaiSach.getMaLoai())});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
}
