package com.vothanhhien.automarkmobile.activities.Fix;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;
import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.constants.SC;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.models.HinhAnh;
import com.vothanhhien.automarkmobile.models.KhungTraLoi;
import com.vothanhhien.automarkmobile.models.MauTraLoi;
import com.vothanhhien.automarkmobile.sqlite.KhungTraLoiDatabase;
import com.vothanhhien.automarkmobile.utils.FileUtils;
import com.vothanhhien.automarkmobile.utils.Utils;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;


public class FixLocation extends AppCompatActivity {
    private static final String mOpenCvLibrary = "opencv_java3";
    static {
        System.loadLibrary(mOpenCvLibrary);
    }
    private Toolbar mytoolbar;
    private ImageView imageView;
    private Spinner spinner;
    private BaiThi baiThi;
    private  Mat image;
    private NumberPicker numberPickerX,numberPickerY,numberPickerCols,numberPickerRows,numberPickerWidth,numberPickerHeight;
    private  List<KhungTraLoi> khungTraLois = new ArrayList<>();
    private Bitmap bitmap;
    private KhungTraLoiDatabase khungTraLoiDatabase;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_made) {
            for(int i=0;i<khungTraLois.size();i++){
                khungTraLoiDatabase.CapNhat(khungTraLois.get(i));
            }
            Toast.makeText(FixLocation.this, "Đã Lưu thay đổi!", Toast.LENGTH_SHORT).show();
        } else if(id == 16908332){
            onBackPressed();
            finish();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.hand_da, menu);
        return true;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        long addr = intent.getLongExtra("Hinh anh", 0);
        Mat _image = new Mat( addr );
        image = _image.clone();
        baiThi = (BaiThi) intent.getSerializableExtra("Bai thi");
        long mauTraLoi = baiThi.getLoaiDe();
        //region Thiết lập thanh toolbar cho activity
        mytoolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView) findViewById(R.id.imageDisplay);
        spinner = findViewById(R.id.spinnerKhung);
        numberPickerX = findViewById(R.id.number_pickerX);
        numberPickerY = findViewById(R.id.number_pickerY);
        numberPickerCols = findViewById(R.id.number_pickerCols);
        numberPickerRows = findViewById(R.id.number_pickerRows);
        numberPickerWidth = findViewById(R.id.number_pickerWidth);
        numberPickerHeight = findViewById(R.id.number_pickerHeight);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadSpinner();
        loadImageView();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadView(position);

//                Toast.makeText(getBaseContext(),position+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for( int id : new int []{R.id.number_pickerX,R.id.number_pickerY,R.id.number_pickerWidth,R.id.number_pickerHeight,R.id.number_pickerCols,R.id.number_pickerRows} ){
            NumberPicker numberPicker = findViewById(id);
            numberPicker.setValueChangedListener(new ValueChangedListener() {
                @Override
                public void valueChanged(int value, ActionEnum action) {
//                    Toast.makeText(getBaseContext(),image.width()+"-"+image.height(),Toast.LENGTH_SHORT).show();
                    capnhatKhung();
                    loadImageView();
                }
            });
        }
    }
    public void capnhatKhung(){
        int idKhung = spinner.getSelectedItemPosition();
        int x = numberPickerX.getValue();
        int y = numberPickerY.getValue();
        int w = numberPickerWidth.getValue();
        int h = numberPickerHeight.getValue();
        int c = numberPickerCols.getValue();
        int r = numberPickerRows.getValue();
        khungTraLois.get(idKhung).capnhat(x,y,w,h,c,r);
    }
    public void loadSpinner(){
        // lay tat ca cac khung của bai thi hien tai
        khungTraLoiDatabase = new KhungTraLoiDatabase(FixLocation.this);
        khungTraLois = khungTraLoiDatabase.TatCaKhungTL(baiThi.getLoaiDe());
        List<String> khungItems = new ArrayList<>();
        khungItems.add("Mã đề");
        khungItems.add("Số báo danh");
        // load vao spinner
        for(int i = 2; i<khungTraLois.size();i++){
            khungItems.add("Khung trả lời "+(i-1));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        khungItems
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spinner.setAdapter(adapter);
    }
    public void loadView(int position){
        //lấy khung đang chọn
        KhungTraLoi khung = khungTraLois.get(position);
        //load vào các number picker và load hình ảnh
        numberPickerX.setValue(khung.getX());
        numberPickerY.setValue(khung.getY());
        numberPickerCols.setValue(khung.getCols());
        numberPickerRows.setValue(khung.getRows());
        numberPickerWidth.setValue(khung.getWidth());
        numberPickerHeight.setValue(khung.getHeight());
    }
    public void loadImageView(){
        Mat _image = image.clone();
        Utils.drawAllChoice(_image,khungTraLois);
        bitmap = Utils.matToBitmapRotate(_image);
        imageView.setImageBitmap(bitmap);
        FileUtils.saveBitmap(bitmap,SC.CURR_DIR,"test_1.jpg");
    }
}
