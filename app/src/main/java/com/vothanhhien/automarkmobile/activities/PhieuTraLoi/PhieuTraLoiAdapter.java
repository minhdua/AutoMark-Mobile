package com.vothanhhien.automarkmobile.activities.PhieuTraLoi;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vothanhhien.automarkmobile.R;

import java.util.ArrayList;


public class PhieuTraLoiAdapter extends ArrayAdapter<PhieuTraLoi> implements View.OnClickListener {

    ImageView hoten;
    TextView sbd;
    TextView diem;
    LinearLayout textInfoBox;
    TextView textHoTen;
    TextView textLop;
    Context myContext;
    private ArrayList<PhieuTraLoi> dsBaiLam;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        convertView = layoutInflater.inflate(R.layout.item_phieu_tra_loi, parent, false);
        hoten = (ImageView) convertView.findViewById(R.id.item_ptl_imageView);
        sbd  = (TextView) convertView.findViewById(R.id.item_ptl_sbd);
        diem = (TextView) convertView.findViewById(R.id.item_ptl_score);
        textInfoBox = (LinearLayout) convertView.findViewById(R.id.item_ptl_textInfoBox);
        textHoTen = (TextView) convertView.findViewById(R.id.item_ptl_hotenText);
        textLop = (TextView) convertView.findViewById(R.id.item_ptl_lopText);
        //region Set thông tin
        PhieuTraLoi myPTL = dsBaiLam.get(position);
        textInfoBox.setVisibility(View.GONE);
        hoten.setImageBitmap(myPTL.getImageOfName());
        sbd.setText(myPTL.getSBD());
        diem.setText(Float.toString(myPTL.getDiem()));
        //endregion

        // Change some thing
//        if (myContext instanceof ThongKeActivity)
//        {
//            hoten.getLayoutParams().width = 350;
//            hoten.getLayoutParams().height = 100;
//        }
        return convertView;
    }

    public PhieuTraLoiAdapter(ArrayList<PhieuTraLoi> dsBaiLam, Context myContext)
    {
        super(myContext, R.layout.item_phieu_tra_loi,dsBaiLam);
        this.dsBaiLam = dsBaiLam;
        this.myContext = myContext;
    }
    @Override
    public void onClick(View view) {

    }
}
