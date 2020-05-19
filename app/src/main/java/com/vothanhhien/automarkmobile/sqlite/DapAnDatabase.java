package com.vothanhhien.automarkmobile.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vothanhhien.automarkmobile.activities.DapAn.DapAn;
import com.vothanhhien.automarkmobile.models.BaiThi;

import java.util.ArrayList;

public class DapAnDatabase {
    private SQLiteDatabase database;
    private DapAnHelper dapAnHelper;

    // Phương thức kiểm tra và tạo bảng, đồng thời cũng là phương thức khởi tạo
    public DapAnDatabase(Context myContext)
    {
        this.dapAnHelper = new DapAnHelper(myContext);
        database = this.dapAnHelper.getWritableDatabase();
    }

    // Phương thức để thêm mới Đáp án vào cơ sở dữ liệu
    public void ThemDapAn(DapAn dapAn)
    {
        ThemDapAn( Integer.parseInt(dapAn.getMaBaiThi()), dapAn.getMaDe(), dapAn.getDapAn());
    }
    public void ThemDapAn(BaiThi baiThi, String made, String dapan)
    {
        ThemDapAn(Integer.parseInt(baiThi.getId()), made, dapan);
    }
    public int ThemDapAn(int id_baithi, String made, String dapan)
    {
        int lastID;
        ContentValues values = new ContentValues();
        values.put(DapAnHelper.ID_BaiThi, id_baithi);
        values.put(DapAnHelper.MADE, made);
        values.put(DapAnHelper.DAPAN, dapan);
        this.database = this.dapAnHelper.getWritableDatabase();
        lastID = (int) this.database.insert(DapAnHelper.Table_DapAn, null, values);
        return lastID;
    }
    // Phương thức để sửa đáp án của một đề thi trong csdl
    public void SuaDapAn(DapAn dapAn)
    {
        SuaDapAn(Integer.parseInt(dapAn.getID()), Integer.parseInt(dapAn.getMaBaiThi()), dapAn.getMaDe(), dapAn.getDapAn());
    }
    public void SuaDapAn(int id, BaiThi baiThi, String made, String dapan)
    {
        SuaDapAn(id, Integer.parseInt(baiThi.getId()), made, dapan);
    }
    public void SuaDapAn(int id, int id_baithi, String made, String dapan)
    {
        ContentValues values = new ContentValues();
        values.put(DapAnHelper.ID, id);
        values.put(DapAnHelper.ID_BaiThi, id_baithi);
        values.put(DapAnHelper.MADE, made);
        values.put(DapAnHelper.DAPAN, dapan);
        this.database = this.dapAnHelper.getWritableDatabase();
        this.database.replace(DapAnHelper.Table_DapAn, null, values);
    }
    // Lấy ID mã đề (Cũng là phương thức kiểm tra xem mã đề có tồn tại hay không)
    public int LayIdMaDe(int made, int mabaithi)
    {
        this.database = this.dapAnHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + DapAnHelper.Table_DapAn +
                " WHERE " + DapAnHelper.ID_BaiThi + " = " + mabaithi, null);
        if (mCursor.moveToFirst()) {
            do {
                if (Integer.parseInt(mCursor.getString(2))==made)
                    return Integer.parseInt(mCursor.getString(0));
            }
            while (mCursor.moveToNext());
        }
        return -1;
    }
    // Lấy tất cả các đáp án
    public ArrayList<DapAn> TatCaDapAn(BaiThi baiThi)
    {
        return TatCaDapAn(Integer.parseInt(baiThi.getId()));
    }
    public ArrayList<DapAn> TatCaDapAn(int idbaithi)
    {
        ArrayList<DapAn> dsDapAn = new ArrayList<>();
        this.database = this.dapAnHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + DapAnHelper.Table_DapAn +
                " WHERE " + DapAnHelper.ID_BaiThi + " = " + idbaithi, null);
        if (mCursor.moveToFirst()) {
            do {
                DapAn dapAn = new DapAn(mCursor.getString(0),
                        mCursor.getString(1),
                        mCursor.getString(2),
                        mCursor.getString(3));
                dsDapAn.add(dapAn);
            }
            while (mCursor.moveToNext());
        }
        return dsDapAn;
    }
    // Đếm số đáp án của bài thi
    public int Count(BaiThi baiThi)
    {
        return Count(Integer.parseInt(baiThi.getId()));
    }
    public int Count(int idbaithi)
    {
        int count = 0;
        this.database = this.dapAnHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + DapAnHelper.Table_DapAn +
                " WHERE " + DapAnHelper.ID_BaiThi + " = " + idbaithi, null);
        if (mCursor.moveToFirst()) {
            do {
                count++;
           }
            while (mCursor.moveToNext());
        }
        return count;
    }
    // Lấy đáp án theo ID trong csdl của nó
    public DapAn layDapAn(int id)
    {
        this.database = this.dapAnHelper.getReadableDatabase();
        // set distinct để loại bỏ các bản sao trùng lặp
        Cursor myCursor = this.database.query(true,
                DapAnHelper.Table_DapAn, new String[]{
                    DapAnHelper.ID,
                    DapAnHelper.ID_BaiThi,
                    DapAnHelper.MADE,
                    DapAnHelper.DAPAN
                },
                DapAnHelper.ID + "=?",
                new String[]{Integer.toString(id)}, null,
                null, null, null, null);
        if (myCursor != null)
            myCursor.moveToFirst();
        return new DapAn(myCursor.getString(0),
                myCursor.getString(1),
                myCursor.getString(2),
                myCursor.getString(3));
    }
    // Xóa đáp án
    public long delete(int key)
    {
        this.database = this.dapAnHelper.getReadableDatabase();
        Cursor mCursor = this.database.query(true, DapAnHelper.Table_DapAn,
                new String[]{},
                DapAnHelper.ID + "=?",
                new String[]{Integer.toString(key)},
                null, null, null, null, null);
        this.database = this.dapAnHelper.getWritableDatabase();
        if (mCursor.getCount() == 0)
            return -1;
        return (long) this.database.delete(DapAnHelper.Table_DapAn,
                DapAnHelper.ID+"=?",
                new String[]{Integer.toString(key)});
    }
    // Xóa tất cả đáp án trong cơ sở dữ liệu
    public void deleteAll()
    {
        this.database = this.dapAnHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT  * FROM "+
                DapAnHelper.Table_DapAn, null);
        if (mCursor.moveToFirst()) {
            do {
                delete(Integer.parseInt(mCursor.getString(0)));
            } while (mCursor.moveToNext());
        }
    }
    // Xóa tất cả đáp án trong 1 bài thi
    public void deleteAll(int idbaithi)
    {
        this.database = this.dapAnHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + DapAnHelper.Table_DapAn +
                " WHERE " + DapAnHelper.ID_BaiThi + " = " + idbaithi, null);
        if (mCursor.moveToFirst()) {
            do {
                delete(idbaithi);
            }
            while (mCursor.moveToNext());
        }
    }
    public void deleteAll(BaiThi baiThi)
    {
        deleteAll(Integer.parseInt(baiThi.getId()));
    }
    // Lấy mã số lớn nhất của đáp án trong csdl
    public int getMaxID()
    {
        this.database = this.dapAnHelper.getReadableDatabase();
        return (int) this.database.compileStatement("SELECT MAX("+
                DapAnHelper.ID+") FROM " + DapAnHelper.Table_DapAn)
                .simpleQueryForLong();
    }
}
