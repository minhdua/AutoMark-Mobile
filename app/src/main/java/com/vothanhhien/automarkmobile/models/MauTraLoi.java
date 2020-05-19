package com.vothanhhien.automarkmobile.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MauTraLoi {
    private long id;
    private String tenMau;
    private int soCauTraLoi;
    private int soKhungTraLoi;

    public MauTraLoi(long id, String tenMau, int soCauTraLoi, int soKhungTraLoi) {
        this.id = id;
        this.tenMau = tenMau;
        this.soCauTraLoi = soCauTraLoi;
        this.soKhungTraLoi = soKhungTraLoi;
    }

    public MauTraLoi() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getSoCauTraLoi() {
        return soCauTraLoi;
    }

    public void setSoCauTraLoi(int soCauTraLoi) {
        this.soCauTraLoi = soCauTraLoi;
    }

    public long getSoKhungTraLoi() {
        return soKhungTraLoi;
    }

    public void setSoKhungTraLoi(int soKhungTraLoi) {
        this.soKhungTraLoi = soKhungTraLoi;
    }
}



