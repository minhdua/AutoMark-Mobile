//package com.vothanhhien.automarkmobile.activities.MauDe;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.activities.TuyChon.ChamDiemActivity;
//import com.vothanhhien.automarkmobile.activities.TuyChon.CaiDatActivity;
//import com.vothanhhien.automarkmobile.activities.TuyChon.MaDeActivity;
//import com.vothanhhien.automarkmobile.constants.SC;
////import testa.AutoMarkDataBaseSource;
//import com.yarolegovich.lovelydialog.LovelyTextInputDialog;
//
//
//public class TuyChonActivity extends AppCompatActivity{
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_options_set);
//        init();
//        Bundle bundle =  getIntent().getExtras();
//        sheet = bundle.getLong("Sheet");
//        autoMarkDataBaseSource = new AutoMarkDataBaseSource(getBaseContext());
//        getSupportActionBar().setTitle(autoMarkDataBaseSource.getSheet(sheet).getName());
//        btnCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getBaseContext(),"button Create",Toast.LENGTH_SHORT).show();
//                Intent intentChild1 = new Intent(getBaseContext(), MaDeActivity.class);
//                intentChild1.putExtra("Sheet",sheet);
//                startActivity(intentChild1);
//            }
//        });
//        btnBlock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentChild2 = new Intent(getBaseContext(), CaiDatActivity.class);
//                intentChild2.putExtra("Sheet",sheet);
//                startActivity(intentChild2);
//            }
//        });
//        btnCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getBaseContext(),"button Camera",Toast.LENGTH_SHORT).show();
//                Intent intentChild3 = new Intent(getBaseContext(), ChamDiemActivity.class);
//                intentChild3.putExtra("Sheet",sheet);
//                startActivity(intentChild3);
//            }
//        });
//
//        btnExport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(),"button Export",Toast.LENGTH_SHORT).show();
////                Intent intentChild3 = new Intent(getBaseContext(),ExportActivity.class);
////                intentChild3.putExtra("SetID",sheet);
////                startActivity(intentChild3);
//            }
//        });
//        buttonStorage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setEnabled(false);
//                v.setClickable(false);
//                new LovelyTextInputDialog(TuyChonActivity.this, R.style.TintTheme)
//                        .setTopColorRes(R.color.dark_gray)
//                        .setTitle(R.string.storage_folder)
//                        .setIcon(R.drawable.ic_storage_black_24dp)
//                        .setInitialInput(SC.INPUT_DIR+ SC.IMAGE_PREFIX)
//                        .setHint(getString(R.string.storage_hint))
//                        .setInputFilter(R.string.storage_invalid, new LovelyTextInputDialog.TextFilter() {
//                            @Override
//                            public boolean check(String text) {
//                                // TODO: update this filter
//                                return text.matches("[\\w\\d]+[/\\w\\d]*");
//                            }
//                        })
//                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
//                            @Override
//                            public void onTextInputConfirmed(String text) {
////                                Log.d(TAG,"Received text: "+text);
//                                if(!text.endsWith("/"))
//                                    text+="/";
//                                String [] splits = text.split("/");
//                                String tmp = "";
//                                SC.INPUT_DIR = splits[0]+"/";
//                                int ctr = 0;
//                                for(String s : splits){
//                                    tmp = tmp+s+"/";
//                                    ctr++;
//                                    if(ctr == splits.length-1){
//                                        SC.INPUT_DIR = tmp;
//                                        SC.INPUT_ORIG_DIR = "Original_"+ SC.INPUT_DIR;
//                                    }
//                                    if(ctr == splits.length){
//                                        SC.IMAGE_PREFIX = s;
//                                    }
//                                }
//                                SC.CURR_DIR =  SC.STORAGE_HOME +"/" + SC.INPUT_DIR;
//                                SC.CURR_ORIG_DIR =  SC.STORAGE_HOME +"/" + SC.INPUT_ORIG_DIR;
//                                // checkmakeDirs will be called before saving.
//                                Toast.makeText(TuyChonActivity.this, SC.INPUT_DIR+ SC.IMAGE_PREFIX, Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();
//
//                v.setEnabled(true);
//                v.setClickable(true);
//            }
//        });
//
//        btnShowResult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(),"button Result",Toast.LENGTH_SHORT).show();
////                Intent intentChild4 = new Intent(getBaseContext(),ShowResultActivity.class);
////                intentChild4.putExtra("SetID",sheet);
////                startActivity(intentChild4);
//            }
//        });
//    }
//    private void init() {
//        btnCreate = findViewById(R.id.buttonCreateCode);
//        btnCamera = findViewById(R.id.buttonCamera);
//        btnShowResult = findViewById(R.id.buttonResult);
//        btnExport = findViewById(R.id.buttonExport);
//        btnBlock= findViewById(R.id.buttonEditBlock);
//        buttonStorage = findViewById(R.id.buttonStorage);
//    }
//    private AutoMarkDataBaseSource autoMarkDataBaseSource;
//    private Button btnCreate,btnCamera,btnShowResult,btnExport,btnBlock,buttonStorage;
//    private long sheet;
//}
