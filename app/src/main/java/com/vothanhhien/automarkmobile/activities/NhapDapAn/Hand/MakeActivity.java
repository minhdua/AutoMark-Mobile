package com.vothanhhien.automarkmobile.activities.NhapDapAn.Hand;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.DapAn.DanhSachDapAn;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.sqlite.DapAnDatabase;
import com.vothanhhien.automarkmobile.activities.DapAn.DapAn;
import com.vothanhhien.automarkmobile.sqlite.MauTraLoiDatabase;

import java.util.ArrayList;
import java.util.List;



public class MakeActivity extends AppCompatActivity {
    Context myContext;
    BaiThi thisBaiThi;
    Toolbar mytoolbar;
    String myMaDe;
    public static final int BackButton = 16908332;
    RecyclerView recyclerView;
    DapAnDatabase databaseAction;   // Dành cho thao tác 332;dữ liệu với đáp án
    DapAn dapAn = new DapAn();   // Dành cho lưu trữ đáp án của mã đề hiện hành
    String id_made; // Lưu ID của mã đề hiện hành - ở chế độ xem lại
    protected boolean isReview = false;
    protected boolean isMaybe = false;
    MauTraLoiDatabase mauTraLoiDatabase;
    // Sự kiện khi tác dộng vào RecyclerView
    class rvClickListener implements DapAn_RecycleViewItemClickListener {

        @Override
        public void onClick(View view, int i) {
        }

