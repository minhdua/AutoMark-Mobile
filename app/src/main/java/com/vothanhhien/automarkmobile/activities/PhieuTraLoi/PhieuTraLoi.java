package com.vothanhhien.automarkmobile.activities.PhieuTraLoi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.vothanhhien.automarkmobile.activities.ScoreRank;

import java.io.Serializable;


public class PhieuTraLoi implements Serializable {
    public final static int THANGDIEMCHU_CODE = 0x011;
    public final static int THANGDIEMMUOI_CODE = 0x010;
    public final static int THANGDIEMBON_CODE = 0x004;
    // Trong CSDL
    private String ID;
    private String MaBaiThi; // Dùng để xác định xem bài làm này thuộc bài thi mục nào, lấy ID của BàiThi
    // Phần hiển hị bên ngoài
    private String SBD;
    private byte[] Name_image;
    private float Diem;
    // Phần chi tiết cụ thể
    private String MaDe;
    private String DS_CauTraLoi;
    private int SoCauDung;
    private int SoCauCuaBaiThi;
    private byte[] PTL_image;
    private Context tempContext;

    public void setTempContext(Context tempContext) {
        this.tempContext = tempContext;
    }

    // Các constructor
    public PhieuTraLoi() // Nếu chỉ khai báo tạm và chưa biết làm gì
    {
    }

    // Dành cho việc nhập dữ liệu hoàn toàn từ chuỗi
    public PhieuTraLoi(String ID, String MaBaiThi, String SBD, byte[] Name_image, String Diem, String MaDe, String DS_CauTraLoi, String SoCauDung, String SoCauCuaBaiThi, byte[] PTL_image) {
        this.ID = ID;
        this.MaBaiThi = MaBaiThi;
        this.SBD = SBD;
        this.Name_image = Name_image;
        this.Diem = Float.parseFloat(Diem);
        this.MaDe = MaDe;
        this.DS_CauTraLoi = DS_CauTraLoi;
        this.SoCauDung = Integer.parseInt(SoCauDung);
        this.SoCauCuaBaiThi = Integer.parseInt(SoCauCuaBaiThi);
        this.PTL_image = PTL_image;
    }

    // Dành cho việc nhập dữ liệu chuẩn
    public PhieuTraLoi(String ID, String MaBaiThi, String SBD, byte[] Name_image, float Diem, String MaDe, String DS_CauTraLoi, int SoCauDung, int SoCauCuaBaiThi, byte[] PTL_image) {
        this.ID = ID;
        this.MaBaiThi = MaBaiThi;
        this.SBD = SBD;
        this.Name_image = Name_image;
        this.Diem = Diem;
        this.MaDe = MaDe;
        this.DS_CauTraLoi = DS_CauTraLoi;
        this.SoCauDung = SoCauDung;
        this.SoCauCuaBaiThi = SoCauCuaBaiThi;
        this.PTL_image = PTL_image;
    }

    public String getDS_CauTraLoi() {
        return DS_CauTraLoi;
    }

    public void setDS_CauTraLoi(String DS_CauTraLoi) {
        this.DS_CauTraLoi = DS_CauTraLoi;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMaBaiThi() {
        return MaBaiThi;
    }

    public void setMaBaiThi(String maBaiThi) {
        this.MaBaiThi = maBaiThi;
    }

    public String getSBD() {
        return SBD;
    }

    public void setSBD(String SBD) {
        this.SBD = SBD;
    }

    public byte[] getName_image() {
        return Name_image;
    }

    public void setName_image(byte[] Name_image) {
        this.Name_image = Name_image;
    }

    public int getRankCode()
    {
        if (Diem >= 9.0 && Diem <= 10)
            return 0;
        else if (Diem >= 8 && Diem < 9)
            return 1;
        else if (Diem >= 7 && Diem < 8)
            return 2;
        else if (Diem >= 6 && Diem < 7)
            return 3;
        else if (Diem >= 5 && Diem < 6)
            return 4;
        else if (Diem >= 4 && Diem < 5)
            return 5;
        else
            return 6;
    }

    // Mặc định get điểm là hệ 10
    public float getDiem() {
        return Diem;
    }

    // Khi truyền tham số là chữ vào thì nhận thang điểm chữ
    public String getDiem(int TYPE_CODE)
    {
        return new ScoreRank(tempContext).getDiem(TYPE_CODE, this.Diem);
    }

    public String getXepLoai(int TYPE_CODE)
    {
        return new ScoreRank(tempContext).getXepLoai(TYPE_CODE, this.Diem);
    }

    public void setDiem(float diem) {
        this.Diem = diem;
    }

    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(String maDe) {
        this.MaDe = maDe;
    }

    public int getSoCauDung() {
        return SoCauDung;
    }

    public void setSoCauDung(int soCauDung) {
        SoCauDung = soCauDung;
    }

    public int getSoCauCuaBaiThi() {
        return SoCauCuaBaiThi;
    }

    public void setSoCauCuaBaiThi(int soCauCuaBaiThi) {
        SoCauCuaBaiThi = soCauCuaBaiThi;
    }

    public byte[] getPTL_image() {
        return PTL_image;
    }

    public void setPTL_image(byte[] PTL_image) {
        this.PTL_image = PTL_image;
    }

    public Bitmap getImageOfPTL() {
        return BitmapFactory.decodeByteArray(PTL_image, 0, PTL_image.length);
    }

    public Bitmap getImageOfName() {
        return BitmapFactory.decodeByteArray(Name_image, 0, Name_image.length);
    }
}
