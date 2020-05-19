package com.vothanhhien.automarkmobile.models;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BaiThi implements Serializable {
    private String id;
    private String Ten;
    private Date NgayTao;
    private int SoCau;
    private int HeDiem;
    private long LoaiDe;

    public BaiThi(String id, String Ten, Date NgayTao,long LoaiDe, int SoCau, int HeDiem) {
        this.id = id;
        this.Ten = Ten;
        Calendar mC = Calendar.getInstance();
        mC.setTime(NgayTao);
        mC.add(Calendar.MONTH,1);
        String dateTimeStr = mC.get(Calendar.DAY_OF_MONTH) + "/" +
                mC.get(Calendar.MONTH) + "/" + mC.get(Calendar.YEAR) + " " +
                mC.get(Calendar.HOUR_OF_DAY) + ":" +
                mC.get(Calendar.MINUTE) + ":00";
        try {
            this.NgayTao = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateTimeStr);
        } catch (ParseException e) {
            Log.e("BaiThi.java", "Không chuyển từ string sang date được! " + e.getMessage());
        }
        this.SoCau = SoCau;
        this.HeDiem = HeDiem;
        this.LoaiDe = LoaiDe;
    }

    public BaiThi(String id, String Ten, String NgayTao, long LoaiDe, String SoCau,String HeDiem) {
        this.id = id;
        this.Ten = Ten;
        try {
            this.NgayTao = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(NgayTao);
        } catch (ParseException e) {
            this.NgayTao = new Date();
            Log.e("BaiThi.java", "Không chuyển từ string sang date được! " + e.getMessage());
        }
        this.SoCau = Integer.parseInt(SoCau);
        this.HeDiem = Integer.parseInt(HeDiem);
        this.LoaiDe = LoaiDe;
    }

    public BaiThi() {
    }

    public long getLoaiDe() {
        return LoaiDe;
    }

    public void setLoaiDe(long loaiDe) {
        LoaiDe = loaiDe;
    }

    public String getId() {
        return id;
    }

    public String getTen() {
        return Ten;
    }

    public Date getNgayTao() {
        return NgayTao;
    }


    public int getSoCau() {
        return SoCau;
    }

    public int getHeDiem() {
        return HeDiem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setNgayTao(Date ngayTao) {
        NgayTao = ngayTao;
    }

    public void setSoCau(int soCau) {
        SoCau = soCau;
    }

    public void setHeDiem(int heDiem) {
        HeDiem = heDiem;
    }

    public int getLoaiGiay() {
        switch ((int) LoaiDe){
            case 1: return 50;
            case 2: return 60;
            case 3: return 80;
            default: return 20;
        }
    }
}
