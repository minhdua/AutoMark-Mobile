package com.vothanhhien.automarkmobile.activities.NhapDapAn.MaDe;

public class MaDe {
    public String a;
    public String b;
    public String c;
    public String n;
    public int select;

    public MaDe(String n, String a, String b, String c)
    {
        this.n = n;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }
}
