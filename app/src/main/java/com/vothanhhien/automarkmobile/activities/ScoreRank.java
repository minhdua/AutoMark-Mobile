package com.vothanhhien.automarkmobile.activities;

import android.content.Context;

import com.vothanhhien.automarkmobile.activities.PhieuTraLoi.PhieuTraLoi;
import com.vothanhhien.automarkmobile.activities.Setting.Setting;

public class ScoreRank {
    Context myContext;
    public ScoreRank(Context myContext)
    {
        this.myContext = myContext;
    }
    public String getXepLoai(int TYPE_CODE, float Diem) {
        boolean TOIGIAN = new Setting(myContext).simplify;
        if (TYPE_CODE == PhieuTraLoi.THANGDIEMMUOI_CODE) {
            if (Diem >= 9.0 && Diem <= 10)
                return "Xuất Sắc";
            else if (Diem >= 8 && Diem < 9)
                return "Giỏi";
            else if (Diem >= 7 && Diem < 8)
                return "Khá";
            else if (Diem >= 6 && Diem < 7)
                return "Trung Bình Khá";
            else if (Diem >= 5 && Diem < 6)
                return "Trung Bình";
            else if (Diem >= 4 && Diem < 5)
                return "Yếu";
            else
                return "Kém";
        } else if (TYPE_CODE == PhieuTraLoi.THANGDIEMCHU_CODE) {
            if (TOIGIAN) {
                if (Diem >= 8.0 && Diem <= 10)
                    return "Giỏi";
                else if (Diem >= 6.5 && Diem <= 7.9)
                    return "Khá";
                else if (Diem >= 5.0 && Diem <= 6.4)
                    return "Trung Bình";
                else if (Diem >= 3.5 && Diem <= 4.9)
                    return "Yếu";
            } else {
                if (Diem >= 8.5 && Diem <= 10)
                    return "Giỏi";
                else if (Diem >= 8.0 && Diem <= 8.4)
                    return "Khá Giỏi";
                else if (Diem >= 7.0 && Diem <= 7.9)
                    return "Khá";
                else if (Diem >= 6.5 && Diem <= 6.9)
                    return "Trung Bình Khá";
                else if (Diem >= 5.5 && Diem <= 6.4)
                    return "Trung Bình";
                else if (Diem >= 5.0 && Diem <= 5.4)
                    return "Trung Bình Yếu";
                else if (Diem >= 4.0 && Diem <= 4.9)
                    return "Yếu";
                else
                    return "Kém";
            }
        } else if (TYPE_CODE == PhieuTraLoi.THANGDIEMBON_CODE) {
            float DIEM_4 = Diem / (float) 2.5;
            if (DIEM_4 >= 3.6 && DIEM_4 <= 4.0)
                return "Xuất Sắc";
            else if (DIEM_4 >= 3.20 && DIEM_4 < 3.6)
                return "Giỏi";
            else if (DIEM_4 >= 2.50 && DIEM_4 < 3.20)
                return "Khá";
            else if (DIEM_4 >= 2.00 && DIEM_4 < 2.50)
                return "Trung bình";
            else
                return "Yếu";
        }
        return getXepLoai(PhieuTraLoi.THANGDIEMMUOI_CODE, Diem);
    }
    public String getDiem(int TYPE_CODE, float Diem) {
        boolean TOIGIAN = new Setting(myContext).simplify;
        if (TYPE_CODE == PhieuTraLoi.THANGDIEMCHU_CODE) {
            if (TOIGIAN) {
                if (Diem >= 8.0 && Diem <= 10)
                    return "A";
                else if (Diem >= 6.5 && Diem < 8.0)
                    return "B";
                else if (Diem >= 5.0 && Diem < 6.5)
                    return "C";
                else if (Diem >= 3.5 && Diem < 5.0)
                    return "D";
            } else {
                if (Diem >= 8.5 && Diem <= 10)
                    return "A";
                else if (Diem >= 8.0 && Diem < 8.5)
                    return "B+";
                else if (Diem >= 7.0 && Diem < 8.0)
                    return "B";
                else if (Diem >= 6.5 && Diem < 7.0)
                    return "C+";
                else if (Diem >= 5.5 && Diem < 6.5)
                    return "C";
                else if (Diem >= 5.0 && Diem < 5.5)
                    return "D+";
                else if (Diem >= 4.0 && Diem < 5.0)
                    return "D";
                else
                    return "F";
            }
        } else if (TYPE_CODE == PhieuTraLoi.THANGDIEMBON_CODE) {
            if (Diem >= 8.5 && Diem <= 10)
                return "4" + " (" + Diem / 2.5 + ")";
            else if (Diem >= 8.0 && Diem < 8.5)
                return "3.5" + " (" + Diem / 2.5 + ")";
            else if (Diem >= 7.0 && Diem < 8.0)
                return "3" + " (" + Diem / 2.5 + ")";
            else if (Diem >= 6.5 && Diem < 7.0)
                return "2.5" + " (" + Diem / 2.5 + ")";
            else if (Diem >= 5.5 && Diem < 6.5)
                return "2" + " (" + Diem / 2.5 + ")";
            else if (Diem >= 5.0 && Diem <= 5.4)
                return "1.5" + " (" + Diem / 2.5 + ")";
            else if (Diem >= 4.0 && Diem < 5.0)
                return "1" + " (" + Diem / 2.5 + ")";
            else
                return "0" + " (" + Diem / 2.5 + ")";
        }
        return Diem + "";
    }
}
