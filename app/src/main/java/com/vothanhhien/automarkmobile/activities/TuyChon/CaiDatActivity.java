//package com.vothanhhien.automarkmobile.activities.TuyChon;
//
//import android.graphics.Bitmap;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.travijuu.numberpicker.library.Enums.ActionEnum;
//import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
//import com.travijuu.numberpicker.library.NumberPicker;
//
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.google.android.material.snackbar.Snackbar;
//import com.jaredrummler.materialspinner.MaterialSpinner;
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.adapter.MauDeAdapter;
//import com.vothanhhien.automarkmobile.models.Khoi;
//import com.vothanhhien.automarkmobile.models.KhungTraLoi;
//import com.vothanhhien.automarkmobile.models.TypeSheet;
//import test.AutoMarkDataBaseSource;
//import com.vothanhhien.automarkmobile.utils.Utils;
//
//import org.opencv.core.Point;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CaiDatActivity extends AppCompatActivity {
//    private static final String mOpenCvLibrary = "opencv_java3";
//    static {
//        System.loadLibrary(mOpenCvLibrary);
//    }
//
//    private LinearLayoutManager layoutManager;
//    private MauDeAdapter mAdapter;
//    private long block;
//    private List<Long> blockIDs = new ArrayList<>();
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_frame);
//        init();
//
//        spnTypeFrame.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                setFrameView(position);
//                spnTypeFrame.setSelectedIndex(position);
//            }
//
//        });
//
//        imageViewSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KhungTraLoi khungTraLoi = getFrameView();
//                autoMarkDataBaseSource.addFrame(khungTraLoi);
//                Snackbar.make(v, "Saved !" , Snackbar.LENGTH_LONG).show();
//            }
//        });
//        for( int id : new int []{R.id.number_pickerX,R.id.number_pickerY,R.id.number_pickerWidth,R.id.number_pickerHeight} ){
//            NumberPicker btn = findViewById(id);
//            btn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                    imageViewCheck.performClick();
//                    return false;
//                }
//            });
//
//            btn.setValueChangedListener(new ValueChangedListener() {
//                @Override
//                public void valueChanged(int value, ActionEnum action) {
//                    imageViewCheck.performClick();
//                }
//            });
//
//        }
//        imageViewCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KhungTraLoi khungTraLoi = getFrameView();
//                TypeSheet typeSheet = autoMarkDataBaseSource.getSheet(sheet);
//                byte [] image = typeSheet.getImage();
//                Bitmap bitmap = Utils.decodeBitmapFromByteArray(image,840,1200);
//                Bitmap bitmapRotate =Utils.rotateBitmap(bitmap,-90);
//                Point [] points = new Point[4];
//                int x = khungTraLoi.getX();
//                int y = khungTraLoi.getY();
//                int w = khungTraLoi.getWidth();
//                int h = khungTraLoi.getHeight();
////
//                points[0] = new Point(x,y);
//                points[1] = new Point(x,y+h);
//                points[2] = new Point(x+w,y+h);
//                points[3] = new Point(x+w,y);
//
//
//                Bitmap subBitmap = Utils.cropBitmap(bitmapRotate,points);
//                subBitmap = Utils.rotateBitmap(subBitmap,90);
//                imageViewDisplay.setImageBitmap(subBitmap);
////                Snackbar.make(v, ""+subBitmap.getWidth()+"-"+subBitmap.getHeight() , Snackbar.LENGTH_LONG).show();
//            }
//        });
//
//        imageViewClear.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) {
//                number_pickerX.setValue(number_pickerX.getMin());
//                number_pickerY.setValue(number_pickerY.getMin());
//                number_pickerWidth.setValue(number_pickerWidth.getMax());
//                number_pickerHeight.setValue(number_pickerHeight.getMax());
//                number_pickerCols.setValue(number_pickerCols.getMin());
//                number_pickerRows.setValue(number_pickerRows.getMin());
//
//            }
//        });
//
//        setFrameView(0);
//    }
//
//
//    public KhungTraLoi getFrameView(){
//        long block = (long) (blockIDs.get(spnTypeFrame.getSelectedIndex()));
//        int x = number_pickerX.getValue();
//        int y = number_pickerY.getValue();
//        int width = number_pickerWidth.getValue();
//        int height = number_pickerHeight.getValue();
//        int cols = number_pickerCols.getValue();
//        int rows = number_pickerRows.getValue();
//        return  new KhungTraLoi(x,y,width,height,cols,rows,block);
//    }
//
//    public void setFrameView(int position){
//        KhungTraLoi khungTraLoi = autoMarkDataBaseSource.getFrame(blockIDs.get(position));
//        if (null != khungTraLoi){
//            number_pickerX.setValue(khungTraLoi.getX());
//            number_pickerY.setValue(khungTraLoi.getY());
//            number_pickerWidth.setValue(khungTraLoi.getWidth());
//            number_pickerHeight.setValue(khungTraLoi.getHeight());
//            number_pickerCols.setValue(khungTraLoi.getCols());
//            number_pickerRows.setValue(khungTraLoi.getRows());
//            imageViewDisplay.setImageBitmap(null);
//            imageViewCheck.performClick();
//        }else{
//            imageViewClear.performClick();
//        }
//    }
//    public void loadSpinner(){
//        List<String> typeFrameItems = new ArrayList<>();
//
//        List<Khoi> khois = autoMarkDataBaseSource.getAllBlock(sheet);
//
//        for(Khoi khoi : khois){
//            typeFrameItems.add(khoi.getName());
//            blockIDs.add(khoi.getBlockID());
//        }
//
//        spnTypeFrame.setItems(typeFrameItems);
//    }
//
//    public void init(){
//        spnTypeFrame = findViewById(R.id.spinnerTypeFrame);
//        number_pickerX = findViewById(R.id.number_pickerX);
//        number_pickerY = findViewById(R.id.number_pickerY);
//        number_pickerWidth = findViewById(R.id.number_pickerWidth);
//        number_pickerHeight = findViewById(R.id.number_pickerHeight);
//        number_pickerCols = findViewById(R.id.number_pickerCols);
//        number_pickerRows= findViewById(R.id.number_pickerRows);
//        imageViewSave = findViewById(R.id.imageViewSave);
//        imageViewClear = findViewById(R.id.imageViewClear);
//        imageViewDisplay = findViewById(R.id.imageViewDisplay);
//        imageViewCheck = findViewById(R.id.imageViewCheck);
//        autoMarkDataBaseSource = new AutoMarkDataBaseSource(getBaseContext());
//        Bundle bundle = getIntent().getExtras();
//        sheet = bundle.getLong("Sheet");
//        getSupportActionBar().setTitle("Blocks Setting");
//        loadSpinner();
//    }
//    private long sheet;
//    private AutoMarkDataBaseSource autoMarkDataBaseSource;
//    private NumberPicker number_pickerX,number_pickerY,number_pickerWidth,number_pickerHeight,number_pickerRows,number_pickerCols;
//    private MaterialSpinner spnTypeFrame;
//    private ImageView imageViewSave,imageViewClear,imageViewDisplay,imageViewCheck;
//}
