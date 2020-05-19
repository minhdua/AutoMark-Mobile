package com.vothanhhien.automarkmobile.activities.DapAn;

import java.util.ArrayList;

public class DapAn {
    // Trong CSDL
    private String ID;
    private String MaBaiThi; // Dùng để xác định xem bài này thuộc bài thi mục nào?
    // Phần hiển thị bên ngoài
    private String MaDe;
    private String DapAn;

    // Các phương thức khời tạo
    public DapAn(int id, int idbaithi, String made, String dapan)
    {
        this.ID = Integer.toString(id);
        this.MaBaiThi = Integer.toString(idbaithi);
        this.MaDe = made;
        this.DapAn = dapan;
    }
    public DapAn(String id, String idbaithi, String made, String dapan)
    {
        this.ID = id;
        this.MaBaiThi = idbaithi;
        this.MaDe = made;
        this.DapAn = dapan;
    }
    public DapAn() {}

    // Các phương thức get/set dữ liệu
    public String getMaBaiThi() {
        return MaBaiThi;
    }

    public String getID() {
        return ID;
    }

    public String getMaDe() {
        return MaDe;
    }

    public String getDapAn() {
        return DapAn;
    }

    public void setMaBaiThi(String maBaiThi) {
        MaBaiThi = maBaiThi;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMaDe(String maDe) {
        MaDe = maDe;
    }

    public void setDapAn(String dapAn) {
        DapAn = dapAn;
    }
    // Phương thức lấy đáp án theo từng câu 1
    public ArrayList<Integer> getdsDapAn()
    {
        ArrayList<Integer> ds = new ArrayList();
        for (char c : this.DapAn.toCharArray())
            ds.add(c - 48);
        return ds;
    }
    public int[] ArrayDapAn()
    {
        int[] ds = new int[DapAn.length()];
        char[] dsda = DapAn.toCharArray();
        for (int i =0; i< DapAn.length(); i++)
            ds[i] = dsda[i] - 48;
        return ds;
    }
}
