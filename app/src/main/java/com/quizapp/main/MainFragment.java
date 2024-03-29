package com.quizapp.main;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.SeekBar;
import android.widget.TextView;

import com.quizapp.R;
import com.quizapp.core.CoreFragment;
import com.quizapp.quiz.QuizActivity;

import org.angmarch.views.NiceSpinner;

public class MainFragment extends CoreFragment {


    private AppCompatSeekBar mSeekBar;
    private NiceSpinner mCategorySpinner;
    private NiceSpinner mDifficultySpinner;

    private TextView mAmount;
    private View mStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSeekBar = view.findViewById(R.id.main_amount_seek_bar);
        mCategorySpinner = view.findViewById(R.id.main_category_spinner);
        mDifficultySpinner = view.findViewById(R.id.main_difficulty_spinner);
        mAmount = view.findViewById(R.id.main_questions_amount);
        mStart = view.findViewById(R.id.main_start);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAmount.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int categoryId = 0;

                if (mCategorySpinner.getSelectedIndex() != 0) {
                    categoryId = mCategorySpinner.getSelectedIndex() + 8;
                }

                QuizActivity.start(
                        getContext(),
                        mSeekBar.getProgress(),
                        categoryId,
                        mCategorySpinner.getSelectedItem().toString()
                );

                Log.d("ololo", "Start properties - amount:" + mSeekBar.getProgress()
                        + " category: " + mCategorySpinner.getSelectedIndex()
                        + " difficulty: " + mDifficultySpinner.getSelectedIndex());
            }
        });
    }

}
