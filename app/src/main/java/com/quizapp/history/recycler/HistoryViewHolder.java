package com.quizapp.history.recycler;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolder extends RecyclerView.ViewHolder {


    public HistoryViewHolder(View inflate, HistoryViewHolderListener mListener) {
    }



    public interface HistoryViewHolderListener {
        void onClick(int position);

        void onOptionsClick(int position);
    }
}
