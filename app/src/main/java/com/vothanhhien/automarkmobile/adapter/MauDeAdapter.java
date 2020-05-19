//package com.vothanhhien.automarkmobile.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.snackbar.Snackbar;
//import com.vothanhhien.automarkmobile.R;
//import com.vothanhhien.automarkmobile.activities.MauDe.TuyChonActivity;
//import com.vothanhhien.automarkmobile.activities.MauDe.MauDeActivity;
//import com.vothanhhien.automarkmobile.models.TypeSheet;
//import test.AutoMarkDataBaseSource;
//
//import java.util.List;
//
//public class MauDeAdapter extends RecyclerView.Adapter<MauDeViewHolder> {
//    Context context;
//    List<TypeSheet> list;
//
//    public MauDeAdapter(Context context, List<TypeSheet> sheets) {
//        this.context = context;
//        this.list = sheets;
//    }
//
//    @NonNull
//    @Override
//    public MauDeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setofomr_element, parent, false);
//        return new MauDeViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MauDeViewHolder holder, int position) {
//        Log.d("SetOfOMRAdapter", "onBindViewHolder: ");
//        holder.tvName.setText(list.get(position).getName());
////        holder.tvQuestionCount.setText(list.get(position).getQuestionCount()+"");
////        holder.tvMaximumScore.setText(list.get(position).getCoreMaximum()+"");
////        holder.tvSheetType.setText(list.get(position).getTypeSheet()+"");
//        holder.tvPosition.setText(position+"");
//        holder.elementSetOfOMR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, TuyChonActivity.class);
//                intent.putExtra("Sheet",list.get(position).getTypeSheetID());
//                context.startActivity(intent);
//            }
//        });
//        holder.elementSetOfOMR.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                holder.deleteSet.setVisibility(View.VISIBLE);
//                AutoMarkDataBaseSource autoMarkDataBaseSource = new AutoMarkDataBaseSource(context);
//                autoMarkDataBaseSource.deleteSheet(list.get(position).getTypeSheetID());
//                ((MauDeActivity)context).setRecyclerView();
//                Snackbar.make(v, "Delete!" , Snackbar.LENGTH_LONG).show();
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
//class MauDeViewHolder extends RecyclerView.ViewHolder {
//
//    TextView tvName,tvQuestionCount,tvMaximumScore,tvSheetType,tvPosition;
//    RelativeLayout elementSetOfOMR;
//    ImageView deleteSet;
//
//    public MauDeViewHolder(@NonNull View itemView) {
//        super(itemView);
//        tvName = itemView.findViewById(R.id.tv_name_setofomr);
//        tvQuestionCount = itemView.findViewById(R.id.tv_numberquesion_setofomr);
//        tvMaximumScore = itemView.findViewById(R.id.tv_maximumscore_setofomr);
//        tvSheetType= itemView.findViewById(R.id.tv_typesheet_setofomr);
//        elementSetOfOMR = itemView.findViewById(R.id.elementSetOfOMR);
//        tvPosition = itemView.findViewById(R.id.textViewPosition);
//        deleteSet = itemView.findViewById(R.id.deleteSet);
//    }
//}
