package com.vothanhhien.automarkmobile.activities.BaiThi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


//import com.ajts.androidmads.library.SQLiteToExcel;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.DapAn.DanhSachDapAn;
import com.vothanhhien.automarkmobile.activities.Main.MainActivity;
import com.vothanhhien.automarkmobile.activities.PhieuTraLoi.DsPhieuTraLoi;
import com.vothanhhien.automarkmobile.sqlite.MauTraLoiDatabase;
import com.vothanhhien.automarkmobile.sqlite.PhieuTraLoiDatabase;
import com.vothanhhien.automarkmobile.activities.Main.ChamDiemActivity;
import com.vothanhhien.automarkmobile.models.BaiThi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class BaiThiAdapter extends ArrayAdapter<BaiThi> implements View.OnClickListener {
    private ArrayList<BaiThi> dsBaiThi;
    private ImageView LoaiGiayThi;
    private TextView TenKyThi;
    private TextView NgayTao;
    private Context mContext;
    private BoomMenuButton bmb;
    private LinearLayout NearSoCau;
    private MauTraLoiDatabase mauTraLoiDatabase;
    public BaiThiAdapter(ArrayList<BaiThi> dsBaiThi, Context context) {
        super(context, R.layout.row_item, dsBaiThi);
        this.dsBaiThi = dsBaiThi;
        this.mContext = context;
        this.mauTraLoiDatabase = new MauTraLoiDatabase(context);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // lấy bài thi tại vị trí position
        final BaiThi baiThi = dsBaiThi.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.row_item, parent, false);
        LoaiGiayThi = convertView.findViewById(R.id.loai_giay_thi);
        TenKyThi =  convertView.findViewById(R.id.ten_ky_thi);
        NgayTao = convertView.findViewById(R.id.ngay_tao);

        if (mauTraLoiDatabase.LayIdMau((int) baiThi.getLoaiDe()).getSoCauTraLoi() == 50)
            LoaiGiayThi.setImageResource(R.drawable.nam_muoi);
        else if (mauTraLoiDatabase.LayIdMau((int) baiThi.getLoaiDe()).getSoCauTraLoi() == 60)
            LoaiGiayThi.setImageResource(R.drawable.sau_muoi);
        else if (mauTraLoiDatabase.LayIdMau((int) baiThi.getLoaiDe()).getSoCauTraLoi() == 80)
            LoaiGiayThi.setImageResource(R.drawable.tam_muoi);

        if (baiThi.getTen().length() <= 20)
            TenKyThi.setText(baiThi.getTen());
        else
            TenKyThi.setText(baiThi.getTen().substring(0,20) + "...");

        DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NgayTao.setText(myDateFormat.format(baiThi.getNgayTao()));

        //region Tuỳ chọn khi nhấn vào nút số câu
        NearSoCau =  convertView.findViewById(R.id.NearSoCau);
        NearSoCau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        bmb = (BoomMenuButton) convertView.findViewById(R.id.bmb);
        bmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bmb.isBoomed()) {
                }
            }
        });
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.Vertical);
        bmb.setCancelable(true);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder;
            switch (i) {
                case 0:
                    builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.dap_an)
                            .normalColorRes(R.color.colorPrimaryDark)
                            .normalText("Đáp án")
                            .subNormalText("Thêm/Sửa đáp án cho bài thi!")
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    Intent dsDapAn = new Intent(mContext, DanhSachDapAn.class);
                                    dsDapAn.putExtra("BaiThi",baiThi);
                                    mContext.startActivity(dsDapAn);
                                }
                            });
                    break;
                case 1:
                    builder = new HamButton.Builder()
                            .normalColorRes(R.color.colorPrimaryDark)
                            .normalImageRes(R.drawable.cham_bai)
                            .normalText("Chấm bài")
                            .subNormalText("Thực hiện thao tác chấm thi!")
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    Intent openCam = new Intent(mContext, ChamDiemActivity.class);
                                    openCam.putExtra("mode",1);
                                    openCam.putExtra("BaiThi", baiThi);
                                    mContext.startActivity(openCam);
                                }
                            });
                    break;
                case 2:
                    builder = new HamButton.Builder()
                            .normalColorRes(R.color.colorPrimaryDark)
                            .normalImageRes(R.drawable.xem_lai)
                            .normalText("Xem lại")
                            .subNormalText("Xem lại danh sách bài thi đã chấm!")
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    Intent dsBaiLam = new Intent(mContext, DsPhieuTraLoi.class)
                                            .putExtra("BaiThi", baiThi);
                                    mContext.startActivity(dsBaiLam);
                                }
                            });
                    break;
                case 3:
                    builder = new HamButton.Builder()
                            .normalColorRes(R.color.colorPrimaryDark)
                            .normalImageRes(R.drawable.thong_ke)
                            .normalText("Xuất excel")
                            .subNormalText("Xuất kết quả cho bài thi này!")
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    //create the sqLiteToExcel object
//                                    String directory_path = SC.CURR_DIR;
//                                    File file = new File(directory_path);
//                                    if (!file.exists()) {
//                                        file.mkdirs();
//                                    }

