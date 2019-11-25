package com.quizapp.history.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quizapp.R;
import com.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private ArrayList<ShortQuizResult> mQuizResults = new ArrayList<>();
    HistoryViewHolder.HistoryViewHolderListener mListener;


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);

        return new HistoryViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        if (holder instanceof HistoryViewHolder) {
            ((HistoryViewHolder) holder).onBind(mQuizResults.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return mQuizResults.size();
    }

    public void setQuestions(List<ShortQuizResult> quizResults) {
        mQuizResults.clear();
        mQuizResults.addAll(quizResults);
        notifyDataSetChanged();
    }
}