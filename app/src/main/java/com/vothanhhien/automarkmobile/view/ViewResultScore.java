package com.vothanhhien.automarkmobile.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.NhapDapAn.Hand.MakeActivity;
import com.vothanhhien.automarkmobile.activities.PhieuTraLoi.DsPhieuTraLoi;
import com.vothanhhien.automarkmobile.activities.PhieuTraLoi.PhieuTraLoi;
import com.vothanhhien.automarkmobile.sqlite.PhieuTraLoiDatabase;
import com.vothanhhien.automarkmobile.activities.Setting.Setting;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.activities.BaiThi.BaiThiDatabase;
import com.vothanhhien.automarkmobile.sqlite.DapAnDatabase;

public class ViewResultScore extends AppCompatActivity {

    Toolbar mytoolbar;
    PhieuTraLoi myPTL;
    ImageView resultView;
    TextView ShowScore;
    boolean isReView = false;
    Setting mySetting;
    PhieuTraLoiDatabase phieuTraLoiDatabase;

    @Override
    public void onBackPressed() {
        if (!isReView) {
            AlertDialog.Builder mbox = new AlertDialog.Builder(ViewResultScore.this)
                    .setTitle("QUÝ THẦY/CÔ MUỐN HỦY?")
                    .setMessage("Hiện tại kết quả chấm của bài làm này chưa được lưu," +
                            "quý Thầy/Cô thực sự muốn hủy kết quả này?")
                    .setPositiveButton("Xem lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .setNegativeButton("Đúng vậy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ViewResultScore.super.onBackPressed();
                            finish();
                        }
                    });
            mbox.create();
            mbox.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //region Thao tác khi nhấn nút quay lại
        if (id == MakeActivity.BackButton) {
            onBackPressed();
            finish();
        }
        //endregion

        //region Thao tác khi nhấn nút xem thông tin bài làm
        else if (id == R.id.activity_view_result_score_info) {
            LayoutInflater layoutInflater = LayoutInflater.from(ViewResultScore.this);
            View promtView = layoutInflater.inflate(R.layout.bailam_info, null);
            AlertDialog.Builder infoBox = new AlertDialog.Builder(ViewResultScore.this)
                    .setView(promtView);
            // Lấy bài thi của nó
            BaiThi baiThi = new BaiThiDatabase(ViewResultScore.this).LayBaiThi(Integer.parseInt(myPTL.getMaBaiThi()));
            // Tìm kiếm và cài đặt địa chỉ các View
            final TextView Ten_LopSV_TXT = (TextView) promtView.findViewById(R.id.bli_nameOfStudent_txt);
            final ImageView Ten_LopSV_IMG = (ImageView) promtView.findViewById(R.id.bli_nameOfStudent_img);
            final TextView HienThiSBD = (TextView) promtView.findViewById(R.id.bli_sbd);
            final TextView HienThiTenBaiThi = (TextView) promtView.findViewById(R.id.bli_tenbaithi);
            final TextView HienThiSoCau = (TextView) promtView.findViewById(R.id.bli_socau);
            final TextView HienThiLoaiGiay = (TextView) promtView.findViewById(R.id.bli_loaigiay);
            final TextView HienThiMaDe = (TextView) promtView.findViewById(R.id.bli_made);
            final TextView HienThiSoCauDung = (TextView) promtView.findViewById(R.id.bli_socaudung);
            final TextView HienThiKetQua = (TextView) promtView.findViewById(R.id.bli_thanhdiem);
            // Thiết đặt nội dung cho các view
            Ten_LopSV_TXT.setVisibility(View.GONE);
            Ten_LopSV_IMG.setImageBitmap(myPTL.getImageOfName());
            HienThiSBD.setText(myPTL.getSBD());
            HienThiTenBaiThi.setText(baiThi.getTen());
            HienThiSoCau.setText(baiThi.getSoCau() + "");
            HienThiLoaiGiay.setText(baiThi.getLoaiGiay() + "");
            HienThiMaDe.setText(myPTL.getMaDe());
            HienThiSoCauDung.setText(myPTL.getSoCauDung() + "/" + myPTL.getSoCauCuaBaiThi());
            HienThiKetQua.setText(myPTL.getDiem() + "/" + myPTL.getDiem(PhieuTraLoi.THANGDIEMCHU_CODE) + "/" + myPTL.getDiem(PhieuTraLoi.THANGDIEMBON_CODE));
            // Thiết lập hộp thoại thông tin
            infoBox.setCancelable(false)
                    .setTitle("THÔNG TIN BÀI LÀM")
                    .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            infoBox.create();
            infoBox.show();
        }
        //endregion

