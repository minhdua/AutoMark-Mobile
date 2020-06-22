package com.vothanhhien.automarkmobile.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vothanhhien.automarkmobile.models.KhungTraLoi;
import com.vothanhhien.automarkmobile.models.MauTraLoi;

import java.util.ArrayList;
import java.util.List;


public class KhungTraLoiDatabase {
    private SQLiteDatabase database;
    private KhungTraLoiHelper dbHelper;
    private Context myContext;

    public KhungTraLoiDatabase(Context myConText) {
        this.myContext = myConText;
        this.dbHelper = new KhungTraLoiHelper(myContext);
        this.database = this.dbHelper.getWritableDatabase();
        this.database.execSQL(KhungTraLoiHelper.CREATE_TABLE);
    }

    public long ThemKhungTL(KhungTraLoi khungTraLoi) {
//        this.database = this.dbHelper.getWritableDatabase();
//        this.database.execSQL("DROP TABLE IF EXISTS " + this.dbHelper.TABLE_NAME);
//        return -1;
        ContentValues dl = new ContentValues();
        dl.put(dbHelper.ID, khungTraLoi.getId());
        dl.put(dbHelper.ToaDoX, khungTraLoi.getX());
        dl.put(dbHelper.ToaDoY, khungTraLoi.getY());
        dl.put(dbHelper.ChieuRong, khungTraLoi.getWidth());
        dl.put(dbHelper.ChieuDai, khungTraLoi.getHeight());
        dl.put(dbHelper.SoDong, khungTraLoi.getRows());
        dl.put(dbHelper.SoCot, khungTraLoi.getCols());
        dl.put(dbHelper.MauTraiLoi, khungTraLoi.getMau());
        this.database = this.dbHelper.getWritableDatabase();
        return this.database.insert(dbHelper.TABLE_NAME, null, dl);
    }

    public void ThayThe(KhungTraLoi khungTraLoi) {
        ContentValues dl = new ContentValues();
        dl.put(dbHelper.ID, khungTraLoi.getId());
        dl.put(dbHelper.ToaDoX, khungTraLoi.getX());
        dl.put(dbHelper.ToaDoY, khungTraLoi.getY());
        dl.put(dbHelper.ChieuRong, khungTraLoi.getWidth());
        dl.put(dbHelper.ChieuDai, khungTraLoi.getHeight());
        dl.put(dbHelper.SoDong, khungTraLoi.getRows());
        dl.put(dbHelper.SoCot, khungTraLoi.getCols());
        dl.put(dbHelper.MauTraiLoi, khungTraLoi.getMau());
        this.database = this.dbHelper.getWritableDatabase();
        this.database.replace(dbHelper.TABLE_NAME, null, dl);
    }

    public void CapNhat(KhungTraLoi khungTraLoi) {
        ContentValues dl = new ContentValues();
        long id =  khungTraLoi.getId();
        dl.put(dbHelper.ToaDoX, khungTraLoi.getX());
        dl.put(dbHelper.ToaDoY, khungTraLoi.getY());
        dl.put(dbHelper.ChieuRong, khungTraLoi.getWidth());
        dl.put(dbHelper.ChieuDai, khungTraLoi.getHeight());
        dl.put(dbHelper.SoDong, khungTraLoi.getRows());
        dl.put(dbHelper.SoCot, khungTraLoi.getCols());
        dl.put(dbHelper.MauTraiLoi, khungTraLoi.getMau());
        this.database = this.dbHelper.getWritableDatabase();
        this.database.update(dbHelper.TABLE_NAME, dl, dbHelper.ID + " = '"+id+"'",null);
    }
    public ArrayList<KhungTraLoi> TatCaKhungTL(MauTraLoi mauTraLoi) {
        return (ArrayList<KhungTraLoi>) TatCaKhungTL(mauTraLoi.getId());
    }

    public List<KhungTraLoi> TatCaKhungTL(long mauID) {
        ArrayList<KhungTraLoi> ds = new ArrayList();
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                KhungTraLoi ptl = new KhungTraLoi(mCursor.getLong(0),mCursor.getLong(7), mCursor.getInt(1),
                        mCursor.getInt(2), mCursor.getInt(3),
                        mCursor.getInt(4),
                        mCursor.getInt(6), mCursor.getInt(5));
                if (ptl.getMau() == mauID)
                    ds.add(ptl);
            }
            while (mCursor.moveToNext());
        }
        return ds;
    }

    public int SoCauTraLoi(MauTraLoi mauTraLoi) {
        return SoCauTraLoi(mauTraLoi.getId());
    }

    public int SoCauTraLoi(long id) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        int Count = 0;
        ArrayList<KhungTraLoi> khungTraLois = (ArrayList<KhungTraLoi>) TatCaKhungTL(id);
        for(int i = 2;i<khungTraLois.size();i++){
            Count += khungTraLois.get(i).getRows();
        }
        return Count;
    }

    public KhungTraLoi LayKhungTraLoi(int id) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor myCoursor = this.database.query(true, this.dbHelper.TABLE_NAME, new String[]
                        {this.dbHelper.ID, this.dbHelper.ToaDoX, this.dbHelper.ToaDoY,
                                this.dbHelper.ChieuRong, this.dbHelper.ChieuDai, this.dbHelper.SoDong, this.dbHelper.SoCot,
                                this.dbHelper.MauTraiLoi},
                this.dbHelper.ID + "=?",
                new String[]{Integer.toString(id)}, null, null, null, null, null);
        if (myCoursor != null) {
            myCoursor.moveToFirst();
            return new KhungTraLoi(myCoursor.getLong(0), myCoursor.getLong(8), myCoursor.getInt(2),
                    myCoursor.getInt(3), myCoursor.getInt(4), myCoursor.getInt(5),
                    myCoursor.getInt(6), myCoursor.getInt(7));
        } else
            return null;
    }

    public long XoaKhungTraLoi(int id) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.query(true, this.dbHelper.TABLE_NAME,
                new String[]{this.dbHelper.ID}, this.dbHelper.ID + "=?",
                new String[]{Integer.toString(id)}, null, null, null, null, null);
        if (mCursor.getCount() == 0)
            return -1;
        return (long) this.database.delete(this.dbHelper.TABLE_NAME,
                this.dbHelper.ID + "=?", new String[]{Integer.toString(id)});
    }

    public void XoaTatCaKhungTraLoi(long mau) {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                int idKhung = Integer.parseInt(mCursor.getString(0));
                int idMau = Integer.parseInt(mCursor.getString(1));
                if (idMau == mau)
                    XoaKhungTraLoi(idKhung);
            }
            while (mCursor.moveToNext());
        }
    }
    public void XoaTatCaKhungTraLoi() {
        this.database = this.dbHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + this.dbHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                int idKhung = Integer.parseInt(mCursor.getString(0));
                this.database.delete(this.dbHelper.TABLE_NAME,
                        this.dbHelper.ID + "=?", new String[]{Integer.toString(idKhung)});
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
