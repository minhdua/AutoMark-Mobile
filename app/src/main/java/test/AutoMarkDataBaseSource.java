//package test;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.vothanhhien.automarkmobile.models.Khoi;
//import com.vothanhhien.automarkmobile.models.DapAn;
//import com.vothanhhien.automarkmobile.models.KhungTraLoi;
//import com.vothanhhien.automarkmobile.models.TypeSheet;
//import com.vothanhhien.automarkmobile.models.DapAnThiSinh;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AutoMarkDataBaseSource {
//
//    private static final String[] COLS_CORRECT = {
//            AutoMarkSQLiteHelper.COL_CODE,
//            AutoMarkSQLiteHelper.COL_ANSWER,
//            AutoMarkSQLiteHelper.COL_SHEET
//    };
//
//
//    private SQLiteDatabase database;
//    private AutoMarkSQLiteHelper dbHelper;
//
//    public AutoMarkDataBaseSource(Context context){
//        dbHelper = new AutoMarkSQLiteHelper(context);
//    }
//    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//    public void close(){
//        dbHelper.close();
//    }
//
////////////////////////////////////////////////     TYPE SHEET /////////////////////////////
//    private static final String[] COLS_SHEET = {
//            AutoMarkSQLiteHelper.COL_NAME,
//            AutoMarkSQLiteHelper.COL_IMAGE
//    };
//    public long addSheet(TypeSheet sheet) {
//        open();
//        long sheetID;
//        ContentValues values = new ContentValues();
//        values.put(COLS_SHEET[0], sheet.getName());
//        values.put(COLS_SHEET[1], sheet.getImage());
//        sheetID = database.insert(AutoMarkSQLiteHelper.TAB_SHEET, null, values);
//        close();
//        return sheetID;
//    }
//    public List<TypeSheet> getAllSheet() {
//        open();
//        List<TypeSheet> typeSheets = new ArrayList<>();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_SHEET,null, null, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            typeSheets.add(cursorToTypeSheet(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        close();
//        return typeSheets;
//    }
//    public TypeSheet getSheet(long sheet) {
//        open();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_SHEET,null,   AutoMarkSQLiteHelper.COL_ID + " = '" + sheet +"' ",null, null, null, null);
//        cursor.moveToFirst();
//        if (!cursor.isAfterLast()){
//            close();
//            return cursorToTypeSheet(cursor);
//        }
//        close();
//        return null;
//    }
//    private TypeSheet cursorToTypeSheet(Cursor cursor) {
//        int typeSheetID = cursor.getInt(0);
//        String typeSheetName = cursor.getString(1);
//        byte[] image = cursor.getBlob(2);
//        return new TypeSheet(typeSheetID, typeSheetName,image);
//    }
//
//    public void deleteSheet(long sheet) {
//        open();
//        database.delete(AutoMarkSQLiteHelper.TAB_SHEET, AutoMarkSQLiteHelper.COL_ID + " = '"+sheet+"' ", null);
//        close();
//    }
//
//    //////////////////////////////////////////////     FRAME  /////////////////////////////
//    private static final String[] COLS_FRAME = {
//            AutoMarkSQLiteHelper.COL_X,
//            AutoMarkSQLiteHelper.COL_Y,
//            AutoMarkSQLiteHelper.COL_WIDTH,
//            AutoMarkSQLiteHelper.COL_HEIGHT,
//            AutoMarkSQLiteHelper.COL_COLS,
//            AutoMarkSQLiteHelper.COL_ROWS,
//            AutoMarkSQLiteHelper.COL_BLOCK,
//    };
//
//    public long addFrame(KhungTraLoi khungTraLoi) {
//        open();
//        long id;
//        ContentValues values = new ContentValues();
//        values.put(COLS_FRAME[0], khungTraLoi.getX());
//        values.put(COLS_FRAME[1], khungTraLoi.getY());
//        values.put(COLS_FRAME[2], khungTraLoi.getWidth());
//        values.put(COLS_FRAME[3], khungTraLoi.getHeight());
//        values.put(COLS_FRAME[4], khungTraLoi.getCols());
//        values.put(COLS_FRAME[5], khungTraLoi.getRows());
//        if(getFrame(khungTraLoi.getBlock())!=null){
//           id = database.update(AutoMarkSQLiteHelper.TAB_FRAME,values,
//                   AutoMarkSQLiteHelper.COL_BLOCK+" = '"+ khungTraLoi.getBlock()+"' ", null);
//        }
//       else{
//            values.put(COLS_FRAME[6], khungTraLoi.getBlock());
//            id = database.insert(AutoMarkSQLiteHelper.TAB_FRAME, null, values);
//        }
//        close();
//        return id;
//    }
//    public List<KhungTraLoi> getAllFrame() {
//        open();
//        List<KhungTraLoi> khungTraLois = new ArrayList<>();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_FRAME,null, null, null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            khungTraLois.add(cursortoFrame(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        close();
//        return khungTraLois;
//    }
//
//    public KhungTraLoi getFrame(long block) {
//        open();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_FRAME,null,   AutoMarkSQLiteHelper.COL_BLOCK+" = '"+block+"' ", null, null, null, null);
//        cursor.moveToFirst();
//        if (!cursor.isAfterLast())
//            return cursortoFrame(cursor);
//        return null;
//    }
//
//    private KhungTraLoi cursortoFrame(Cursor cursor) {
//        long id = cursor.getInt(0);
//        int x = cursor.getInt(1);
//        int y = cursor.getInt(2);
//        int width = cursor.getInt(3);
//        int height = cursor.getInt(4);
//        int cols = cursor.getInt(5);
//        int rows = cursor.getInt(6);
//        long block = cursor.getInt(7);
//        return new KhungTraLoi(id,x,y,width,height,cols,rows,block);
//    }
//    //////////////////////////////////////////////     Block  //////////////////////////
//    private static final String[] COLS_BLOCK = {
//            AutoMarkSQLiteHelper.COL_NAME,
//            AutoMarkSQLiteHelper.COL_SHEET
//    };
//
//    public long addBlock(Khoi khoi) {
//        open();
//        long id;
//        ContentValues values = new ContentValues();
//        values.put(COLS_BLOCK[0], khoi.getName());
//        values.put(COLS_BLOCK[1], khoi.getSheet());
//        id = database.insert(AutoMarkSQLiteHelper.TAB_BLOCK, null, values);
//        close();
//        return id;
//    }
//
//    public List<Khoi> getAllBlock(long sheet) {
//        open();
//        List<Khoi> khois = new ArrayList<>();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_BLOCK,null, AutoMarkSQLiteHelper.COL_SHEET+" = '"+sheet+"' ", null, null, null,
//                    AutoMarkSQLiteHelper.COL_ID+" ASC");
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            khois.add(cursortoBlock(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        close();
//        return khois;
//    }
//    private Khoi cursortoBlock(Cursor cursor) {
//        long id = cursor.getInt(0);
//        String name  = cursor.getString(1);
//        long sheet = cursor.getInt(2);
//        return new Khoi(id,name,sheet);
//    }
//
//    public void deleteBlock(long block) {
//        database.delete(AutoMarkSQLiteHelper.TAB_BLOCK, AutoMarkSQLiteHelper.COL_ID + " = '"+block+"' ", null);
//    }
//
//    public void deleteBlockCursor(Cursor cursor) {
//        String id =  cursor.getString(cursor.getColumnIndex(AutoMarkSQLiteHelper.COL_ID));
//        database.delete(AutoMarkSQLiteHelper.TAB_BLOCK, AutoMarkSQLiteHelper.COL_ID + " = '"+id+"' ",null);
//    }
//
//
//    public void deleteAllBlock() {
//        database.delete(AutoMarkSQLiteHelper.TAB_BLOCK, null, null);
//    }
//
//    //////////////////////////////////////////////     Answers  //////////////////////////
//
//
//    public long addCorrectAnswer(DapAn answer) {
//        open();
//        long id;
//        ContentValues values = new ContentValues();
//        values.put(COLS_CORRECT[1],answer.getAnswers());
//        if(getCorrectAnswer(answer.getTypeSheet(),answer.getCode())!=null) {
//            id = database.update(AutoMarkSQLiteHelper.TAB_CORRECT_ANSWER,values,
//                    AutoMarkSQLiteHelper.COL_SHEET+" = ? and "+AutoMarkSQLiteHelper.COL_CODE +" = ?",
//                    new String[]{answer.getTypeSheet()+"",answer.getCode()+""});
//        }else{
//            values.put(COLS_CORRECT[0],answer.getCode());
//            values.put(COLS_CORRECT[2],answer.getTypeSheet());
//            id = database.insert(AutoMarkSQLiteHelper.TAB_CORRECT_ANSWER, null, values);
//        }
//        close();
//        return id;
//    }
//
//    //    + COL_CORRECTS + " integer not null, "
////            + COL_IMAGE + " blob not null, "
//    private static final String[] COLS_YOUR_ANSWER = {
//            AutoMarkSQLiteHelper.COL_CODE,
//            AutoMarkSQLiteHelper.COL_ANSWER,
//            AutoMarkSQLiteHelper.COL_SBD,
//            AutoMarkSQLiteHelper.COL_SHEET,
//            AutoMarkSQLiteHelper.COL_CORRECTS,
//            AutoMarkSQLiteHelper.COL_IMAGE
//    };
//    public long addYourAnswer(DapAnThiSinh answer) {
//        open();
//        long id;
//        ContentValues values = new ContentValues();
//        values.put(COLS_YOUR_ANSWER[1],answer.getAnswers());
//        values.put(COLS_YOUR_ANSWER[4],answer.getCorrectNumber());
//        values.put(COLS_YOUR_ANSWER[5],answer.getImage());
//        if (getYourAnswer(answer.getTypeSheet(),answer.getCode(),answer.getSbd()) != null){
//            id = database.update(AutoMarkSQLiteHelper.TAB_YOUR_ANSWER,values,
//                    AutoMarkSQLiteHelper.COL_SHEET+" = ? and "+AutoMarkSQLiteHelper.COL_CODE +" = ? and "  + AutoMarkSQLiteHelper.COL_SBD +"   = ?" ,
//                    new String[]{answer.getTypeSheet()+"",answer.getCode(),answer.getSbd()});
//        }
//        else{
//            values.put(COLS_YOUR_ANSWER[0],answer.getCode());
//            values.put(COLS_YOUR_ANSWER[2],answer.getSbd());
//            values.put(COLS_YOUR_ANSWER[3],answer.getTypeSheet());
//            id = database.insert(AutoMarkSQLiteHelper.TAB_YOUR_ANSWER, null, values);
//        }
//
//
//
//        close();
//        return id;
//    }
//
//
//    public DapAn getCorrectAnswer(long sheet, String code) {
//        open();
////        Log.d("TAG", AutoMarkSQLiteHelper.COL_SHEET + " = '"+sheet+"' and " + AutoMarkSQLiteHelper.COL_CODE + " =  '"+code+"'");
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_CORRECT_ANSWER,null,
//                AutoMarkSQLiteHelper.COL_SHEET + " = '"+sheet+"' and " + AutoMarkSQLiteHelper.COL_CODE + " =  '"+code+"'",null, null, null, null);
//
//        cursor.moveToFirst();
//        if (!cursor.isAfterLast())
//            return cursortoCorrectAnswer(cursor);
//        return null;
//    }
//    public void deleteCorrectAnswers(String code) {
//        open();
//        database.delete(AutoMarkSQLiteHelper.TAB_CORRECT_ANSWER, AutoMarkSQLiteHelper.COL_CODE + " = '"+code+"' ", null);
//        close();
//    }
//    private DapAn cursortoCorrectAnswer(Cursor cursor) {
//        long id = cursor.getInt(0);
//        String code = cursor.getString(1);
//        String answer = cursor.getString(2);
//        long sheet = cursor.getInt(3);
//        return new DapAn(id,sheet,answer,code);
//    }
//
//    public DapAnThiSinh getYourAnswer(long sheet, String code, String sbd) {
//        open();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_YOUR_ANSWER,null,
//                AutoMarkSQLiteHelper.COL_SHEET + " = ? and " + AutoMarkSQLiteHelper.COL_CODE + " = ? and "
//                        + AutoMarkSQLiteHelper.COL_SBD +"   = ?" ,
//                new String[]{sheet+"",code,sbd}, null, null, null);
//        cursor.moveToFirst();
//        if (!cursor.isAfterLast())
//            return cursortoYourAnswer(cursor);
//        return null;
//    }
//
//    private DapAnThiSinh cursortoYourAnswer(Cursor cursor) {
//        long id = cursor.getInt(0);
//        String code = cursor.getString(1);
//        String sbd = cursor.getString(2);
//        String answer = cursor.getString(3);
//        long sheet = cursor.getLong(4);
//        int corrects = cursor.getInt(5);
//        byte[] image = cursor.getBlob(6);
//        return new DapAnThiSinh(id,sheet,sbd,answer,code,corrects,image);
//    }
//
//    public List<DapAn> getAllCode(long sheet) {
//        open();
//        List<DapAn> answers = new ArrayList<>();
//        Cursor cursor = database.query(AutoMarkSQLiteHelper.TAB_CORRECT_ANSWER,null,
//                AutoMarkSQLiteHelper.COL_SHEET +" = '"+ sheet + "'", null, null, null, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            answers.add(cursortoCorrectAnswer(cursor));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        close();
//        return answers;
//    }
//
//
//}
