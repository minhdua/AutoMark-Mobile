//package com.vothanhhien.automarkmobile.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.activities.MaDe.TaoMaDeActivity;
//import com.vothanhhien.automarkmobile.models.DapAn;
//import test.AutoMarkDataBaseSource;
//
//import java.util.List;
//
//public class MaDeAdapter extends RecyclerView.Adapter<MaDeViewHolder> {
//    Context context;
//    List<DapAn> list;
//    public MaDeAdapter(Context context, List<DapAn> answers) {
//        this.context = context;
//        this.list = answers;
//    }
//
//    @NonNull
//    @Override
//    public MaDeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_element, parent, false);
//        return new MaDeViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MaDeViewHolder holder, int position) {
//        Log.d("SheetSampleAdapter", "onBindViewHolder: ");
//        holder.tvCode.setText(list.get(position).getCode());
//        holder.elementCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, TaoMaDeActivity.class);
//                intent.putExtra("Code",list.get(position).getCode());
//                intent.putExtra("Sheet",list.get(position).getTypeSheet());
//                context.startActivity(intent);
//            }
//        });
//        holder.elementCode.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                AutoMarkDataBaseSource autoMarkDataBaseSource = new AutoMarkDataBaseSource(context);
//                autoMarkDataBaseSource.deleteCorrectAnswers(list.get(position).getCode());
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//}
//
//
//class MaDeViewHolder extends RecyclerView.ViewHolder {
//
//    TextView tvCode,tvDate;
//    RelativeLayout elementCode;
//    public MaDeViewHolder(@NonNull View itemView) {
//        super(itemView);
//        elementCode =itemView.findViewById(R.id.elementCode);
//        tvCode = itemView.findViewById(R.id.textViewCode);
//        tvDate = itemView.findViewById(R.id.textViewDate);
//
//    }
//}