        @Override
        public void onLongClick(View view, int i) {
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_made) {
            if (!isReview || isMaybe)
                if (!isMaybe)
                    CheckAndSave();
                else
                    CheckAndSave(dapAn);
            else
                DeleteX();
        } else if (id == BackButton) {
            if (!isReview || isMaybe) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MakeActivity.this);
                builder.setTitle("CHƯA LƯU?")
                        .setMessage("Hiện đáp án cho mã đề \"" + myMaDe + "\" của bài kiểm tra mang tên \"" +
                                thisBaiThi.getTen() + "\" vẫn chưa được lưu! Hãy kiểm tra cẩn thận trước khi xác nhận!")
                        .setPositiveButton("Sửa tiếp", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (databaseAction.delete(Integer.parseInt(id_made)) != -1) {
                                    // Create for return RESULT
                                    Intent data = new Intent();
                                    String myData = Base64.encodeToString(dapAn.getID().getBytes(), Base64.DEFAULT) + "@@" +
                                            Base64.encodeToString(dapAn.getMaBaiThi().getBytes(), Base64.DEFAULT) + "@@" +
                                            Base64.encodeToString(dapAn.getMaDe().getBytes(), Base64.DEFAULT) + "@@" +
                                            Base64.encodeToString(dapAn.getDapAn().getBytes(), Base64.DEFAULT) + "@@" +
                                            Base64.encodeToString("true".getBytes(), Base64.DEFAULT);
                                    data.setData(Uri.parse(myData));
                                    setResult(DanhSachDapAn.DELETED_CODE, data);
                                    // end
                                    onBackPressed();
                                    finish();
                                }
                            }
                        });
                builder.create();
                builder.show();
            } else {
                onBackPressed();
                finish();
            }
        } else {
            Toast.makeText(myContext, "Đã xuất hiện lựa chọn lỗi!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void DeleteX() {
        if (Integer.parseInt(id_made) == -1) {
            Toast.makeText(MakeActivity.this, "Xóa không thành công! " + id_made, Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder ask = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("XÓA ĐÁP ÁN NÀY?")
                .setMessage("Thực sự xóa đáp án của mã đề [" + myMaDe
                        + "] thuộc bài thi tên [" + thisBaiThi.getTen() + "] hay không?")
                .setPositiveButton("Giữ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (databaseAction.delete(Integer.parseInt(id_made)) != -1) {
                            // Create for return RESULT
                            Intent data = new Intent();
                            String myData = Base64.encodeToString(dapAn.getID().getBytes(), Base64.DEFAULT) + "@@" +
                                    Base64.encodeToString(dapAn.getMaBaiThi().getBytes(), Base64.DEFAULT) + "@@" +
                                    Base64.encodeToString(dapAn.getMaDe().getBytes(), Base64.DEFAULT) + "@@" +
                                    Base64.encodeToString(dapAn.getDapAn().getBytes(), Base64.DEFAULT) + "@@" +
                                    Base64.encodeToString("true".getBytes(), Base64.DEFAULT);
                            data.setData(Uri.parse(myData));
                            setResult(DanhSachDapAn.DELETED_CODE, data);
                            // end
                            Toast.makeText(MakeActivity.this, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else
                            Toast.makeText(MakeActivity.this, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
        ask.create();
        ask.show();
    }

    void CheckAndSave(DapAn dapAn) {
        DapAn_Adapter dapAn_adapter = (DapAn_Adapter) recyclerView.getAdapter();
        dapAn_adapter.getDsDapAn();
        dapAn_adapter.CheckForSave();
        if (dapAn_adapter.isOK()) {
            int lastID = Integer.parseInt(dapAn.getID());
            databaseAction.SuaDapAn(lastID,
                    Integer.parseInt(thisBaiThi.getId()),
                    myMaDe, dapAn_adapter.getDsDapAn());
            Toast.makeText(myContext, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
            // Create for return RESULT
            Intent data = new Intent();
            String myData = Base64.encodeToString(Integer.toString(lastID).toString().getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString(thisBaiThi.getId().getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString(myMaDe.getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString(dapAn_adapter.getDsDapAn().getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString("false".getBytes(), Base64.DEFAULT);
            data.setData(Uri.parse(myData));
            setResult(DanhSachDapAn.DELETED_CODE, data);
            // end
            onBackPressed();
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MakeActivity.this);
            builder.setTitle("HÃY KIỂM TRA LẠI")
                    .setMessage("Hiện tại vẫn còn một số câu chưa có đáp án! Chúng đã được đánh dấu màu đỏ, vui lòng " +
                            "hoàn thiện chúng trước khi lưu.")
                    .setPositiveButton("Xem lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            builder.create();
            builder.show();
        }
    }

    void CheckAndSave() {
        DapAn_Adapter dapAn_adapter = (DapAn_Adapter) recyclerView.getAdapter();
        dapAn_adapter.CheckForSave();
        if (dapAn_adapter.isOK()) {

            int lastID = databaseAction.ThemDapAn(
                    Integer.parseInt(thisBaiThi.getId()),
                    myMaDe, dapAn_adapter.getDsDapAn());
            Toast.makeText(myContext, "Đã lưu!", Toast.LENGTH_SHORT).show();
            // Create for return RESULT
            Intent data = new Intent();
            String myData = Base64.encodeToString(Integer.toString(lastID).toString().getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString(thisBaiThi.getId().getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString(myMaDe.getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString(dapAn_adapter.getDsDapAn().getBytes(), Base64.DEFAULT) + "@@" +
                    Base64.encodeToString("false".getBytes(), Base64.DEFAULT);
            data.setData(Uri.parse(myData));
            setResult(DanhSachDapAn.RESULT_CODE, data);
            // end
            onBackPressed();
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MakeActivity.this);
            builder.setTitle("HÃY KIỂM TRA LẠI")
                    .setMessage("Hiện tại vẫn còn một số câu chưa có đáp án! Chúng đã được đánh dấu màu đỏ, vui lòng " +
                            "hoàn thiện chúng trước khi lưu.")
                    .setPositiveButton("Xem lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            builder.create();
            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.hand_da, menu);
        if (isReview && !isMaybe)
            menu.findItem(R.id.save_made).setIcon(R.drawable.ic_deleteall);
        else
            menu.findItem(R.id.save_made).setIcon(R.drawable.ic_save);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        this.myContext = this;
        mauTraLoiDatabase = new MauTraLoiDatabase(getBaseContext());
        databaseAction = new DapAnDatabase(myContext);
        // Lấy biến cờ Review được truyền sang
        isReview = getIntent().getBooleanExtra("Review", false);
        isMaybe = getIntent().getBooleanExtra("Maybe", false);
        ArrayList<Integer> dsCautl = new ArrayList();
        if (isReview) {
            id_made = getIntent().getStringExtra("ID_MaDe");
            if (Integer.parseInt(id_made) == -1) {
                Toast.makeText(myContext, "Xem lại lỗi!", Toast.LENGTH_SHORT).show();
                onBackPressed();
                finish();
                return;
            } else {
                dapAn = databaseAction.layDapAn(Integer.parseInt(id_made));
                dsCautl = dapAn.getdsDapAn();
            }
        }
        // Lấy mã đề được truyền sang
        myMaDe = getIntent().getStringExtra("MaDe");
        if (myMaDe == null || myMaDe.isEmpty())
            myMaDe = "---";
        // Thao tác với toolbar
        mytoolbar = (Toolbar) findViewById(R.id.toolbar_make);
        mytoolbar.setTitle("Mã đề " + myMaDe);
        setSupportActionBar(mytoolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        // Thực hiện lấy thông số của bài thi hiện hành!
        Bundle tempExtras = getIntent().getExtras();    // Lấy bộ tham số
        if (tempExtras != null)   // Nếu nó không rỗng
        {
            thisBaiThi = (BaiThi) getIntent().getSerializableExtra("BaiThi"); // Thì lấy tham số với key đó và ép sang kiểu bài thi
        }

        List<DapAnIn> hand = new ArrayList(); // Khởi tạo danh sách các câu
        for (int i = 0; i < mauTraLoiDatabase.LayIdMau((int) thisBaiThi.getLoaiDe()).getSoCauTraLoi(); i++)  // Chạy từ đầu đến số câu của mẫu giấy
        {
            DapAnIn _da = new DapAnIn(Integer.toString(i + 1), "A", "B", "C", "D");
            if (isReview && dsCautl.get(i) > 0 && dsCautl.get(i) < 5)
                _da.setSelect(dsCautl.get(i));
            hand.add(i, _da);
        }

        // Các thao tác với RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.makebyhand); // Tìm nó
        recyclerView.setAdapter(new DapAn_Adapter(hand, myContext, thisBaiThi));    // Đặt Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(myContext));      // Đặt kiểu dàn sếp các phần tử
        // Phần hiệu ứng màu mè
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        // Phần thêm bắt sự kiện
        recyclerView.addOnItemTouchListener(new DapAn_ItemTouchListener(myContext, recyclerView, new rvClickListener()));
    }
}
