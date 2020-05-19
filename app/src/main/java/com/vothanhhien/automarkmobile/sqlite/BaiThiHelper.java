package com.vothanhhien.automarkmobile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vothanhhien.automarkmobile.constants.BuildConfig;


public class BaiThiHelper extends SQLiteOpenHelper {
    // Tên bảng và tên các trường trong bảng
    public static final String TABLE_BaiThi = "BaiThi";
    public static final String ID = "id";
    public static final String TEN = "ten";
    public static final String NGAY_TAO = "ngay_tao";
    public static final String LOAI_GIAY = "loai_giay";
    public static final String SO_CAU = "so_cau";
    public static final String HE_DIEM = "he_diem";
    public static final String LOAI_DE = "loai_de";
    // Các truy vấn thiết yếu
    private static final String TAO_BANG = "CREATE TABLE IF NOT EXISTS " + TABLE_BaiThi + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            TEN + " TEXT, " +
            NGAY_TAO + " TEXT, " +
            LOAI_DE + " INTEGER, " +
            SO_CAU + " INTEGER, " +
            HE_DIEM + " INTEGER)";
    private static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;

    public BaiThiHelper(Context context) {
        super(context, TABLE_BaiThi, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TAO_BANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        Log.w(BaiThiHelper.class.getName(), "Cập nhật cơ sở dữ liệu từ bản " + oldVer + " sang bản " + newVer + ", và xóa tất cả dữ liệu cũ.");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BaiThi);

        onCreate(sqLiteDatabase);
    }
}