//                                    SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getContext(), BaiThiHelper.TABLE_BaiThi, SC.CURR_DIR);
//                                    sqliteToExcel.exportAllTables(baiThi.getTen() + ".xls", new SQLiteToExcel.ExportListener() {
//                                                @Override
//                                                public void onStart() {
//
//                                                }
//
//                                                @Override
//                                                public void onCompleted(String filePath) {
//
//                                                }
//
//                                                @Override
//                                                public void onError(Exception e) {
//
//                                                }
//                                            });
//                                    Intent thongKe = new Intent(mContext, ThongKeActivity.class)
//                                            .putExtra("BaiThi", baiThi);
//                                    mContext.startActivity(thongKe);
                                };
                            });
                    break;
                case 4:
                    builder = new HamButton.Builder()
                            .normalColorRes(R.color.colorPrimaryDark)
                            .normalImageRes(R.drawable.thong_tin)
                            .normalText("Thông tin")
                            .subNormalText("Thông tin của bài thi này!")
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    ThongTinBaiThi(baiThi);
                                }
                            });
                    break;
                case 5:
                    builder = new HamButton.Builder()
                            .normalColorRes(R.color.colorPrimaryDark)
                            .normalImageRes(R.drawable.dong)
                            .normalText("Đóng")
                            .subNormalText("Đóng danh sách lựa chọn!");
                    break;
                default:
                    builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.bapple)
                            .normalText("Xuất hiện lỗi!")
                            .subNormalText("Nút bị lỗi! Vui lòng không click!").listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                }
                            });
                    break;
            }
            builder.imagePadding(new Rect(15, 15, 15, 15));
            bmb.addBuilder(builder);

        }
        //endregion

        return convertView;
    }

    void ActionToDo(int Option_Number, BaiThi baiThi) {
        switch (Option_Number) {
            case 1:
                Intent openCam = new Intent(mContext, ChamDiemActivity.class);
                openCam.putExtra("BaiThi", baiThi);
                mContext.startActivity(openCam);
            default:
                Toast.makeText(getContext(), baiThi.getTen() + " đã được chọn! với hành động thứ " + Integer.toString(Option_Number), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {

    }
    //region Phương thức hiển thị khi thông tin bài thi được nhấn
    private void ThongTinBaiThi(final BaiThi baiThi)
    {
        // Định huớng nơi mà View sẽ được nạp vào, ở đây là context được truyền đến
        LayoutInflater inflater = LayoutInflater.from(mContext);
        // Gọi Lay out vừa được tạo như là một view trong giao diện hiện hành
        View ViewPorm = inflater.inflate(R.layout.thong_tin_bai_thi, null);
        // Tạo một hộp thoại android app
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        // Cho hộp thoại hiển thị khung nhìn là view vừa nãy
        builder.setView(ViewPorm);
        // Lấy các thành phần tham chiếu ra để chèn tham số hiển thị
        final TextView tt_Ten = (TextView) ViewPorm.findViewById(R.id.tt_ten_bai_thi);
        final TextView tt_TaoLuc = (TextView) ViewPorm.findViewById(R.id.tt_tao_luc);
        final TextView tt_LoaiGiay = (TextView) ViewPorm.findViewById(R.id.tt_loai_giay);
        final TextView tt_SoCau = (TextView) ViewPorm.findViewById(R.id.tt_so_cau);
        final TextView tt_SoBaiDaCham = (TextView) ViewPorm.findViewById(R.id.tt_so_bai_da_cham);
        // Thêm các tham trị vào để hiển thị
        tt_Ten.setText(baiThi.getTen());
        DateFormat myDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        tt_TaoLuc.setText(myDateFormat.format(baiThi.getNgayTao()).replace(":","h"));
        tt_LoaiGiay.setText(mauTraLoiDatabase.LayIdMau((int) baiThi.getLoaiDe()).getSoCauTraLoi() + " câu");
        tt_SoCau.setText(baiThi.getSoCau() + " câu");
        tt_SoBaiDaCham.setText(Integer.toString(
                new PhieuTraLoiDatabase(mContext).SoPhieuTraLoiTrongMotBaiThi(baiThi)
        ));
        // Cài đặt các nút và thuộc tính cho hộp thoại
        builder.setCancelable(false)
                .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "Đã đóng!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Sửa tên", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditContestName(baiThi);
                    }
                })
                .setNeutralButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder hoi = new AlertDialog.Builder(mContext);
                        hoi.setCancelable(false)
                                .setTitle("XÓA BÀI THI?")
                                .setMessage("Sau khi xác nhận xóa, bài thi này và các mục chấm sẽ bị xóa hoàn toàn khỏi hệ thống. Đồng thời chúng không thể được khôi phục!Bạn thực sự muốn xóa bài thi này?")
                                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(mContext, "Đã hủy tác vụ xóa!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (mContext instanceof MainActivity)
                                        {
                                            new BaiThiDatabase(mContext).XoaBaiThi(Integer.parseInt(baiThi.getId()));
                                            ((MainActivity)mContext).Update_DS_BaiThi();
                                            Toast.makeText(mContext, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        hoi.create();
                        hoi.show();

                    }
                });
        builder.create();
        builder.show();
    }
    //endregion

    void EditContestName(final BaiThi baiThi)
    {
        final BaiThi mybaiThi = baiThi;
        // get view
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.textbox, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(promptView);

        final EditText mTenBaiThi = (EditText) promptView.findViewById(R.id.editTextBox);
        mTenBaiThi.setText(baiThi.getTen());
        builder.setCancelable(false)
                .setTitle("SỬA TÊN BÀI THI")
                .setPositiveButton("Hoàn thành", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Lưu tên lại tại đây
                        dsBaiThi.remove(mybaiThi);
                        mybaiThi.setTen(mTenBaiThi.getText().toString());
                        BaiThiDatabase baiThiDatabase = new BaiThiDatabase(mContext);
                        baiThiDatabase.ThayTheBaiThi(mybaiThi);
                        dsBaiThi.add(baiThi);
                        notifyDataSetChanged();
                        Toast.makeText(mContext, "Đã thay đổi tên bài thi thành công!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Đóng lại
                        dialogInterface.cancel();
                    }
                });
        builder.create();
        builder.show();
    }
}
