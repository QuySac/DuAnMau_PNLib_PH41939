package com.example.duanmau_pnlib_ph41939.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.Sach;

import java.util.ArrayList;

public class ThongKeDao {
    DbHelper dbHelper;

    public ThongKeDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> layDSTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT pm.MASACH, sc.TENSACH, COUNT(pm.MASACH) AS SOLANMUON " +
                "FROM PHIEUMUON pm, SACH sc WHERE pm.MASACH = sc.MASACH GROUP BY pm.MASACH, sc.TENSACH " +
                "ORDER BY COUNT(pm.MASACH) DESC LIMIT 10",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int layTopDoanhThu (String start, String end){
        start = start.replace("/","");
        end = end.replace("/","");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(TienThue) FROM PhieuMuon WHERE substr(Ngay,7) || substr(Ngay,4,2) || substr(Ngay,1,2) BETWEEN ? AND ?",new String[]{start,end});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
