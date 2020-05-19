//package com.vothanhhien.automarkmobile.activities.TuyChon;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.activities.MaDe.TaoMaDeActivity;
//import com.vothanhhien.automarkmobile.adapter.MaDeAdapter;
//import com.vothanhhien.automarkmobile.models.DapAn;
//import test.AutoMarkDataBaseSource;
//
//import java.util.List;
//
//public class MaDeActivity extends AppCompatActivity {
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycler);
//        init();
//        Bundle bundle = getIntent().getExtras();
//        sheet = bundle.getLong("Sheet");
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), TaoMaDeActivity.class);
//                intent.putExtra("Sheet",sheet);
//                startActivity(intent);
//                setRecyclerView();
//            }
//        });
//
//        setRecyclerView();
//    }
//    public void setRecyclerView(){
//        setOfOMR.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        setOfOMR.setLayoutManager(layoutManager);
//        List<DapAn> list;
//        list = autoMarkDataBaseSource.getAllCode(sheet);
//        mAdapter = new MaDeAdapter(getBaseContext(),list);
//        setOfOMR.setAdapter(mAdapter);
//        setOfOMR.addItemDecoration(new DividerItemDecoration(setOfOMR.getContext(), DividerItemDecoration.VERTICAL));
//    }
//
//    public void init(){
//        btnAdd = findViewById(R.id.btn_adds_setofomr);
//        getSupportActionBar().setTitle("CÁC MÃ ĐỀ");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setOfOMR = findViewById(R.id.setofomr_recycle_list);
//        autoMarkDataBaseSource = new AutoMarkDataBaseSource(MaDeActivity.this);
//    }
//    private long sheet;
//    private AutoMarkDataBaseSource autoMarkDataBaseSource;
//    private RecyclerView setOfOMR;
//    private ImageView btnAdd;
//    private LinearLayoutManager layoutManager;
//    private MaDeAdapter mAdapter;
//
//    public void resetGraph(Context context) {
//        setRecyclerView();
//    }
//}
