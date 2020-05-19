//package com.vothanhhien.automarkmobile.activities.MauDe;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.adapter.MauDeAdapter;
//import com.vothanhhien.automarkmobile.models.TypeSheet;
//import test.AutoMarkDataBaseSource;
//
//import java.util.List;
//
//public class MauDeActivity extends AppCompatActivity {
//
//    private static final int REQUEST_CODE = 1000;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycler);
//        init();
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MauDeActivity.this, TaoMauDeActivity.class);
//                startActivityForResult(intent,REQUEST_CODE);
//            }
//        });
//        setRecyclerView();
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_CODE) {
//            if(resultCode == Activity.RESULT_OK) {
//                setRecyclerView();
//            } else {
//
//            }
//        }
//    }
//    public void setRecyclerView(){
//        setOfOMR.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        setOfOMR.setLayoutManager(layoutManager);
//        List<TypeSheet> list;
//        list = autoMarkDataBaseSource.getAllSheet();
//        mAdapter = new MauDeAdapter(MauDeActivity.this,list);
//        setOfOMR.setAdapter(mAdapter);
//        setOfOMR.addItemDecoration(new DividerItemDecoration(setOfOMR.getContext(), DividerItemDecoration.VERTICAL));
//    }
//
//    public void init(){
//        btnAdd = findViewById(R.id.btn_adds_setofomr);
//        getSupportActionBar().setTitle("CÁC MẪU ĐỀ THI");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setOfOMR = findViewById(R.id.setofomr_recycle_list);
//        autoMarkDataBaseSource = new AutoMarkDataBaseSource(MauDeActivity.this);
//        editTextName = findViewById(R.id.editTextName);
//        buttonSubmit = findViewById(R.id.buttonSubmit);
//        addSheet = findViewById(R.id.addSheet);
//    }
//
//    private AutoMarkDataBaseSource autoMarkDataBaseSource;
//    private RecyclerView setOfOMR;
//    private ImageView btnAdd,buttonSubmit;
//    private LinearLayoutManager layoutManager;
//    private MauDeAdapter mAdapter;
//    private EditText editTextName;
//     private RelativeLayout addSheet;
//}
