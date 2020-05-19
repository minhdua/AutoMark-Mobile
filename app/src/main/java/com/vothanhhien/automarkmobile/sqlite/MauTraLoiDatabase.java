package com.vothanhhien.automarkmobile.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vothanhhien.automarkmobile.activities.DapAn.DapAn;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.models.CauTraLoi;
import com.vothanhhien.automarkmobile.models.MauTraLoi;

import java.util.ArrayList;

public class MauTraLoiDatabase {
    private SQLiteDatabase database;
    private MauTraLoiHelper mauTraLoiHelper;

    // Phương thức kiểm tra và tạo bảng, đồng thời cũng là phương thức khởi tạo
    public MauTraLoiDatabase(Context myContext)
    {
        this.mauTraLoiHelper = new MauTraLoiHelper(myContext);
        database = this.mauTraLoiHelper.getWritableDatabase();
    }

    // Phương thức để thêm mới Đáp án vào cơ sở dữ liệu
    public int ThemMauTraLoi(MauTraLoi mauTraLoi)
    {
        int lastID;
        ContentValues values = new ContentValues();
        values.put(MauTraLoiHelper.ID, mauTraLoi.getId());
        values.put(MauTraLoiHelper.Ten_Mau, mauTraLoi.getTenMau());
        values.put(MauTraLoiHelper.So_Cau, mauTraLoi.getSoCauTraLoi());
        values.put(MauTraLoiHelper.So_KhungTraLoi, mauTraLoi.getSoKhungTraLoi());
        this.database = this.mauTraLoiHelper.getWritableDatabase();
        lastID = (int) this.database.insert(MauTraLoiHelper.TABLE_NAME, null, values);
        return lastID;
    }
    // Phương thức để sửa đáp án của một đề thi trong csdl
    public void SuaMauTraLoi(MauTraLoi mauTraLoi)
    {
        ContentValues values = new ContentValues();
        values.put(MauTraLoiHelper.ID, mauTraLoi.getId());
        values.put(MauTraLoiHelper.Ten_Mau, mauTraLoi.getTenMau());
        values.put(MauTraLoiHelper.So_Cau, mauTraLoi.getSoCauTraLoi());
        values.put(MauTraLoiHelper.So_KhungTraLoi, mauTraLoi.getSoKhungTraLoi());
        this.database = this.mauTraLoiHelper.getWritableDatabase();
        this.database.replace(MauTraLoiHelper.TABLE_NAME, null, values);
    }
    // Lấy ID mã đề (Cũng là phương thức kiểm tra xem mẫu có tồn tại hay không)
    public MauTraLoi LayIdMau(int mauID)
    {
        this.database = this.mauTraLoiHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + MauTraLoiHelper.TABLE_NAME +
                " WHERE " + MauTraLoiHelper.ID + " = " + mauID, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            long id = mCursor.getLong(0);
            String tenMau = mCursor.getString(1);
            int soCau = mCursor.getInt(2);
            int soKhung = mCursor.getInt(3);
            return new MauTraLoi(id, tenMau, soCau, soKhung);
        }
        return null;
    }
    // Lấy tất cả các đáp án
    public ArrayList<MauTraLoi> TatCaMauTraLoi()
    {
        ArrayList<MauTraLoi> dsMauTraLoi = new ArrayList<>();
        this.database = this.mauTraLoiHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT * FROM " + MauTraLoiHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                MauTraLoi mauTraLoi = new MauTraLoi(mCursor.getLong(0),
                        mCursor.getString(1),
                        mCursor.getInt(2),
                        mCursor.getInt(3));
                dsMauTraLoi.add(mauTraLoi);
            }
            while (mCursor.moveToNext());
        }
        return dsMauTraLoi;
    }
    // Xóa đáp án
    public long delete(int key)
    {
        this.database = this.mauTraLoiHelper.getReadableDatabase();
        Cursor mCursor = this.database.query(true, MauTraLoiHelper.TABLE_NAME,
                new String[]{},
                MauTraLoiHelper.ID + "=?",
                new String[]{Integer.toString(key)},
                null, null, null, null, null);
        this.database = this.mauTraLoiHelper.getWritableDatabase();
        if (mCursor.getCount() == 0)
            return -1;
        return (long) this.database.delete(MauTraLoiHelper.TABLE_NAME,
                MauTraLoiHelper.ID+"=?",
                new String[]{Integer.toString(key)});
    }
    // Xóa tất cả đáp án trong cơ sở dữ liệu
    public void deleteAll()
    {
        this.database = this.mauTraLoiHelper.getReadableDatabase();
        Cursor mCursor = this.database.rawQuery("SELECT  * FROM "+
                MauTraLoiHelper.TABLE_NAME, null);
        if (mCursor.moveToFirst()) {
            do {
                delete(Integer.parseInt(mCursor.getString(0)));
            } while (mCursor.moveToNext());
        }
    }
    // Xóa tất cả đáp án trong 1 bài thi
    public int getMaxID()
    {
        this.database = this.mauTraLoiHelper.getReadableDatabase();
        return (int) this.database.compileStatement("SELECT MAX("+
                MauTraLoiHelper.ID+") FROM " + MauTraLoiHelper.TABLE_NAME)
                .simpleQueryForLong();
    }
}
