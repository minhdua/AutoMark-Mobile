//package com.vothanhhien.automarkmobile.activities.MauDe;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TableRow;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.mohammedalaa.seekbar.RangeSeekBarView;
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.constants.SC;
//import com.vothanhhien.automarkmobile.models.Khoi;
//import com.vothanhhien.automarkmobile.models.KhungTraLoi;
//import com.vothanhhien.automarkmobile.models.TypeSheet;
//import test.AutoMarkDataBaseSource;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//public class TaoMauDeActivity extends AppCompatActivity {
//    private static final String TAG ="TypeSheetActivity";
//    private static final int IMAGE_PICK_CODE = 1001;
//    private static final int PERMISSION_CODE = 1001;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_type_sheet);
//        init();
//        buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TypeSheet typeSheet = new TypeSheet(editTextName.getText().toString(),imageView2Byte(image_view));
//                long typeSheetID = autoMarkDataBaseSource.addSheet(typeSheet);
//                long blockID;
//
//                blockID = autoMarkDataBaseSource.addBlock(new Khoi("Code",typeSheetID));
//                autoMarkDataBaseSource.addFrame(new KhungTraLoi(SC.CODE_CAU_TRA_LOI,blockID));
//
//                blockID = autoMarkDataBaseSource.addBlock(new Khoi("Student ID",typeSheetID));
//                autoMarkDataBaseSource.addFrame(new KhungTraLoi(SC.ID_CAU_TRA_LOI,blockID));
//
//                int answersNumber = seekBarBlock.getCurrentValue();
//                for(int i =1; i<= answersNumber;i++){
//                    blockID = autoMarkDataBaseSource.addBlock(new Khoi("Answers "+i,typeSheetID));
//                    autoMarkDataBaseSource.addFrame(new KhungTraLoi(SC.BLOCK_CAU_TRA_LOI,blockID));
//                }
//                clearView();
//                Toast.makeText(TaoMauDeActivity.this,"Was saved successfully!",Toast.LENGTH_LONG).show();
//                final Intent intent = new Intent();
//                setResult(Activity.RESULT_OK);
//            }
//        });
//
//        buttonBrowser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
//                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                        requestPermissions(permissions,PERMISSION_CODE);
//                    }else{
//                        pickImageFromGallery();
//                    }
//                }else{
//                    pickImageFromGallery();
//                }
//            }
//        });
//    }
//
//    public void pickImageFromGallery(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,IMAGE_PICK_CODE);
//    }
////
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case PERMISSION_CODE:{
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permissions was granted
//                    pickImageFromGallery();
//                }else{
//                    // permission was denied
//                    Toast.makeText(this,"Permission was denied! ",Toast.LENGTH_LONG);
//                }
//            }
//
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
//            Uri uri = data.getData();
//            try{
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                image_view.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }else{
//            super.onActivityResult(requestCode, resultCode, data);
//
//        }
//    }
////
////    @Override
////    protected void onResume() {
////        super.onResume();
////    }
////
//    private byte[] imageView2Byte(ImageView image) {
//        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        return byteArray;
//    }
////
//    public void clearView(){
//        editTextName.setText("");
//        image_view.setImageBitmap(null);
//        seekBarBlock.setCurrentValue(3);
//    }
////    @Override
////    public void onBackPressed() {
////        super.onBackPressed();
////        finish();
////    }
////
//    void init(){
//        getSupportActionBar().setTitle("Tạo Mẫu Đề Thi");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        editTextName = findViewById(R.id.editTextName);
//        buttonSave = findViewById(R.id.buttonSave);
//        buttonBrowser = findViewById(R.id.buttonBrowser);
//        image_view = findViewById(R.id.image_view);
//        seekBarBlock = findViewById(R.id.seekBarBlock);
//        autoMarkDataBaseSource = new AutoMarkDataBaseSource(TaoMauDeActivity.this);
//    }
//    private RangeSeekBarView seekBarBlock;
//    private EditText editTextName;
//    private TableRow buttonSave, buttonBrowser;
//    private ImageView  image_view;
//    private AutoMarkDataBaseSource autoMarkDataBaseSource;
//}
