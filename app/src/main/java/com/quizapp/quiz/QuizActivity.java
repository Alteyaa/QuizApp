package com.quizapp.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.quizapp.R;
import com.quizapp.quiz.recycler.QuizAdapter;
import com.quizapp.quiz.recycler.QuizViewHolder;

public class QuizActivity extends AppCompatActivity
        implements QuizViewHolder.Listener {

    private QuizViewModel mViewModel;

    private QuizAdapter mAdapter;
    private ProgressBar mProgress;
    private TextView mProgressText;
    private TextView mTitle;
    private RecyclerView mRecycler;

    private final static String EXTRA_AMOUNT = "amount";
    private final static String EXTRA_CATEGORY = "category";
    private final static String EXTRA_DIFFICULTY = "difficulty";

    public static void start(Context context, int amount, int category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY,category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        context.startActivity(intent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();

        mViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);

        mViewModel.questions.observe(this, questions -> {
            Log.d("ololo", "Questions size " + questions.size());
            TextView view = findViewById(R.id.quiz_title);
            view.setText(questions.get(0).getCategory());
            mAdapter.setQuestions(questions);
        });

        mViewModel.currentQuestionPosition.observe(this, position -> {
            Log.d("ololo", "Current position " + position);
            mProgressText.setText(position + 1 + "/" + mAdapter.getItemCount());
            mProgress.setProgress(position + 1);
            mRecycler.smoothScrollToPosition(position + 1);


        });

        mViewModel.finishEvent.observe(this, aVoid -> finish());

        int amount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);
        int category = getIntent().getIntExtra(EXTRA_CATEGORY,0);
        String difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);

        mViewModel.init(amount,category, difficulty);
    }

    private void initView() {
        mAdapter = new QuizAdapter(this);

        mProgress = findViewById(R.id.quiz_progress);
        mProgressText = findViewById(R.id.quiz_progress_text);
        mTitle = findViewById(R.id.quiz_title);

        mProgressText.setEnabled(false);

        initRecycler();

        findViewById(R.id.quiz_back).setOnClickListener(v -> mViewModel.onBackPressed());
        mRecycler = findViewById(R.id.quiz_recycler);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false)
        );
        mRecycler.setOnTouchListener((v, event) -> true);

        findViewById(R.id.quiz_skip).setOnClickListener(v -> {
            mViewModel.onSkipClick();
        });
    }

    private void initRecycler() {
        mAdapter = new QuizAdapter(this);
        mRecycler = findViewById(R.id.quiz_recycler);
        mRecycler.setAdapter(mAdapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false);
        mRecycler.setLayoutManager(lm);

        //Prevent user touch scroll
        mRecycler.setOnTouchListener((v, event) -> true);
    }

    @Override
    public void onAnswerClick(int questionPosition, int answerPosition) {
        mViewModel.onAnswerClick(questionPosition, answerPosition);
    }
}
