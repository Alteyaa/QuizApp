package com.quizapp.quiz.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quizapp.R;
import com.quizapp.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private Listener mListener;
    private TextView mQuizQuestion;
    private TextView mQuizAnswer1;
    private TextView mQuizAnswer2;
    private TextView mQuizAnswer3;
    private TextView mQuizAnswer4;


    QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        mListener = listener;

        mQuizQuestion = itemView.findViewById(R.id.quiz_question_field);
        mQuizAnswer1 = itemView.findViewById(R.id.quiz_question_answer_1);
        mQuizAnswer2 = itemView.findViewById(R.id.quiz_question_answer_2);
        mQuizAnswer3 = itemView.findViewById(R.id.quiz_question_answer_3);
        mQuizAnswer4 = itemView.findViewById(R.id.quiz_question_answer_4);

        //TODO: Initialize views
    }

    void onBind(Question question) {
        mQuizQuestion.setText(question.getQuestion());
    }

    public interface Listener {
        void onAnswerClick(int questionPosition, int answerPosition);
    }
}