package com.vothanhhien.automarkmobile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vothanhhien.automarkmobile.constants.BuildConfig;
import com.vothanhhien.automarkmobile.models.MauTraLoi;

public class KhungTraLoiHelper extends SQLiteOpenHelper {

    // Câc chuỗi hằng cần thiết trong bảng
    public static String TABLE_NAME = "KhungTraLoi";
    public static String ID = "id";
    public static String ToaDoX = "x";
    public static String ToaDoY = "y";
    public static String ChieuRong = "rong";
    public static String ChieuDai = "dai";
    public static String SoDong = "rows";
    public static String SoCot = "cols";
    public static String MauTraiLoi = "mau";

    // Các truy vấn thiết yếu
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            ToaDoX + " TEXT, " +
            ToaDoY + " INTEGER, " +
            ChieuRong + " INTEGER,"+
            ChieuDai + " INTEGER,"+
            SoDong + " INTEGER,"+
            SoCot + " INTEGER,"+
            MauTraiLoi + " INTEGER)";
    private static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;

    public KhungTraLoiHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(INSERT_KHUNG1);
        sqLiteDatabase.execSQL(INSERT_KHUNG2);
        sqLiteDatabase.execSQL(INSERT_KHUNG3);
        sqLiteDatabase.execSQL(INSERT_KHUNG4);
        sqLiteDatabase.execSQL(INSERT_KHUNG5);

        sqLiteDatabase.execSQL(INSERT_KHUNG6);
        sqLiteDatabase.execSQL(INSERT_KHUNG7);
        sqLiteDatabase.execSQL(INSERT_KHUNG8);
        sqLiteDatabase.execSQL(INSERT_KHUNG9);
        sqLiteDatabase.execSQL(INSERT_KHUNG10);

        sqLiteDatabase.execSQL(INSERT_KHUNG11);
        sqLiteDatabase.execSQL(INSERT_KHUNG12);
        sqLiteDatabase.execSQL(INSERT_KHUNG13);
        sqLiteDatabase.execSQL(INSERT_KHUNG14);
        sqLiteDatabase.execSQL(INSERT_KHUNG15);
        sqLiteDatabase.execSQL(INSERT_KHUNG16);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private static final String INSERT_KHUNG1 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 1,240,40,288,80,10,3,1);";
    private static final String INSERT_KHUNG2 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 2,240,148,288,160,10,6,1);";
    private static final String INSERT_KHUNG3 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 3,664,584,428,156,17,4,1);";
    private static final String INSERT_KHUNG4 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 4,664,312,428,156,17,4,1);";
    private static final String INSERT_KHUNG5 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 5,664,44,428,156,17,4,1);";

    private static final String INSERT_KHUNG6 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 6,172,56,252,80,10,3,2);";
    private static final String INSERT_KHUNG7 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 7,172,164,252,160,10,6,2);";
    private static final String INSERT_KHUNG8 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 8,560,568,508,148,20,4,2);";
    private static final String INSERT_KHUNG9= "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES(  9,560,320,508,148,20,4,2);";
    private static final String INSERT_KHUNG10 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 10,560,68,504,148,20,4,2);";

    private static final String INSERT_KHUNG11 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 11,260,60,272,80,10,3,3);";
    private static final String INSERT_KHUNG12 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 12,260,168,272,148,10,6,3);";
    private static final String INSERT_KHUNG13 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES(  13,592,612,460,124,20,4,3);";
    private static final String INSERT_KHUNG14= "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES(  14,592,428,460,124,20,4,3);";
    private static final String INSERT_KHUNG15 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES( 15,592,244,460,124,20,4,3);";
    private static final String INSERT_KHUNG16 = "INSERT INTO "+TABLE_NAME+" ( "+ID+" , "+ToaDoX+" , "+ToaDoY+", "+ChieuRong+", "+ChieuDai+", "+SoDong+", "+SoCot+", "+ MauTraiLoi +
            ") VALUES(16,592,60,460,124,20,4,3);";

}
