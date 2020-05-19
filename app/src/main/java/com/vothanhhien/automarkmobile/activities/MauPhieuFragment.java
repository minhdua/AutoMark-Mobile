package com.vothanhhien.automarkmobile.activities;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.vothanhhien.automarkmobile.R;


public class MauPhieuFragment extends Fragment {
    private LinearLayout m50_xemtruoc;
    private LinearLayout m50_luu;
    private LinearLayout m50_open;
    private LinearLayout m60_xemtruoc;
    private LinearLayout m60_luu;
    private LinearLayout m60_open;
    private LinearLayout m80_xemtruoc;
    private LinearLayout m80_luu;
    private LinearLayout m80_open;
    private LinearLayout m100_xemtruoc;
    private LinearLayout m100_luu;
    private LinearLayout m100_open;

    private final static int PREVIEW_CODE = 0x01;
    private final static int SAVE_CODE = 0x02;
    private final static int OPEN_CODE = 0x03;

    public MauPhieuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_mau_phieu, container, false);
        m50_xemtruoc = (LinearLayout) myView.findViewById(R.id.m50_xemtruoc);
        m50_xemtruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(20, PREVIEW_CODE);
            }
        });

        m50_luu = (LinearLayout) myView.findViewById(R.id.m50_luu);
        m50_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(20, SAVE_CODE);
            }
        });

        m50_open = (LinearLayout) myView.findViewById(R.id.m50_open);
        m50_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(20, OPEN_CODE);
            }
        });

        m60_xemtruoc = (LinearLayout) myView.findViewById(R.id.m60_xemtruoc);
        m60_xemtruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(40, PREVIEW_CODE);
            }
        });

        m60_luu = (LinearLayout) myView.findViewById(R.id.m60_luu);
        m60_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(40, SAVE_CODE);
            }
        });

        m60_open = (LinearLayout) myView.findViewById(R.id.m60_open);
        m60_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(40, OPEN_CODE);
            }
        });

        m80_xemtruoc = (LinearLayout) myView.findViewById(R.id.m80_xemtruoc);
        m80_xemtruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(60, PREVIEW_CODE);
            }
        });

        m80_luu = (LinearLayout) myView.findViewById(R.id.m80_luu);
        m80_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(60, SAVE_CODE);
            }
        });

        m80_open = (LinearLayout) myView.findViewById(R.id.m80_open);
        m80_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunEvent(60, OPEN_CODE);
            }
        });


        return myView;
    }

    void RunEvent(int paperType, int actionCode) {
        String filePath = "" + paperType;
        String urlPath = "" + paperType;
        Toast.makeText(getContext(), "Bạn vừa nhấn chọn loại phiếu " + paperType + " câu với mã hành động là " + actionCode, Toast.LENGTH_SHORT).show();
        switch (actionCode) {
            case PREVIEW_CODE:
                return;
            case SAVE_CODE:
                return;
            case OPEN_CODE:
                return;
            default:
                return;
        }
    }
}
