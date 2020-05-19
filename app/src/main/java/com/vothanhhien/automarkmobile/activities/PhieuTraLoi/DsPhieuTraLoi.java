package com.vothanhhien.automarkmobile.activities.PhieuTraLoi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.NhapDapAn.Hand.MakeActivity;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.sqlite.PhieuTraLoiDatabase;
import com.vothanhhien.automarkmobile.view.ViewResultScore;

import java.util.ArrayList;

public class DsPhieuTraLoi extends AppCompatActivity {
    Context myContext;
    BaiThi myBaiThi;
    Toolbar toolbar;
    ListView listView;
    ProgressBar progressBar;
    TextView textView;
    ArrayList<PhieuTraLoi> dsPhieuTraLoi;
    PhieuTraLoiAdapter phieuTraLoiAdapter;
    PhieuTraLoiDatabase phieuTraLoiDatabase;
    PhieuTraLoi currentPTL;
    public final static int DELETE_CODE = 0x98;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_phieu_tra_loi);
        //region Khu vực đặt view
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        toolbar = (Toolbar) findViewById(R.id.ds_phieu_tra_loi_toolbar);
        listView = (ListView) findViewById(R.id.ds_phieu_tra_loi_listview);
        progressBar = (ProgressBar) findViewById(R.id.ds_phieu_tra_loi_progressbar);
        textView = (TextView) findViewById(R.id.ds_phieu_tra_loi_thongbao);
        //endregion

        //region Khu vực setup
        myContext = DsPhieuTraLoi.this;
        if (getIntent().getExtras() != null) {
            myBaiThi = (BaiThi) getIntent().getSerializableExtra("BaiThi");
            dsPhieuTraLoi = new ArrayList();
            phieuTraLoiDatabase = new PhieuTraLoiDatabase(myContext);
            setUpProgressBarShow(true);
            setUpTextViewShow(true);
            setUpToolbar();
            setUpListView();
        } else {
            Toast.makeText(myContext, "Không xác nhận được dữ liệu!", Toast.LENGTH_SHORT).show();
            onBackPressed();
            finish();
        }
        //endregion
    }

    void setUpToolbar() {
        toolbar.setTitle("Danh sách bài làm");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void setUpListView() {
        new Thread() {
            @Override
            public void run() {
                dsPhieuTraLoi = phieuTraLoiDatabase.TatCaPTL(myBaiThi);
                phieuTraLoiAdapter = new PhieuTraLoiAdapter(dsPhieuTraLoi, myContext);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(phieuTraLoiAdapter);
                        if (listView.getCount() > 0)
                            setUpTextViewShow(false);
                        else
                            setUpTextViewShow(true);
                        WhenListViewChanged();
                        WhenLongPressToItem();
                        WhenClickToItem();
                    }
                });
                setUpProgressBarShow(false);
            }
        }.start();

    }

    void setUpProgressBarShow(boolean show) {
        //progressBar.bringToFront();
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    void setUpTextViewShow(boolean show) {
        textView.bringToFront();
        if (show)
            textView.setVisibility(View.VISIBLE);
        else
            textView.setVisibility(View.INVISIBLE);
    }

    void WhenListViewChanged() {
        phieuTraLoiAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                if (listView.getCount() <= 0)
                    setUpTextViewShow(true);
                else
                    setUpTextViewShow(false);
            }
        });
    }

    void WhenClickToItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentPTL = dsPhieuTraLoi.get(i);
                Intent ReView = new Intent(myContext, ViewResultScore.class);
                ReView.putExtra("PhieuTraLoi", currentPTL);
                ReView.putExtra("isReview", true);
                startActivityForResult(ReView, DELETE_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ds_phieu_tra_loi_delete_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.dsptl_del_all) {
            AlertDialog.Builder builder = new AlertDialog.Builder(myContext)
                    .setTitle("XÓA TẤT CẢ?")
                    .setMessage("Thầy/Cô thực sự muốn xóa tất cả bài làm đã chấm của bài thi này?")
                    .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            phieuTraLoiDatabase.XoaTatCaPhieuTraLoi(Integer.parseInt(myBaiThi.getId()));
                            phieuTraLoiAdapter.clear();
                            phieuTraLoiAdapter.notifyDataSetChanged();
                            Toast.makeText(myContext, "Hoàn Thành!", Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.create();
            builder.show();
        } else if (id == MakeActivity.BackButton) {
            onBackPressed();
            finish();
        } else {
            Toast.makeText(myContext, "Lựa chọn không phù hợp. Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    void WhenLongPressToItem() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                PopupMenu myPopUpMenu = new PopupMenu(myContext, view);
                myPopUpMenu.getMenuInflater().inflate(R.menu.ds_phieu_tra_loi_long_press, myPopUpMenu.getMenu());
                myPopUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.ds_phieu_tra_loi_long_press_delete) {
                            AlertDialog.Builder mbox = new AlertDialog.Builder(myContext)
                                    .setTitle("XÁC NHẬN XÓA?")
                                    .setMessage("Quý thầy/cô thực sự muốn xóa bài làm này?")
                                    .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    })
                                    .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (phieuTraLoiDatabase.XoaPhieuTraLoi(
                                                    Integer.parseInt(dsPhieuTraLoi.get(pos).getID())) != -1) {
                                                Toast.makeText(DsPhieuTraLoi.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                                                phieuTraLoiAdapter.remove(dsPhieuTraLoi.get(pos));
                                            } else {
                                                Toast.makeText(DsPhieuTraLoi.this, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            mbox.create();
                            mbox.show();
                            return true;
                        } else {
                            Toast.makeText(myContext, "Lựa chọn không tồn tại.", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                });
                myPopUpMenu.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == resultCode && requestCode == DELETE_CODE) {
            String recivedData = data.getData().toString().replace("\n", "").trim();
            int OK = Integer.parseInt(recivedData);
            if (OK == 1)
                phieuTraLoiAdapter.remove(currentPTL);
            else
                return;
            phieuTraLoiAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
