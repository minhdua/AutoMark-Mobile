package com.vothanhhien.automarkmobile.activities.NhapDapAn.Hand;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.sqlite.MauTraLoiDatabase;

import java.util.Collections;
import java.util.List;

public class DapAn_Adapter extends RecyclerView.Adapter<DapAn_Holder> {
    Context myContext;
    int vt[];
    BaiThi baithi;
    List<DapAnIn> list = Collections.emptyList();
    boolean isCheck = false;
    boolean isReview = false;
    boolean isMaybe = false;
    MauTraLoiDatabase mauTraLoiDatabase;
    // Constructure của hắn
    public DapAn_Adapter(List<DapAnIn> list, Context myContext, BaiThi baiThi) {
        this.baithi = baiThi;
        this.vt = new int [baiThi.getLoaiGiay() + 20];
        this.list = list;
        this.myContext = myContext;
        if (myContext instanceof MakeActivity)
        {
            isReview = ((MakeActivity) myContext).isReview;
            isMaybe = ((MakeActivity) myContext).isMaybe;
        }
    }

    @Override
    public DapAn_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DapAn_Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hand_row, parent, false));
    }

    // Đặt màu nền cho các ô được lựa chọn
    @SuppressLint("ResourceType")
    public void setBackground(int a, DapAn_Holder holder) {
        if (isCheck == true) {
            if (a == 0) {
                holder.n.setBackground(this.myContext.getResources().getDrawable(R.drawable.num_red));
                holder.end.setBackground(this.myContext.getResources().getDrawable(R.drawable.numreverse_red));
                holder.choices.setBackground(this.myContext.getResources().getDrawable(R.drawable.choices_row_red));
            } else {
                holder.n.setBackground(this.myContext.getResources().getDrawable(R.drawable.num));
                holder.end.setBackground(this.myContext.getResources().getDrawable(R.drawable.numreverse));
                holder.choices.setBackground(this.myContext.getResources().getDrawable(R.drawable.choices_row));
            }
        }
        holder.a.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle));
        holder.b.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle));
        holder.c.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle));
        holder.d.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle));
        switch (a) {
            case 1:
                holder.a.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle2));
                return;
            case 2:
                holder.b.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle2));
                return;
            case 3:
                holder.c.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle2));
                return;
            case 4:
                holder.d.setBackground(this.myContext.getResources().getDrawable(R.drawable.circle2));
                return;
            default:
                return;
        }
    }

    // Thiết lập nhãn hiển thị cho các ô tròn VD: A B C D
    public void setText(DapAn_Holder holder, int pos) {
        holder.a.setText(this.list.get(pos).a);
        holder.b.setText(this.list.get(pos).b);
        holder.c.setText(this.list.get(pos).c);
        holder.d.setText(this.list.get(pos).d);
    }

    public void setSelect(int a, int pos) {
        if (this.list.get(pos).getSelect() == a) {
            this.list.get(pos).setSelect(0);
            vt[pos + 20] = -1;
        } else {
            this.list.get(pos).setSelect(a);
            vt[pos + 20] = a - 1;
        }
        notifyDataSetChanged();
    }

    // Phương thức kiểm tra xem tất cả các ô đã được đánh dấu hết chưa
    public boolean isOK() {
        for (DapAnIn dapAnIn : list)
            if (dapAnIn.getSelect() == 0)
                return false;
        return true;
    }

    // Phương thức lấy địa chỉ của các đáp án
    public String getDsDapAn() {
        String ds = "";
        for (DapAnIn dapAnIn : list)
            ds += Integer.toString(dapAnIn.getSelect());
        return ds;
    }

    // Gọi ngược để tô đỏ những ô chưa được đánh đấu
    public void CheckForSave() {
        isCheck = true;
        notifyDataSetChanged();
    }

    int cout_press = 0;

    @Override
    public void onBindViewHolder(DapAn_Holder holder, final int position) {
        holder.n.setText(list.get(position).n);
        setBackground(this.list.get(position).getSelect(), holder);
        setText(holder, position);
        holder.a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isReview || isMaybe)
                {
                    if (Integer.parseInt(list.get(position).n) > baithi.getSoCau())
                    {
                        Toast.makeText(myContext, "Đã vượt quá giới hạn số câu của bài thi!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DapAn_Adapter.this.setSelect(1, position);
                }
                else if (cout_press++ % 10 == 0)
                    Toast.makeText(myContext, "BẢO ĐẢM AN TOÀN: Không cho phép sửa đáp án!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isReview || isMaybe)
                {
                    if (Integer.parseInt(list.get(position).n) > baithi.getSoCau())
                    {
                        Toast.makeText(myContext, "Đã vượt quá giới hạn số câu của bài thi!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DapAn_Adapter.this.setSelect(2, position);
                }
                else if (cout_press++ % 10 == 0)
                    Toast.makeText(myContext, "BẢO ĐẢM AN TOÀN: Không cho phép sửa đáp án!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isReview || isMaybe)
                {
                    if (Integer.parseInt(list.get(position).n) > baithi.getSoCau())
                    {
                        Toast.makeText(myContext, "Đã vượt quá giới hạn số câu của bài thi!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DapAn_Adapter.this.setSelect(3, position);
                }
                else if (cout_press++ % 10 == 0)
                    Toast.makeText(myContext, "BẢO ĐẢM AN TOÀN: Không cho phép sửa đáp án!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isReview || isMaybe)
                {
                    if (Integer.parseInt(list.get(position).n) > baithi.getSoCau())
                    {
                        Toast.makeText(myContext, "Đã vượt quá giới hạn số câu của bài thi!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DapAn_Adapter.this.setSelect(4, position);
                }
                else if (cout_press++ % 10 == 0)
                    Toast.makeText(myContext, "BẢO ĐẢM AN TOÀN: Không cho phép sửa đáp án!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
