package com.quizapp.history.recycler;

import android.view.View;

public class HistoryViewHolder {


    public HistoryViewHolder(View inflate, HistoryViewHolderListener mListener) {
    }

    public interface HistoryViewHolderListener {
        void onClick(int position);

        void onOptionsClick(int position);
    }
}
