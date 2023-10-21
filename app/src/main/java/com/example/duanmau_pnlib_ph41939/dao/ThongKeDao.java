package com.example.duanmau_pnlib_ph41939.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_pnlib_ph41939.database.DbHelper;
import com.example.duanmau_pnlib_ph41939.model.Sach;
import com.example.duanmau_pnlib_ph41939.model.ThongKeTop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDao (Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<ThongKeTop> getTop () {
        String sqlTop = "SELECT MASACH, COUNT(MASACH) AS SOLUONG FROM PHIEUMUON GROUP BY MASACH ORDER BY SOLUONG DESC LIMIT 10";
        List<ThongKeTop> lstTOP = new ArrayList<>();
        SachDao sachDAO = new SachDao(context);
        Cursor cursor = db.rawQuery(sqlTop,null);
        while ((cursor.moveToNext())){
            Sach sach = sachDAO.getID(cursor.getString(0));
            lstTOP.add(new ThongKeTop(
                    sach.getTenSach(),
                    Integer.parseInt(cursor.getString(1))
            ));
        }
        return lstTOP;
    }

    public int getDoanhThu(String tuNgay,String denNgay) {
        String sqlDoanhthu = "SELECT SUM(TIENTHUE) as DOANHTHU FROM PHIEUMUON WHERE NGAY BETWEEN ? AND ?";
        List<Integer> lstDT = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlDoanhthu,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()) {
            try {
                lstDT.add(Integer.parseInt(cursor.getString(0)));
            } catch (Exception e) {
                lstDT.add(0);
            }
        }
        return lstDT.get(0);
    }
}
