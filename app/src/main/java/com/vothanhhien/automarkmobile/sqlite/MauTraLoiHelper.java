package com.vothanhhien.automarkmobile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vothanhhien.automarkmobile.constants.BuildConfig;

public class MauTraLoiHelper extends SQLiteOpenHelper {
    // Câc chuỗi hằng cần thiết trong bảng
    public static String TABLE_NAME = "MauTraLoi";
    public static String ID = "id";
    public static String Ten_Mau = "ten";
    public static String So_Cau = "so_cau";
    public static String So_KhungTraLoi = "so_khungTL";
    // Các truy vấn thiết yếu
    public static final String INSERT_50 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+Ten_Mau+" , "+So_Cau+", "+So_KhungTraLoi+") VALUES( 1, BW50 , 50 , 3);";
    public static final String INSERT_60 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+Ten_Mau+" , "+So_Cau+", "+So_KhungTraLoi+") VALUES( 2, BW60 , 60 , 3);";
    public static final String INSERT_80 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+Ten_Mau+" , "+So_Cau+", "+So_KhungTraLoi+") VALUES( 3, BW80 , 80 , 4);";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            Ten_Mau + " TEXT, " +
            So_Cau + " INTEGER, " +
            So_KhungTraLoi + " INTEGER )";
    private static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;

    public MauTraLoiHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
//        sqLiteDatabase.execSQL(INSERT_50);
//        sqLiteDatabase.execSQL(INSERT_60);
//        sqLiteDatabase.execSQL(INSERT_80);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
