package com.vothanhhien.automarkmobile.activities.NhapDapAn.Hand;

public class DapAnIn {
    public String n;
    public String a;
    public String b;
    public String c;
    public String d;
    int select;
    public DapAnIn(String n, String a, String b, String c, String d)
    {
        this.n = n;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public DapAnIn()
    {
        this.select = 0;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }

}
