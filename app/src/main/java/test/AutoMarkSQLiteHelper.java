package test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;


public class AutoMarkSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "database_automark.db";
    private static final int DB_VERSION = 10;
    private static final int TYPES_FRAME = 10;
    public static final String TAB_SHEET = "type_sheet";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "_name";
    public static final String COL_IMAGE = "_image" ;

    public static final String SHEET_CREATE = "create table "
            + TAB_SHEET + "( "
            + COL_ID + " integer primary key autoincrement, "
            + COL_NAME + " text not null unique, "
            + COL_IMAGE + " blob not null )";

    public static final String TAB_BLOCK = "block";
    public static final String COL_SHEET = "_sheet";
    public static final String BLOCK_CREATE = "create table "
            + TAB_BLOCK+ "( "
            + COL_ID + " integer primary key autoincrement, "
            + COL_NAME + " text not null,"
            + COL_SHEET + " integer not null,"
            + "foreign key(" + COL_SHEET+ ") references "+ TAB_SHEET +"(" + COL_ID + ") on delete cascade )";

    public static final String TAB_FRAME = "frame";
    public static final String COL_X = "_x";
    public static final String COL_Y = "_y";
    public static final String COL_WIDTH = "_width";
    public static final String COL_COLS = "_cols";
    public static String COL_HEIGHT = "_height";
    public static String COL_ROWS = "_rows";
    public static String COL_BLOCK = "block";

    public static final String FRAME_CREATE = "create table "
            + TAB_FRAME + "( "
            + COL_ID + " integer primary key autoincrement, "
            + COL_X + " integer not null, "
            + COL_Y + " integer not null, "
            + COL_WIDTH + " integer not null, "
            + COL_HEIGHT + " integer not null, "
            + COL_COLS + " integer not null, "
            + COL_ROWS + " integer not null, "
            + COL_BLOCK + " integer not null, "
            + "foreign key(" + COL_BLOCK+ ") references "+ TAB_BLOCK +"(" + COL_ID + ") on delete cascade )";

    public static final String TAB_CORRECT_ANSWER = "correct_answer";
    public static final String COL_CODE = "_code";
    public static final String COL_ANSWER = "_answer";
    public static final String CORRECT_ANSWER_CREATE = "create table "
            + TAB_CORRECT_ANSWER + "( "
            + COL_ID + " integer primary key autoincrement, "
            + COL_CODE + " text not null, "
            + COL_ANSWER + " text not null, "
            + COL_SHEET + " integer not null,"
            + "unique ( " + COL_SHEET + " , " + COL_CODE+ " ),"
            + "foreign key(" + COL_SHEET+ ") references "+ TAB_SHEET +"(" + COL_ID + ") on delete cascade )";

    public static final String TAB_YOUR_ANSWER = "your_answer";
    public static final String COL_SBD = "sbd";
    public static final String COL_CORRECTS = "_corrects";
    public static final String YOUR_ANSWER_CREATE = "create table "
            + TAB_YOUR_ANSWER + "( "
            + COL_ID + " integer primary key autoincrement, "
            + COL_CODE + " text not null, "
            + COL_SBD + " text not null, "
            + COL_ANSWER + " text not null, "
            + COL_SHEET + " integer not null, "
            + COL_CORRECTS + " integer not null, "
            + COL_IMAGE + " blob not null, "
            + "unique ( " + COL_SHEET + " , " + COL_CODE + " , " + COL_CODE+ " ),"
            + "foreign key(" + COL_SHEET+ ") references "+ TAB_SHEET +"(" + COL_ID + ") on delete cascade )";

    public AutoMarkSQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }
    public static final String TABLE_BaiThi = "BaiThi";
    public static final String ID = "id";
    public static final String TEN = "ten";
    public static final String NGAY_TAO = "ngay_tao";
    public static final String LOAI_GIAY = "loai_giay";
    public static final String SO_CAU = "so_cau";
    public static final String HE_DIEM = "he_diem";
    // Các truy vấn thiết yếu
    private static final String TAO_BANG = "CREATE TABLE IF NOT EXISTS " + TABLE_BaiThi + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            TEN + " TEXT, " +
            NGAY_TAO + " TEXT, " +
            LOAI_GIAY + " INTEGER, " +
            SO_CAU + " INTEGER, " +
            HE_DIEM + " INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SHEET_CREATE);
        db.execSQL(BLOCK_CREATE);
        db.execSQL(FRAME_CREATE);
        db.execSQL(CORRECT_ANSWER_CREATE);
        db.execSQL(YOUR_ANSWER_CREATE);
        db.execSQL(TAO_BANG);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TAB_SHEET);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_BLOCK);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_FRAME);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_CORRECT_ANSWER);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_YOUR_ANSWER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BaiThi);
        onCreate(db);
    }
}
