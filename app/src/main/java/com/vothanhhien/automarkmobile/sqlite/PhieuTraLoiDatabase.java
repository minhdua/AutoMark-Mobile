package com.vothanhhien.automarkmobile.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vothanhhien.automarkmobile.activities.PhieuTraLoi.PhieuTraLoi;
import com.vothanhhien.automarkmobile.models.BaiThi;

import java.util.ArrayList;


public class PhieuTraLoiDatabase {
    private SQLiteDatabase database;
    private PhieuTraLoiHelper dbHelper;
    private Context myContext;

    public PhieuTraLoiDatabase(Context myConText) {
        this.myContext = myConText;
        this.dbHelper = new PhieuTraLoiHelper(myContext);
        this.database = this.dbHelper.getWritableDatabase();
        this.database.execSQL(PhieuTraLoiHelper.CREATE_TABLE);
    }

    public long ThemPhieuTL(PhieuTraLoi phieuTraLoi) {
//        this.database = this.dbHelper.getWritableDatabase();
//        this.database.execSQL("DROP TABLE IF EXISTS " + this.dbHelper.TABLE_NAME);
//        return -1;
        ContentValues dl = new ContentValues();
        dl.put(dbHelper.ID, phieuTraLoi.getID());
        dl.put(dbHelper.MaBaiThi, phieuTraLoi.getMaBaiThi());
        dl.put(dbHelper.SBD, phieuTraLoi.getSBD());
        dl.put(dbHelper.Name, phieuTraLoi.getName_image());
        dl.put(dbHelper.Diem, phieuTraLoi.getDiem());
        dl.put(dbHelper.MaDe, phieuTraLoi.getMaDe());
        dl.put(dbHelper.DSanswer, phieuTraLoi.getDS_CauTraLoi());
        dl.put(dbHelper.SoCauDung, phieuTraLoi.getSoCauDung());
        dl.put(dbHelper.TongCau, phieuTraLoi.getSoCauCuaBaiThi());
        dl.put(dbHelper.PhieuTL, phieuTraLoi.getPTL_image());
        this.database = this.dbHelper.getWritableDatabase();
        return this.database.insert(dbHelper.TABLE_NAME, null, dl);
    }

    public void ThayThe(PhieuTraLoi phieuTraLoi) {
        ContentValues dl = new ContentValues();
        dl.put(dbHelper.ID, phieuTraLoi.getID());
        dl.put(dbHelper.MaBaiThi, phieuTraLoi.getMaBaiThi());
        dl.put(dbHelper.SBD, phieuTraLoi.getSBD());
        dl.put(dbHelper.Name, phieuTraLoi.getName_image());
        dl.put(dbHelper.Diem, phieuTraLoi.getDiem());
        dl.put(dbHelper.MaDe, phieuTraLoi.getMaDe());
        dl.put(dbHelper.DSanswer, phieuTraLoi.getDS_CauTraLoi());
        dl.put(dbHelper.SoCauDung, phieuTraLoi.getSoCauDung());
        dl.put(dbHelper.TongCau, phieuTraLoi.getSoCauCuaBaiThi());
        dl.put(dbHelper.PhieuTL, phieuTraLoi.getPTL_image());
        this.database = this.dbHelper.getWritableDatabase();
        this.database.replace(dbHelper.TABLE_NAME, null, dl);
    }

    public ArrayList<PhieuTraLoi> TatCaPTL(BaiThi baiThi) {
        return TatCaPTL(Integer.parseInt(baiThi.getId()));
    }

    public ArrayList<PhieuTraLoi> TatCaPTL(int IDofBaiThi) {
        ArrayList<PhieuTraLoi> ds = new ArrayList();
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                PhieuTraLoi ptl = new PhieuTraLoi(mCursor.getString(0), mCursor.getString(1),
                        mCursor.getString(2), mCursor.getBlob(3),
                        mCursor.getString(4), mCursor.getString(5),
                        mCursor.getString(6),mCursor.getString(7),
                        mCursor.getString(8), mCursor.getBlob(9));
                if (Integer.parseInt(ptl.getMaBaiThi()) == IDofBaiThi)
                    ds.add(ptl);
            }
            while (mCursor.moveToNext());
        }
        return ds;
    }

    public int SoPhieuTraLoiTrongMotBaiThi(BaiThi baiThi) {
        return SoPhieuTraLoiTrongMotBaiThi(Integer.parseInt(baiThi.getId()));
    }

    public int SoPhieuTraLoiTrongMotBaiThi(int IDofBaiThi) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        int Count = 0;
        if (mCursor.moveToFirst()) {
            do {
                if (Integer.parseInt(mCursor.getString(1)) == IDofBaiThi)
                    Count++;
            }
            while (mCursor.moveToNext());
        }
        return Count;
    }

    public PhieuTraLoi LayPhieuTraLoi(int id) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor myCoursor = this.database.query(true, this.dbHelper.TABLE_NAME, new String[]
                        {this.dbHelper.ID, this.dbHelper.MaBaiThi, this.dbHelper.SBD,
                                this.dbHelper.Name, this.dbHelper.Diem, this.dbHelper.MaDe, this.dbHelper.DSanswer,
                                this.dbHelper.SoCauDung, this.dbHelper.TongCau, this.dbHelper.PhieuTL},
                this.dbHelper.ID + "=?",
                new String[]{Integer.toString(id)}, null, null, null, null, null);
        if (myCoursor != null) {
            myCoursor.moveToFirst();
            return new PhieuTraLoi(myCoursor.getString(0), myCoursor.getString(1), myCoursor.getString(2),
                    myCoursor.getBlob(3), myCoursor.getString(4), myCoursor.getString(5),
                    myCoursor.getString(6), myCoursor.getString(7), myCoursor.getString(8), myCoursor.getBlob(9));
        } else
            return null;
    }

    public long XoaPhieuTraLoi(int id) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.query(true, this.dbHelper.TABLE_NAME,
                new String[]{this.dbHelper.ID}, this.dbHelper.ID + "=?",
                new String[]{Integer.toString(id)}, null, null, null, null, null);
        if (mCursor.getCount() == 0)
            return -1;
        return (long) this.database.delete(this.dbHelper.TABLE_NAME,
                this.dbHelper.ID + "=?", new String[]{Integer.toString(id)});
    }

    public void XoaTatCaPhieuTraLoi(int idBaiThi) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                int IDofPTL = Integer.parseInt(mCursor.getString(0));
                int IDofBaiThi = Integer.parseInt(mCursor.getString(1));
                if (IDofBaiThi == idBaiThi)
                    XoaPhieuTraLoi(IDofPTL);
            }
            while (mCursor.moveToNext());
        }
    }

    public int MaxID() {
        this.database = this.dbHelper.getReadableDatabase();
        return (int) this.database.compileStatement("SELECT MAX(" + this.dbHelper.ID + ") FROM "
                + this.dbHelper.TABLE_NAME).simpleQueryForLong();
    }


}
