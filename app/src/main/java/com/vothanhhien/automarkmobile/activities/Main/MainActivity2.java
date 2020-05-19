//package com.vothanhhien.automarkmobile.activities.Main;
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
//import com.vothanhhien.automarkmobile.activities.MauDe.MauDeActivity;
//import com.vothanhhien.automarkmobile.constants.SC;
//import com.yarolegovich.lovelydialog.LovelyTextInputDialog;
//
//public class MainActivity2 extends AppCompatActivity {
//
//
//    private String TAG = "MainActivity";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        init();
//        btnSetOfOMR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity2.this, MauDeActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonStorage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setEnabled(false);
//                v.setClickable(false);
//                new LovelyTextInputDialog(MainActivity2.this, R.style.TintTheme)
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
//                                Toast.makeText(MainActivity2.this, SC.INPUT_DIR+ SC.IMAGE_PREFIX, Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();
//
//                v.setEnabled(true);
//                v.setClickable(true);
//            }
//        });
//    }
//
//
//    public  void init(){
//        btnSetOfOMR = findViewById(R.id.setofomr);
//        buttonStorage = findViewById(R.id.buttonStorage);
//    }
//    private Button btnSetOfOMR,buttonStorage;
//}
