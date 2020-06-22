package com.vothanhhien.automarkmobile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vothanhhien.automarkmobile.constants.BuildConfig;


public class DapAnHelper extends SQLiteOpenHelper {
    // Tên bảng và tên các trường trong bảng
    public static final String Table_DapAn = "DapAn";
    public static final String ID = "id";
    public static final String ID_BaiThi = "ID_BaiThi";
    public static final String MADE = "MaDe";
    public static final String DAPAN = "DanhSachDapAn";

    // Câu truy vấn tạo bảng
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + Table_DapAn + " (" +
            ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, " +
            ID_BaiThi + " INTEGER ," +
            MADE + " TEXT, " +
            DAPAN + " TEXT)";
//            "UNIQUE("+ID_BaiThi+","+DAPAN+"));";
    private static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;

    public DapAnHelper(Context context)
    {
        super(context,Table_DapAn, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_DapAn);
    }
}
