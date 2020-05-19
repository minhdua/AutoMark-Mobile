package com.vothanhhien.automarkmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.constants.SC;

import java.util.List;

public class DapAnAdapter extends RecyclerView.Adapter<DapAnViewHolder> {
    private Context context;
    List<Character> correctAnswerCharacterList;
    public DapAnAdapter(Context context, List<Character> correctAnswerCharacterList) {
        this.context = context;
        this.correctAnswerCharacterList = correctAnswerCharacterList;
    }
    @NonNull
    @Override
    public DapAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.omr_element, parent, false);
        return new DapAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DapAnViewHolder holder, int position) {
        holder.tvQuestionNumber.setText("" + (position + 1) + ".");
        switch (correctAnswerCharacterList.get(position)) {
            case 'A':
                clearAllButtons(holder);
                holder.btnA.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                holder.tvChoiceAnswer.setText("A");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
                break;
            case 'B':
                clearAllButtons(holder);
                holder.btnB.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                holder.tvChoiceAnswer.setText("B");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
                break;
            case 'C':
                clearAllButtons(holder);
                holder.btnC.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                holder.tvChoiceAnswer.setText("C");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
                break;
            case 'D':
                clearAllButtons(holder);
                holder.btnD.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                holder.tvChoiceAnswer.setText("D");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
                break;
            case 'N':
                clearAllButtons(holder);
                holder.tvChoiceAnswer.setVisibility(View.GONE);
                holder.tvQuestionNumberR.setVisibility(View.GONE);
        }
        holder.btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnA.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                correctAnswerCharacterList.set(position, 'A');
                holder.tvChoiceAnswer.setText("A");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
            }
        });

        holder.btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnB.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                correctAnswerCharacterList.set(position, 'B');
                holder.tvChoiceAnswer.setText("B");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
            }
        });

        holder.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnC.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                correctAnswerCharacterList.set(position, 'C');
                holder.tvChoiceAnswer.setText("C");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
            }
        });

        holder.btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllButtons(holder);
                holder.btnD.setBackgroundResource(SC.MARKED_BUTTON_COLOR);
                correctAnswerCharacterList.set(position, 'D');
                holder.tvChoiceAnswer.setText("D");
                holder.tvQuestionNumberR.setText((position+1)+"");
                holder.tvChoiceAnswer.setVisibility(View.VISIBLE);
                holder.tvQuestionNumberR.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return correctAnswerCharacterList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void clearAllButtons(DapAnViewHolder holder) {
        holder.btnA.setBackgroundResource(SC.DEFAULT_BUTTON_COLOR);
        holder.btnB.setBackgroundResource(SC.DEFAULT_BUTTON_COLOR);
        holder.btnC.setBackgroundResource(SC.DEFAULT_BUTTON_COLOR);
        holder.btnD.setBackgroundResource(SC.DEFAULT_BUTTON_COLOR);
        holder.tvChoiceAnswer.setVisibility(View.GONE);
        holder.tvQuestionNumberR.setVisibility(View.GONE);
    }
}


class DapAnViewHolder extends RecyclerView.ViewHolder {

    Button btnA, btnB, btnC, btnD;
    TextView tvQuestionNumber,tvChoiceAnswer,tvQuestionNumberR;


    public DapAnViewHolder(@NonNull View itemView) {
        super(itemView);
        btnA = itemView.findViewById(R.id.sheet_option1);
        btnB = itemView.findViewById(R.id.sheet_option2);
        btnC = itemView.findViewById(R.id.sheet_option3);
        btnD = itemView.findViewById(R.id.sheet_option4);
        tvQuestionNumber = itemView.findViewById(R.id.sheet_question_number);
        tvChoiceAnswer= itemView.findViewById(R.id.text_view_choice_answer);
        tvQuestionNumberR = itemView.findViewById(R.id.text_view_number_question);
    }
}
