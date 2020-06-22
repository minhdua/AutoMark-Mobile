package com.vothanhhien.automarkmobile.activities.Main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;
import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.BaiThi.DsBaiThiFrg;
import com.vothanhhien.automarkmobile.activities.MauPhieu.MauPhieuFragment;
import com.vothanhhien.automarkmobile.activities.Setting.Setting;
import com.vothanhhien.automarkmobile.constants.SC;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.models.KhungTraLoi;
import com.vothanhhien.automarkmobile.models.MauTraLoi;
import com.vothanhhien.automarkmobile.permission.SimplePermissions;
import com.vothanhhien.automarkmobile.activities.BaiThi.BaiThiDatabase;
import com.vothanhhien.automarkmobile.sqlite.KhungTraLoiDatabase;
import com.vothanhhien.automarkmobile.sqlite.MauTraLoiDatabase;
import com.vothanhhien.automarkmobile.utils.FileUtils;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{


    private String TAG = "MainActivity";
    private BaiThiDatabase duLieu;
    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DsBaiThiFrg DsBaiThi;
    private MauPhieuFragment MauPhieu;
    private List<MauTraLoi> mauTraLois;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        HoiQuyen();
        KiemTraVaTaoMau();
        viewerSQLite();
        duLieu = new BaiThiDatabase(getApplicationContext());
        rfaLayout = findViewById(R.id.activity_main_rfal);
        rfaBtn = findViewById(R.id.activity_main_rfab);
        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(getApplicationContext());
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Thêm bài thi")
                .setResId(R.drawable.addtest)
                .setIconNormalColor(0xff3aaaff)
                .setIconPressedColor(0xff1abc9c)
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Xóa tất cả")
                .setResId(R.drawable.delete_all)
                .setIconNormalColor(0xfff36159)
                .setIconPressedColor(0xffe67e22)
                .setWrapper(1)
        );
        rfaContent
                .setItems(items)
                .setIconShadowColor(0xff888888)
        ;
        rfabHelper = new RapidFloatingActionHelper(
                getApplicationContext(),
                rfaLayout,
                rfaBtn,
                rfaContent
        ).build();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        MauPhieu = new MauPhieuFragment();
        DsBaiThi = new DsBaiThiFrg();
        setFragment(DsBaiThi);
        toolbar.setTitle("Danh sách bài thi");
    }

    private void viewerSQLite() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    private void KiemTraVaTaoMau() {
        MauTraLoiDatabase mauTraLoiDatabase = new MauTraLoiDatabase(getApplicationContext());
        if (mauTraLoiDatabase.TatCaMauTraLoi().size()<3){
            mauTraLoiDatabase.deleteAll();
            mauTraLoiDatabase.ThemMauTraLoi(new MauTraLoi(1,"BW50",50,3));
            mauTraLoiDatabase.ThemMauTraLoi(new MauTraLoi(2,"BW60",60,3));
            mauTraLoiDatabase.ThemMauTraLoi(new MauTraLoi(3,"BW80",80,4));

            KhungTraLoiDatabase khungTraLoiDatabase = new KhungTraLoiDatabase(getApplicationContext());
            khungTraLoiDatabase.XoaTatCaKhungTraLoi();

            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(1,1,252,48,288,80,3,10));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(2,1,252,148,288,160,6,10));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(3,1,664,592,432,156,4,17));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(4,1,664,320,432,156,4,17));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(5,1,664,52,432,156,4,17));

            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(6,2,184,68,252,80,3,10));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(7,2,184,172,252,160,6,10));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(8,2,568,576,508,148,4,20));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(9,2,568,328,508,148,4,20));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(10,2,568,76,504,148,4,20));

            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(11,3,260,60,272,80,3,10));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(12,3,260,168,272,148,6,10));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(13,3,592,612,460,124,4,20));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(14,3,592,428,460,124,4,20));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(15,3,592,244,460,124,4,20));
            khungTraLoiDatabase.ThemKhungTL( new KhungTraLoi(16,3,592,60,460,124,4,20));
        }
    }

    private void HoiQuyen() {
         permHandler = new SimplePermissions(MainActivity.this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        });
        permHandler.grantPermissions();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    int previous_selected = R.id.nav_ds;
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ds && previous_selected != id) {
            rfaLayout.setVisibility(View.VISIBLE);
            previous_selected = id;
            DsBaiThi = new DsBaiThiFrg();
            setFragment(DsBaiThi);
            toolbar.setTitle("Danh sách bài thi");
        } else if (id == R.id.nav_giay && previous_selected != id) {
            previous_selected = id;
            rfaLayout.setVisibility(View.INVISIBLE);
            toolbar.setTitle("Mẫu phiếu");
            setFragment(MauPhieu);
        } else if (id == R.id.nav_hd && previous_selected != id) {
            previous_selected = id;
            rfaLayout.setVisibility(View.INVISIBLE);
            toolbar.setTitle("Hướng dẫn");
        } else if (id == R.id.nav_caidat && previous_selected != id) {
            Toast.makeText(this, "Cài đặt hiện chưa khả dụng", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share && previous_selected != id) {
            Toast.makeText(this, "Chia sẻ ứng dụng ngay!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_fb && previous_selected != id) {
            Toast.makeText(this, "Đang đến Facebook tác giả...", Toast.LENGTH_SHORT).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Setting.applicationAuthorFacebook));
            startActivity(browserIntent);
        } else if (id == R.id.nav_send && previous_selected != id) {
            Toast.makeText(this, "Mở email để gửi phản hồi!", Toast.LENGTH_SHORT).show();
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", Setting.applicationAuthorEmail, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Phản hồi về dự án phần mềm chấm thi trắc nghiệm trên điện thoại dùng hệ điều hành Android");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Nhập nội dung phản hồi của bạn vào đây");
            startActivity(Intent.createChooser(emailIntent, "Gửi phản hồi"));
        } else if (id == R.id.nav_exit && previous_selected != id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("THOÁT?");
            builder.setMessage("Thực sự muốn thoát ứng dụng ngay?");
            builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Đã hủy thao tác Thoát!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create();
            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permHandler.hasAllPermissions()) {
            Toast.makeText(MainActivity.this, "Các quyền đã được cấp", Toast.LENGTH_SHORT).show();
            onStorageGranted();
        }
        else {
            Toast.makeText(MainActivity.this, "Phải cấp đủ quyền ứng dụng mới có thể hoạt động!", Toast.LENGTH_LONG).show();
            exitApp();
        }
    }

    private void onStorageGranted() {
        FileUtils.checkMakeDirs(SC.STORAGE_PROJECT);
    }
    public void exitApp(){
        System.gc();
        finish();
    }
    public  void init(){
        btnSetOfOMR = findViewById(R.id.setofomr);
        buttonStorage = findViewById(R.id.buttonStorage);
    }
    private Button btnSetOfOMR,buttonStorage;
    SimplePermissions permHandler;

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {

    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        doBtn(position);

    }

    private void doBtn(int position) {
        if (position == 0) {
            // Dành cho  Thêm bài thi
            show();
        } else if (position == 1) {
            // Dành cho xóa tất cả
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("XÓA TẤT CẢ?");
            builder.setMessage("Việc này không thể hoàn tác! Thực sự muốn xóa tất cả các bài đã tạo?");
            builder.setPositiveButton("Vẫn xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    duLieu.XoaTatCa();
                    Toast.makeText(MainActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    Update_DS_BaiThi();
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Đã hủy tác vụ xóa!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create();
            builder.show();
        }
        rfabHelper.toggleContent();
    }

    private void show() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.them_bai_thi_dialog, null);
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText mTenBaiThi;
        final Spinner mLoaiGiayThi;
        final EditText mSoCau;
        mTenBaiThi =  promptView.findViewById(R.id.ten_bai_thi_moi);

        mLoaiGiayThi =  promptView.findViewById(R.id.ds_loai_giay);
        MauTraLoiDatabase mauTraLoi = new MauTraLoiDatabase(getBaseContext());
         mauTraLois = mauTraLoi.TatCaMauTraLoi();
        String[] loaigiay = new String[mauTraLois.size()];
        for(int i =0; i< mauTraLois.size();i++){
            loaigiay[i] = mauTraLois.get(i).getTenMau();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, loaigiay);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLoaiGiayThi.setAdapter(adapter);
//
        mSoCau = (EditText) promptView.findViewById(R.id.so_cau);
//
        // set up dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mTenBaiThi.length() < 1) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("TÊN BÀI THI RỖNG!")
                                    .setMessage("Phải có tên bài thi để phân biệt!")
                                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create();
                            builder.show();
                            return;
                        }
                        if (mSoCau.length() < 1) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("SỐ CÂU RỖNG!")
                                    .setMessage("Số câu của đề không thể là giá trị rỗng!")
                                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create();
                            builder.show();
                            return;
                        }
                        if (Integer.parseInt(mSoCau.getText().toString()) < 1) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("SỐ CÂU KHÔNG ĐÚNG!")
                                    .setMessage("Bài thi phải có ít nhất 1 câu!")
                                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create();
                            builder.show();
                            return;
                        }
                        int tongCauTraLoi =  mauTraLois.get((int) mLoaiGiayThi.getSelectedItemId()).getSoCauTraLoi();
                        if (Integer.parseInt(mSoCau.getText().toString()) >tongCauTraLoi) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("KHÔNG HỢP LỆ!")
                                    .setMessage("Số câu của đề không thể lớn hơn số câu giới hạn của giấy thi!")
                                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            builder.create();
                            builder.show();
                            return;
                        }
                        int MAX_ID = duLieu.MaxID();
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date now = new Date();
                        try {
                            Calendar mC = Calendar.getInstance();
                            mC.add(Calendar.MONTH, 1);
                            String dateTimeStr = mC.get(Calendar.DAY_OF_MONTH) + "/" +
                                    mC.get(Calendar.MONTH) + "/" + mC.get(Calendar.YEAR) + " " +
                                    mC.get(Calendar.HOUR_OF_DAY) + ":" +
                                    mC.get(Calendar.MINUTE) + ":00";
                            Log.d("MainActivity Time", dateTimeStr);
                            now = df.parse(dateTimeStr);
                        } catch (ParseException e) {
                            Log.e("MainActivity", "Không chuyển dạng ngày tháng được! " + e.getMessage());
                        }
                        BaiThi bt = new BaiThi(Integer.toString(MAX_ID + 1), mTenBaiThi.getText().toString(),
                                now,  mauTraLois.get(mLoaiGiayThi.getSelectedItemPosition()).getId(),
                                Integer.parseInt(mSoCau.getText().toString()), 10);
                        duLieu.ThemBaiThiMoi(bt);
                        Toast.makeText(MainActivity.this, "Đã thêm bài thi!", Toast.LENGTH_SHORT).show();
                        Update_DS_BaiThi();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialogBuilder.create();
        alertDialogBuilder.show();

    }
    public void Update_DS_BaiThi() {
        DsBaiThi = new DsBaiThiFrg();
        setFragment(DsBaiThi);
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrgShow, fragment);
        fragmentTransaction.commit();
    }

}
