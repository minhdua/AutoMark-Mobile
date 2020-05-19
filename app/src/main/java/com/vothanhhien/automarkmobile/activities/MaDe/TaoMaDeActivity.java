//package com.vothanhhien.automarkmobile.activities.MaDe;
//
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.adapter.DapAnAdapter;
//import com.vothanhhien.automarkmobile.models.Khoi;
//import com.vothanhhien.automarkmobile.models.DapAn;
//import com.vothanhhien.automarkmobile.models.KhungTraLoi;
//import com.vothanhhien.automarkmobile.models.SharedPrefManager;
//import test.AutoMarkDataBaseSource;
//import com.vothanhhien.automarkmobile.utils.UtilityFunctions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TaoMaDeActivity extends AppCompatActivity {
//
//    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
//
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//           if (s.length() == 3) {
//               btnCheck.setClickable(true);
//               btnCheck.setEnabled(true);
//
//           }else{
//               btnCheck.setClickable(false);
//               btnCheck.setEnabled(false);
//           }
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//
//    };
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.sheet_menu, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.evaluate:
////                Intent answersIntent = new Intent(SheetSampleActivity.this, AnswersActivity.class);
////                answersIntent.putExtra("questioncount", numberQuestion);
////                startActivity(answersIntent);
////                return true;
////
////            default:
////                return super.onOptionsItemSelected(item);
////
////        }
////    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sheet);
//        init();
//        Bundle bundle = getIntent().getExtras();
//        sheet = bundle.getLong("Sheet");
//        List<Khoi> khois = autoMarkDataBaseSource.getAllBlock(sheet);
//        numberQuestion = 0;
//        for(int i = 2; i< khois.size(); i++){
//            KhungTraLoi khungTraLoi = autoMarkDataBaseSource.getFrame(khois.get(i).getBlockID());
//            numberQuestion += khungTraLoi.getRows();
//        }
//
//        btnCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getBaseContext(),""+correctAnswerCharacterList.get(0),Toast.LENGTH_SHORT).show();
//                edtCode.setEnabled(false);
//                btnCheck.setVisibility(View.GONE);
//                btnCheck.setClickable(false);
//                btnCheck.setEnabled(false);
//                btnApply.setVisibility(View.VISIBLE);
//                btnApply.setClickable(true);
//                btnApply.setEnabled(true);
//
//                DapAn answer = autoMarkDataBaseSource.getCorrectAnswer(sheet,edtCode.getText().toString());
//                if (answer != null){
//                    correctAnswerCharacterList = UtilityFunctions.ConvertStringtoList(answer.getAnswers(),numberQuestion);
//                }else{
//                    correctAnswerCharacterList = UtilityFunctions.createDefaultAnswerList(numberQuestion);
//                }
//                setRecyclerView();
//            }
//        });
//        btnApply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String code = edtCode.getText().toString();
//                String correctAnswers = UtilityFunctions.ConvertListtoString(correctAnswerCharacterList,numberQuestion);
//               DapAn answer = new DapAn(sheet,correctAnswers,code);
//                autoMarkDataBaseSource.addCorrectAnswer(answer);
//                Toast.makeText(getBaseContext(),"Saved! ",Toast.LENGTH_SHORT).show();
//            }
//        });
//        edtCode.addTextChangedListener(mTextEditorWatcher);
//        code = bundle.getString("Code","");
//        if (!code.equals("")){
//            edtCode.setText(code);
//            btnCheck.performClick();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("MainActivity", "onResume: ");
//    }
//
////    @Override
////    public void onBackPressed() {
////        new AlertDialog.Builder(this)
////                .setIcon(android.R.drawable.ic_dialog_alert)
////                .setTitle("Closing app")
////                .setMessage("Are you sure you want to close this app?")
////                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        finish();
////                    }
////
////                })
////                .setNegativeButton("No", null)
////                .show();
////    }
//
//    public void setRecyclerView(){
//        sheetOMR.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        sheetOMR.setLayoutManager(layoutManager);
//
//        mAdapter = new DapAnAdapter(getBaseContext(),correctAnswerCharacterList);
//        sheetOMR.setAdapter(mAdapter);
//        sheetOMR.addItemDecoration(new DividerItemDecoration(sheetOMR.getContext(), DividerItemDecoration.VERTICAL));
//
//    }
//    private void init(){
//        sheetOMR = findViewById(R.id.sheet_recycler_list);
//        btnCheck = findViewById(R.id.buttonCheck);
//        btnCheck.setClickable(false);
//        btnCheck.setEnabled(false);
//        btnApply = findViewById(R.id.buttonApply);
//        btnApply.setVisibility(View.GONE);
//        btnApply.setClickable(false);
//        btnApply.setEnabled(false);
//        getSupportActionBar().setTitle("TẠO ĐÁP ÁN");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        edtCode = findViewById(R.id.editTextCode);
//        autoMarkDataBaseSource = new AutoMarkDataBaseSource(TaoMaDeActivity.this);
//        //sPref = SharedPrefManager.getInstance();
//    }
//    String code;
//    private Button btnCheck,btnApply ;
//    private EditText edtCode;
//    private AutoMarkDataBaseSource autoMarkDataBaseSource;
//    private long sheet;
//    private int numberQuestion;
//    private RecyclerView sheetOMR;
//    private  LinearLayoutManager layoutManager;
//    private DapAnAdapter mAdapter;
//    SharedPrefManager sPref;
//    String correctAnswer;
//    List<Character> correctAnswerCharacterList = new ArrayList<Character>();
//}
