package com.vothanhhien.automarkmobile.sqlite.PhieuTraLoi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vothanhhien.automarkmobile.BuildConfig;


public class PhieuTraLoiHelper extends SQLiteOpenHelper {
    // Câc chuỗi hằng cần thiết trong bảng
    public static String TABLE_NAME = "PhieuTraLoi";
    public static String ID = "id";
    public static String MaBaiThi = "MBT";
    public static String SBD = "sbd";
    public static String Name = "path_name";
    public static String Diem = "diem";
    public static String MaDe = "ma_de";
    public static String DSanswer = "AnswerList";
    public static String SoCauDung = "so_cau_dung";
    public static String TongCau = "tong_cau"; // Tổng số câu hay số câu của bài thi
    public static String PhieuTL = "image_ptl";
    // Các truy vấn thiết yếu
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            MaBaiThi + " TEXT, " +
            SBD + " TEXT, " +
            Name + " BLOB, " +
            Diem + " REAL, " +
            MaDe + " TEXT, " +
            DSanswer + " TEXT, " +
            SoCauDung + " INTEGER, " +
            TongCau + " INTEGER, " +
            PhieuTL + " BLOB)";
    private static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;

    public PhieuTraLoiHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