        //region Thao tác khi nhấn nút lưu bài làm
        else if (id == R.id.activity_view_result_score_saveAns) {
            // Nếu không phải là trạng thái xem lại
            if (!isReView) {
                AlertDialog.Builder mbox = new AlertDialog.Builder(ViewResultScore.this)
                        .setTitle("LƯU BÀI LÀM?")
                        .setMessage("Quý Thầy/Cô xác nhận lưu bài làm này?")
                        .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                saveIt();
                            }
                        })
                        .setNegativeButton("Xem lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                mbox.create();
                mbox.show();
            }
            // Khi đó là trạng thái xem lại
            else {
                AlertDialog.Builder mbox = new AlertDialog.Builder(ViewResultScore.this)
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
                                Intent data = new Intent();
                                String resultOK = "0";
                                if (phieuTraLoiDatabase.XoaPhieuTraLoi(
                                        Integer.parseInt(myPTL.getID())) != -1) {
                                    Toast.makeText(ViewResultScore.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                                    resultOK = "1";
                                    data.setData(Uri.parse(resultOK));
                                    setResult(DsPhieuTraLoi.DELETE_CODE, data);
                                    onBackPressed();
                                    finish();
                                } else {
                                    Toast.makeText(ViewResultScore.this, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
                                    dialogInterface.cancel();
                                    return;
                                }
                            }
                        });
                mbox.create();
                mbox.show();
            }
        }
        //endregion
        else
            Toast.makeText(this, "Lựa chọn không tồn tại, vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.view_result_score_menu, menu);
        if (getIntent().getBooleanExtra("ThongKe",false) == true)
            menu.findItem(R.id.activity_view_result_score_saveAns).setVisible(false);
        else if (isReView)
            menu.findItem(R.id.activity_view_result_score_saveAns).setIcon(R.drawable.ic_deleteall);
        else
            menu.findItem(R.id.activity_view_result_score_saveAns).setIcon(R.drawable.ic_save);
        return true;
    }

    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result_score);
        mySetting = new Setting(ViewResultScore.this);
        isReView = getIntent().getBooleanExtra("isReview", false);
        //region Thiết lập thanh toolbar cho activity
        mytoolbar = (Toolbar) findViewById(R.id.activity_view_result_score_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        phieuTraLoiDatabase = new PhieuTraLoiDatabase(ViewResultScore.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        //endregion

        resultView = (ImageView) findViewById(R.id.activity_view_result_score_imageView);
        resultView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                ShowScorePosition();
            }
        });
        ShowScore = (TextView) findViewById(R.id.activity_view_result_score_showScore);
        //region Phần kiểm tra tham biến
        Bundle myBundle = getIntent().getExtras();
        if (myBundle != null) {
            myPTL = (PhieuTraLoi) getIntent().getSerializableExtra("PhieuTraLoi");
            myPTL.setTempContext(ViewResultScore.this);
            mytoolbar.setTitle("SBD: " + myPTL.getSBD());
            resultView.setImageBitmap(myPTL.getImageOfPTL());
            ShowScore.setText(Float.toString((float) Math.round(myPTL.getDiem() * 100) / 10));
            if (!isReView) {
                //region Khi mã đề không nhận diện được
                if (myPTL.getMaDe().contains("-")) {
                    android.app.AlertDialog.Builder xmbox = new android.app.AlertDialog.Builder(ViewResultScore.this)
                            .setTitle("VẤN ĐỀ NHẬN DIỆN")
                            .setMessage("Hiện không nhận diện rõ mã đề, mong quý Thầy/Cô vui lòng chấm lại hoặc chấm thủ công để đảm bảo!\n\n" +
                                    "MẸO: Nghiêng điện thoại từ 30-45 độ để giảm thiểu phản quang của ánh sáng.")
                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    onBackPressed();
                                    finish();
                                    return;
                                }
                            });

                    xmbox.create();
                    xmbox.show();
                } else {
                    if (new DapAnDatabase(ViewResultScore.this).LayIdMaDe(
                            Integer.parseInt(myPTL.getMaDe()),
                            Integer.parseInt(myPTL.getMaBaiThi())) == -1) {
                        AlertDialog.Builder mbox = new AlertDialog.Builder(ViewResultScore.this)
                                .setTitle("KHÔNG CÓ ĐÁP ÁN")
                                .setMessage("Không tìm thấy đáp án của mã đề [" + myPTL.getMaDe() + "]. Có thể làm làm này bị lẫn lộn," +
                                        "quý Thầy/Cô vui lòng kiểm tra lại!")
                                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        onBackPressed();
                                        finish();
                                        return;
                                    }
                                });
                        mbox.create();
                        mbox.show();
                    } else // Mọi thứ đều ổn ^^
                    {
                        if (mySetting.autoScan) {
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(mySetting.timeDelay);
                                    } catch (InterruptedException e) {
                                        Log.e("ERROR", "Lỗi phương thức tạm ngủ ở lớp ViewResultScore.java!");
                                    }
                                    // sau khi ngủ xong -> Nhấn nút save và tiếp tục
                                    saveIt();
                                }
                            }.start();
                        }
                    }
                }
                //endregion
            } else {
                //
            }
        } else {
            mytoolbar.setTitle("SBD: ------");
            AlertDialog.Builder mbox = new AlertDialog.Builder(ViewResultScore.this)
                    .setTitle("GẶP VẤN ĐỀ")
                    .setMessage("Trong quá trình chấm, máy đã không nhận diện dược một số chi tiết. Quý thầy cô vui lòng chấm lại hoặc chấm bằng tay để đảm bảo cho HS/SV")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onBackPressed();
                            finish();
                            return;
                        }
                    });
            mbox.create();
            mbox.show();
        }
        ShowScorePosition();
        //endregion
    }

    void ShowScorePosition() {
        RelativeLayout.LayoutParams my = (RelativeLayout.LayoutParams) ShowScore.getLayoutParams();
        my.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int oldTop = my.topMargin;
        int oldRight = my.rightMargin;
        my.topMargin = (int) ((getWindowManager().getDefaultDisplay().getHeight() - Math.round(Math.sqrt((resultView.getHeight() * resultView.getWidth())))) / 2.4) + 5;
        if (my.topMargin < 0 || my.topMargin > getWindowManager().getDefaultDisplay().getHeight() / 8) {
            my.topMargin = oldTop;
        }
        my.rightMargin = (int) ((Math.round(Math.sqrt((resultView.getHeight() * resultView.getWidth()))) - getWindowManager().getDefaultDisplay().getWidth())) + 7;
        if (my.rightMargin < 0 || my.rightMargin > getWindowManager().getDefaultDisplay().getWidth() / 5) {
            my.rightMargin = oldRight;
        }
        ShowScore.setLayoutParams(my);
        ShowScore.bringToFront();
    }

    void saveIt() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (phieuTraLoiDatabase.ThemPhieuTL(myPTL) != -1) {
                    Toast.makeText(ViewResultScore.this, "Đã lưu! " + (mySetting.autoScan ? " Tự động tiếp tục..." : ""), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewResultScore.this, "Chưa lưu. " + (mySetting.autoScan ? " Tự động tiếp tục..." : "Vui lòng thử lại!"), Toast.LENGTH_SHORT).show();
                }
                if (mySetting.autoScan)
                {
                    onBackPressed();
                    finish();
                }
            }
        });
    }
}
